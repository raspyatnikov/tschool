package com.tsystems.tschool.logiweb.services.dto;

import com.tsystems.tschool.logiweb.entities.Statuses.OrderStatus;

import java.util.List;
import java.util.TreeSet;

public class DeliveryOrderDto {

    private int orderNumber;
    private OrderStatus status;
    private String truckLicencePlate;
    private String drivers;
    private List<CargoDto> cargoes;
    private String route;
    private String fullRoute;



    public DeliveryOrderDto() {
    }

    public DeliveryOrderDto(int orderNumber, OrderStatus status, String truck, List<CargoDto> cargoes, String drivers, String route, String fullRoute ) {
        this.orderNumber = orderNumber;
        this.status = status;
        this.truckLicencePlate = truck;
        this.cargoes = cargoes;
        this.drivers = drivers;
        this.route = route;
        this.fullRoute = fullRoute;
    }

    public String getTruckLicencePlate() {
        return truckLicencePlate;
    }

    public void setTruckLicencePlate(String truckLicencePlate) {
        this.truckLicencePlate = truckLicencePlate;
    }

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<CargoDto> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<CargoDto> cargoes) {
        this.cargoes = cargoes;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getFullRoute() {
        return fullRoute;
    }

    public void setFullRoute(String fullRoute) {
        this.fullRoute = fullRoute;
    }
}
