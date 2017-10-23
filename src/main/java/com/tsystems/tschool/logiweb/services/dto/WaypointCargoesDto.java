package com.tsystems.tschool.logiweb.services.dto;

import java.util.List;

public class WaypointCargoesDto {

    private String cityName;
    private List<CargoDto> cargoesToload;
    private List<CargoDto> cargoesToUnload;

    public WaypointCargoesDto() {
    }

    public List<CargoDto> getCargoesToload() {
        return cargoesToload;
    }

    public void setCargoesToload(List<CargoDto> cargoesToload) {
        this.cargoesToload = cargoesToload;
    }

    public List<CargoDto> getCargoesToUnload() {
        return cargoesToUnload;
    }

    public void setCargoesToUnload(List<CargoDto> cargoesToUnload) {
        this.cargoesToUnload = cargoesToUnload;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
