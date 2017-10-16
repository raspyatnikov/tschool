package com.tsystems.tschool.logiweb.entities.Statuses;


public enum TruckStatus {
    FAULTY("FAULTY"),
    OK("OK");

    public String name;

    TruckStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
