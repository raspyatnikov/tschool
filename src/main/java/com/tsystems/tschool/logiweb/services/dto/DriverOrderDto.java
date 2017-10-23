package com.tsystems.tschool.logiweb.services.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public class DriverOrderDto {

    private int orderId;
    private int orderNumber;
    private String commanderFullName;
    private String truckLicencePlate;
    private Map<Integer, String> orderWaypoints;

    public DriverOrderDto() {
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCommanderFullName() {
        return commanderFullName;
    }

    public void setCommanderFullName(String commanderFullName) {
        this.commanderFullName = commanderFullName;
    }

    public String getTruckLicencePlate() {
        return truckLicencePlate;
    }

    public void setTruckLicencePlate(String truckLicencePlate) {
        this.truckLicencePlate = truckLicencePlate;
    }

    public Map<Integer, String> getOrderWaypoints() {
        return orderWaypoints;
    }

    public void setOrderWaypoints(Map<Integer, String> orderWaypoints) {
        this.orderWaypoints = orderWaypoints;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
