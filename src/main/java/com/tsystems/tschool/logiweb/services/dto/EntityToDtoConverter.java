package com.tsystems.tschool.logiweb.services.dto;

import com.tsystems.tschool.logiweb.entities.Cargo;
import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.OrderWaypoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class EntityToDtoConverter {

    public OrderWaypointDto convertEntityToDto(OrderWaypoint waypoint){
        return new OrderWaypointDto(waypoint.getCity().getName(), waypoint.getSequenceNumber());
    }

    public List<OrderWaypointDto> convertWaypointsToDto(List<OrderWaypoint> orderWaypoints){
        List<OrderWaypointDto> listOfDto= new ArrayList<>();
        for(OrderWaypoint waypoint : orderWaypoints)
            listOfDto.add(this.convertEntityToDto(waypoint));
        return listOfDto;
    }

    public CargoDto convertEntityToDto(Cargo cargo){
        return new CargoDto(cargo.getTitle(), cargo.getWeight(), cargo.getStatus(), cargo.getLoadWaypoint().getCity().getName(),
                cargo.getUnloadWaypoint().getCity().getName());
    }


    public List<CargoDto> convertCargoesToDto(List<Cargo> cargoes){
        List<CargoDto> listOfDto= new ArrayList<>();
        for(Cargo cargo : cargoes)
            listOfDto.add(this.convertEntityToDto(cargo));
        return listOfDto;
    }

    public DriverDto convertEntityToDto(Driver driver){
        if(driver.getCurrentTruck() != null)
        return new DriverDto(driver.getName(), driver.getSurname(), driver.getCurrentTruck().getLicencePlate(),driver.getUserAccount().getMail(),
                driver.getEmployeeId(), driver.getStatus(), driver.getCurrentCity().getName());
        else return new DriverDto(driver.getName(), driver.getSurname(), "",driver.getUserAccount().getMail(),
                driver.getEmployeeId(), driver.getStatus(), driver.getCurrentCity().getName());
    }

    public DeliveryOrderDto convertEntityToDto(DeliveryOrder order){
        DeliveryOrderDto orderDto = new DeliveryOrderDto();
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setStatus(order.getStatus());
        orderDto.setTruckLicencePlate(order.getAssignedTruck().getLicencePlate());

        String drivers = "";
        for(Driver driver : order.getAssignedTruck().getDrivers())
            drivers+= driver.getName() + " " + driver.getSurname()+ "\n";
        orderDto.setDrivers(drivers);
        if (order.getAssignedCargoes() != null) orderDto.setCargoes(convertCargoesToDto(new ArrayList<>(order.getAssignedCargoes()))) ;
        else orderDto.setCargoes(new ArrayList<CargoDto>());
        if (order.getOrderWaypoints() != null) {
            TreeSet<OrderWaypoint> set = new TreeSet<>(order.getOrderWaypoints());
            orderDto.setRoute(set.first().getCity().getName() + " -> " + set.last().getCity().getName());
            StringBuilder fullRoute = new StringBuilder();
            for(OrderWaypoint waypoint: set){
                fullRoute.append(waypoint.getCity().getName()).append(" ->");
            }
            fullRoute.setLength(fullRoute.length() - 2);
            orderDto.setFullRoute(fullRoute.toString());
        }
        return orderDto;
    }

    public List<DeliveryOrderDto> convertOrdersToDto(List<DeliveryOrder> orders){
        List<DeliveryOrderDto> listOfDto= new ArrayList<>();
        for(DeliveryOrder order : orders)
            listOfDto.add(this.convertEntityToDto(order));
        return listOfDto;
    }

    public List<DriverDto> converDriversToDto(List<Driver> drivers){
        List<DriverDto> listOfDto= new ArrayList<>();
        for(Driver driver : drivers)
            listOfDto.add(this.convertEntityToDto(driver));
        return listOfDto;
    }

}
