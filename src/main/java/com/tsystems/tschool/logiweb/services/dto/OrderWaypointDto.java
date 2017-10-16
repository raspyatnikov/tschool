package com.tsystems.tschool.logiweb.services.dto;

public class OrderWaypointDto {

    String cityName;
    int seqNumber;

    public OrderWaypointDto(String cityName, int seqNumber) {
        this.cityName = cityName;
        this.seqNumber = seqNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }
}
