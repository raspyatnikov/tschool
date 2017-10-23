package com.tsystems.tschool.logiweb.services.dto;

import com.tsystems.tschool.logiweb.entities.Statuses.TruckStatus;

public class TruckDTO {

    String currentCity;
    Float cargoCapacity;
    String licencePlate;
    TruckStatus status;

    public TruckDTO() {
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public Float getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(Float cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }
}
