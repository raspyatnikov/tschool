package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.*;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.*;
import com.tsystems.tschool.logiweb.entities.Statuses.CargoStatus;
import com.tsystems.tschool.logiweb.entities.Statuses.DriverStatus;
import com.tsystems.tschool.logiweb.entities.Statuses.OrderStatus;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.OrderService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.dto.*;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class OrderServiceImpl  implements OrderService{
    private OrderDao orderDao;
    private DriverDao driverDao;
    private TruckDao truckDao;
    private CityDao cityDao;
    private CargoDao cargoDao;
    private OrderWaypointDao waypointDao;
    private DriverService driverService;
    private TruckService truckService;
    EntityToDtoConverter converter = new EntityToDtoConverter();

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(CargoDao cargoDao, OrderDao orderDao, DriverDao driverDao, TruckDao truckDao, CityDao cityDao, OrderWaypointDao waypointDao, DriverService driverService, TruckService truckService) {
        this.orderDao = orderDao;
        this.driverDao = driverDao;
        this.truckDao = truckDao;
        this.cityDao = cityDao;
        this.cargoDao = cargoDao;
        this.waypointDao = waypointDao;
        this.driverService = driverService;
        this.truckService = truckService;
    }

    @Override
    @Transactional
    public List<DeliveryOrder> findAllOrders() throws ServiceException {

        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void editOrder(DeliveryOrder editedTruckModel) {

    }

    @Override
    @Transactional
    public void removeOrder(int orderId) {

    }

    @Override
    @Transactional
    public DriverOrderDto getDriverOrderDto(int driverId) throws ServiceException {
        try {
            Driver driver = driverDao.findById(driverId);
            if (driver == null) throw new ServiceException("Driver not found!");
            if(driver.getCurrentOrder() == null)
                throw new ServiceException("Driver has no active order!");
            DeliveryOrder order = driver.getCurrentOrder();
            DriverOrderDto driverOrderDto = new DriverOrderDto();
            driverOrderDto.setOrderId(order.getId());
            driverOrderDto.setOrderNumber(order.getOrderNumber());
            driverOrderDto.setTruckLicencePlate(order.getAssignedTruck().getLicencePlate());
            //Driver commander = driverService.getCoDriver(driver, order.getAssignedTruck());
            //driverOrderDto.setCommanderFullName(commander.getName() + " " + commander.getSurname());
            Map<Integer, String> waypointsCities = new HashMap<>();
            for(OrderWaypoint waypoint: order.getOrderWaypoints()){
                waypointsCities.put(waypoint.getSequenceNumber(), waypoint.getCity().getName());
            }
            driverOrderDto.setOrderWaypoints(waypointsCities);
            return driverOrderDto;
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public List<DeliveryOrderDto> getAllOrdersDto() throws ServiceException {

        try {
            return converter.convertOrdersToDto(orderDao.findAll());
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public DeliveryOrderDto getOrderDto(int orderId) throws ServiceException {
        try {
            return converter.convertEntityToDto(orderDao.findById(orderId));
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }



    private OrderWaypoint findWaypointWhereCity(Set<OrderWaypoint> set, String cityName){
        for(OrderWaypoint waypoint : set){
            if (waypoint.getCity().getName().equals(cityName)) return waypoint;
        }
        return null;
    }


    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public DeliveryOrder addOrder(String orderNumber, String truck, String driver, List<String> waypoints, List<String> weights, List<String> titles, List<String> cargoLoadCity, List<String> cargoUnloadCity) throws ServiceException {

        try {
            String driverEmployeeId = driver.split("\\(")[1].substring(0, (driver.split("\\(")[1]).length() - 1);
            Driver assignedDriver_ = driverDao.findByEmployeeId(Integer.parseInt(driverEmployeeId));
            Truck assignedTruck = truckDao.findByLicencePlate(truck);
            DeliveryOrder order = new DeliveryOrder();
            order.setStatus(OrderStatus.IN_PROCESS);
            order.setAssignedTruck(assignedTruck);
            order.setOrderNumber(Integer.parseInt(orderNumber));
            Set<OrderWaypoint> orderWaypoints = new HashSet<>();
            Set<Cargo> cargoes = new HashSet<>();
            int j = 0;

            OrderWaypoint waypoint = null;
            Set<Driver> orderDrivers = new HashSet<>();
            orderDrivers.add(assignedDriver_);
            order.setOrderDrivers(orderDrivers);
            order = orderDao.create(order);
            assignedDriver_.setCurrentOrder(order);
            assignedDriver_.setStatus(DriverStatus.DRIVING);
            assignedDriver_.setCurrentTruck(assignedTruck);
            driverDao.update(assignedDriver_);
            for (int i = 0; i < waypoints.size(); i++) {
                waypoint = new OrderWaypoint();
                waypoint.setCity(cityDao.findByName(waypoints.get(i)));
                waypoint.setSequenceNumber(++j);
                waypoint.setOrder(order);

                waypoint = waypointDao.create(waypoint);

                orderWaypoints.add(waypoint);
            }
            for (int i = 0; i < weights.size(); i++) {

                Cargo cargo = new Cargo();
                cargo.setOrderForThisCargo(order);
                cargo.setStatus(CargoStatus.WAITING_FOR_PICKUP);
                cargo.setLoadWaypoint(findWaypointWhereCity(orderWaypoints, cargoLoadCity.get(i)));
                cargo.setUnloadWaypoint(findWaypointWhereCity(orderWaypoints, cargoUnloadCity.get(i)));
                cargo.setTitle(titles.get(i));
                cargo.setWeight(Float.parseFloat(weights.get(i)));
                cargoDao.create(cargo);

            }
            return order;
        }catch (DaoException e){
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Float calculateMaxLoadWeight(ShortOrderDto shortOrderDto) {
        float[] waypointsMaxLoad = new float[shortOrderDto.getWaypointsCities().size()];
        for(CargoDto cargo : shortOrderDto.getCargoes()) {
            for(int i = shortOrderDto.getWaypointsCities().indexOf(cargo.getCityToLoadName()); i<= shortOrderDto.getWaypointsCities().indexOf(cargo.getCityToUnLoadName()); i++)
            waypointsMaxLoad[i] += cargo.getWeight();
            waypointsMaxLoad[shortOrderDto.getWaypointsCities().indexOf(cargo.getCityToUnLoadName())] -= cargo.getWeight();
        }
        Arrays.sort(waypointsMaxLoad);
        return waypointsMaxLoad[waypointsMaxLoad.length-1];
    }

    @Override
    @Transactional
    public void setInProgressStatusForOrder(int orderId)
            throws ServiceException {
        try {
            DeliveryOrder order = orderDao.findById(orderId);

            if (order == null) {
                throw new ServiceException("Order does not exist.");
            }

            if (order.getAssignedCargoes() == null
                    || order.getAssignedCargoes().isEmpty()) {
                throw new ServiceException(
                        "Order must contain at least 1 cargo.");
            } else if (order.getAssignedTruck() == null) {
                throw new ServiceException(
                        "Order must have assigned truck.");
            } else if (order.getAssignedTruck().getDrivers() == null
                    || order.getAssignedTruck().getDrivers().size() < 2) {
                throw new ServiceException(
                        "Truck must have full crew. Assign drivers.");
            } else if (order.getStatus() != OrderStatus.CREATED) {
                throw new ServiceException(
                        "Order must be in CREATED STATE.");
            }

            order.setStatus(OrderStatus.IN_PROCESS);
            orderDao.update(order);
            LOGGER.info("Order id#" + order.getId() + " changed status to " + OrderStatus.IN_PROCESS);
        } catch (DaoException e) {
            LOGGER.warn("Database processing error.", e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public boolean isAllCargoesInOrderDelivered(int orderId)
            throws ServiceException {
        try {
            DeliveryOrder order = orderDao.findById(orderId);
            if (order == null) {
                throw new ServiceException("Order not found!");
            }

            Set<Cargo> cargoes = order.getAssignedCargoes();
            for (Cargo cargo : cargoes) {
                if (cargo.getStatus() != CargoStatus.DELIVERED) {
                    return false;
                }
            }
            return true;
        } catch (DaoException e) {
            LOGGER.warn("Error in DAO layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public boolean setStatusDeliveredForOrder(int orderId)
            throws ServiceException {
        try {
            DeliveryOrder order = orderDao.findById(orderId);
            if(order == null) {
                throw new ServiceException("Order not found!");
            }

            if (isAllCargoesInOrderDelivered(orderId)) {
                order.setStatus(OrderStatus.DELIVERED);
                for(Driver driver: order.getOrderDrivers()){
                    driver.setCurrentOrder(null);
                    driver.setCurrentTruck(null);
                    driver.setStatus(DriverStatus.FREE);
                    driverDao.update(driver);
                }
                orderDao.update(order);
                LOGGER.info("Order id#" + order.getId() + " changed status to "
                        + OrderStatus.DELIVERED);
                return true;
            }
            else return false;
        } catch (DaoException e) {
            LOGGER.warn("Error in DAO layer", e);
            throw new ServiceException(e);
        }
    }
}
