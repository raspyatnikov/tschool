package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.*;
import com.tsystems.tschool.logiweb.entities.*;
import com.tsystems.tschool.logiweb.entities.Statuses.CargoStatus;
import com.tsystems.tschool.logiweb.entities.Statuses.OrderStatus;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.OrderService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import com.tsystems.tschool.logiweb.services.dto.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<DeliveryOrder> findAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public void editOrder(DeliveryOrder editedTruckModel) {

    }

    @Override
    @Transactional
    public void removeOrder(int orderId) {

    }

    @Override
    @Transactional
    public List<DeliveryOrderDto> getAllOrdersDto(){
        return converter.convertOrdersToDto(orderDao.findAll());
    }

    public OrderWaypoint findWaypointWhereCity(Set<OrderWaypoint> set, String cityName){
        for(OrderWaypoint waypoint : set){
            if (waypoint.getCity().getName().equals(cityName)) return waypoint;
        }
        return null;
    }


    @Transactional
    @Override
    public DeliveryOrder addOrder(String orderNumber, String truck, String driver, List<String> waypoints, List<String> weights, List<String> titles, List<String> cargoLoadCity, List<String> cargoUnloadCity)
    {

        Driver assignedDriver_ = driverDao.findById(Integer.parseInt(driver));
        Truck assignedTruck = truckDao.findByLicencePlate(truck);
        DeliveryOrder order = new DeliveryOrder();
        order.setStatus(OrderStatus.CREATED);
        order.setOrderNumber(Integer.parseInt(orderNumber));
        Set<OrderWaypoint> orderWaypoints= new HashSet<>();
        Set<Cargo> cargoes = new HashSet<>();
        int j = 0;

        OrderWaypoint waypoint = null;
        order = orderDao.create(order);
        driverService.assignDriverToTruck(assignedDriver_.getId(), assignedTruck.getId());
        truckService.assignOrderToTruck(assignedTruck.getId(), order.getId());
        for (int i = 0; i <waypoints.size(); i++){
            waypoint = new OrderWaypoint();
            waypoint.setCity(cityDao.findByName(waypoints.get(i)));
            waypoint.setSequenceNumber(++j);
            waypoint.setOrder(order);

            waypoint = waypointDao.create(waypoint);

            orderWaypoints.add(waypoint);
        }
        for(int i = 0; i < weights.size(); i++){

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
    }
}
