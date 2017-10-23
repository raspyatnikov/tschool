package com.tsystems.tschool.logiweb.entities.Statuses;

public enum DriverStatus {
    FREE("FREE"),
    DRIVING("DRIVING");

    public String name;

    DriverStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
