package com.tsystems.tschool.logiweb.entities;

import com.tsystems.tschool.logiweb.entities.Statuses.CargoStatus;

import javax.persistence.*;

@Entity
@Table(name = "cargoes")
public class Cargo {
    @Id
    @GeneratedValue
    @Column(name = "cargo_id")
    private int id;

    @Column(name = "cargo_title")
    private String title;

    @Column(name = "cargo_weight")
    private Float weight;

    @Column(name = "cargo_status")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_waypoint_to_load")
    private OrderWaypoint loadWaypoint;

    public OrderWaypoint getLoadWaypoint() {
        return loadWaypoint;
    }

    public void setLoadWaypoint(OrderWaypoint loadWaypoint) {
        this.loadWaypoint = loadWaypoint;
    }

    public OrderWaypoint getUnloadWaypoint() {
        return unloadWaypoint;
    }

    public void setUnloadWaypoint(OrderWaypoint unloadWaypoint) {
        this.unloadWaypoint = unloadWaypoint;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_waypoint_to_unload")
    private OrderWaypoint unloadWaypoint;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_from_order_FK")
    private DeliveryOrder orderForThisCargo;

    public Cargo() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public DeliveryOrder getOrderForThisCargo() {
        return orderForThisCargo;
    }

    public void setOrderForThisCargo(DeliveryOrder orderForThisCargo) {
        this.orderForThisCargo = orderForThisCargo;
    }

}
