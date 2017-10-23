package com.tsystems.tschool.logiweb.entities;

import com.tsystems.tschool.logiweb.entities.Statuses.DriverStatus;

import javax.persistence.*;
import java.util.Set;
@NamedQuery(name = "Driver.findDriverByEmplyeeId", query = "SELECT driver FROM Driver driver WHERE driver.employeeId=:employeeId")
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue
    @Column(name = "driver_id", unique = true, nullable = false)
    private int id;

    @Column(name = "driver_employee_id_UQ", unique = true, nullable = false)
    private int employeeId;

    @OneToOne
    @JoinColumn(name = "driver_account_id")
    private User userAccount;

    @Column(name = "driver_name", nullable = false)
    private String name;

    @Column(name = "driver_surname", nullable = false)
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private DeliveryOrder currentOrder;

    @Column(name = "driver_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_current_location_city_FK", nullable = false)
    private City currentCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_current_truck_FK")
    private Truck currentTruck;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driverForThisRecord")
    private Set<DriverShiftJournal> shitsJournalRecords;

    @ManyToMany
    @JoinTable(name = "drivers_orders", joinColumns = { @JoinColumn(name = "driver_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id")} )
    private Set<DeliveryOrder> orders;

    public Driver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public Truck getCurrentTruck() {
        return currentTruck;
    }

    public void setCurrentTruck(Truck currentTruck) {
        this.currentTruck = currentTruck;
    }

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

    public DeliveryOrder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(DeliveryOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Set<DeliveryOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<DeliveryOrder> orders) {
        this.orders = orders;
    }

    public void setUserbAccount(User logiwebAccount) {
        this.userAccount = logiwebAccount;
    }

}
