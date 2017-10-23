package com.tsystems.tschool.logiweb.entities;

import com.tsystems.tschool.logiweb.entities.Statuses.OrderStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@Entity
@Table(name = "delivery_orders")
public class DeliveryOrder {

    @Id
    @GeneratedValue
    @Column(name = "order_id", unique = true, nullable = false)
    private int id;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(name="drivers_orders", joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="driver_id"))
    private Set<Driver> orderDrivers;

    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck assignedTruck;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderForThisCargo")
    private Set<Cargo> assignedCargoes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderWaypoint> orderWaypoints;

    public Set<OrderWaypoint> getOrderWaypoints() {
        return orderWaypoints;
    }

    public void setOrderWaypoints(Set<OrderWaypoint> orderWaypoints) {
        this.orderWaypoints = orderWaypoints;
    }

    public DeliveryOrder() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Truck getAssignedTruck() {
        return assignedTruck;
    }

    public void setAssignedTruck(Truck assignedTruck) {
        this.assignedTruck = assignedTruck;
    }

    public Set<Cargo> getAssignedCargoes() {
        return assignedCargoes;
    }

    public void setAssignedCargoes(Set<Cargo> assignedCargoes) {
        for (Cargo cargo : assignedCargoes) {
            cargo.setOrderForThisCargo(this);
        }
        this.assignedCargoes = assignedCargoes;
    }

    public Set<Driver> getOrderDrivers() {
        return orderDrivers;
    }

    public void setOrderDrivers(Set<Driver> orderDrivers) {
        this.orderDrivers = orderDrivers;
    }
}
