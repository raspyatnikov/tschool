package com.tsystems.tschool.logiweb.services.dto;

import com.tsystems.tschool.logiweb.entities.Statuses.DriverStatus;

public class DriverDto {

   private String name;
   private String surname;
   private String assignedTruckLicencePlate;
   private String email;
   private int employeeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAssignedTruckLicencePlate() {
        return assignedTruckLicencePlate;
    }

    public void setAssignedTruckLicencePlate(String assignedTruckLicencePlate) {
        this.assignedTruckLicencePlate = assignedTruckLicencePlate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public String getCurrentCityName() {
        return currentCityName;
    }

    public void setCurrentCityName(String currentCityName) {
        this.currentCityName = currentCityName;
    }

    private DriverStatus status;
   private String currentCityName;

    public DriverDto(String name, String surname, String assignedTruckLicencePlate, String email, int employeeId, DriverStatus status, String currentCityName) {
        this.name = name;
        this.surname = surname;
        this.assignedTruckLicencePlate = assignedTruckLicencePlate;
        this.email = email;
        this.employeeId = employeeId;
        this.status = status;
        this.currentCityName = currentCityName;
    }
}
