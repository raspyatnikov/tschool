package com.tsystems.tschool.logiweb.services.dto;

import com.tsystems.tschool.logiweb.entities.Statuses.CargoStatus;

public class CargoDto {

    private int cargoId;
    private String title;
    private Float weight;
    private CargoStatus status;
    private String cityToLoadName;
    private String cityToUnLoadName;

    public CargoDto(int cargoId, String title, Float weight, CargoStatus status, String cityToLoadName, String cityToUnLoadName) {
        this.cargoId = cargoId;
        this.title = title;
        this.weight = weight;
        this.status = status;
        this.cityToLoadName = cityToLoadName;
        this.cityToUnLoadName = cityToUnLoadName;
    }

    public CargoDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    public String getCityToLoadName() {
        return cityToLoadName;
    }

    public void setCityToLoadName(String cityToLoadName) {
        this.cityToLoadName = cityToLoadName;
    }

    public String getCityToUnLoadName() {
        return cityToUnLoadName;
    }

    public void setCityToUnLoadName(String cityToUnLoadName) {
        this.cityToUnLoadName = cityToUnLoadName;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }
}
