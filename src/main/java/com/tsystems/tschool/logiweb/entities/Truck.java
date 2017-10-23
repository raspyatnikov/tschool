package com.tsystems.tschool.logiweb.entities;

import com.tsystems.tschool.logiweb.entities.Statuses.TruckStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trucks")
@NamedQuery(name = "Truck.findTruckByLicencePlate", query = "SELECT truck FROM Truck truck WHERE truck.licencePlate=:licencePlate")
public class Truck {

    @Id
    @GeneratedValue
    @Column(name = "truck_id")
    private int id;

    @Column(name = "truck_license_plate_UQ")
    private String licencePlate;

    @Column(name = "truck_cargo_capacity")
    private Float cargoCapacity;

    @Column(name = "truck_status")
    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "truck_current_location_city_FK")
    private City currentCity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "currentTruck")
    private Set<Driver> drivers;

    public Truck() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Float getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(Float cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }


    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

}
