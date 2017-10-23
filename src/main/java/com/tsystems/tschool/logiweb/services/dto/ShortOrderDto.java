package com.tsystems.tschool.logiweb.services.dto;

import java.util.List;

public class ShortOrderDto {

    List<String> waypointsCities;
    List<CargoDto> cargoes;

    public ShortOrderDto(List<String> waypointsCities, List<CargoDto> cargoes) {
        this.waypointsCities = waypointsCities;
        this.cargoes = cargoes;
    }

    public List<String> getWaypointsCities() {
        return waypointsCities;
    }

    public void setWaypointsCities(List<String> waypointsCities) {
        this.waypointsCities = waypointsCities;
    }

    public List<CargoDto> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<CargoDto> cargoes) {
        this.cargoes = cargoes;
    }
}
