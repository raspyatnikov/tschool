package com.tsystems.tschool.logiweb.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_waypoint")
public class OrderWaypoint implements Comparable<OrderWaypoint>{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "waypoint_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private DeliveryOrder order;

    @Column(name = "seq_number", nullable = false)
    private int sequenceNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "loadWaypoint")
    private Set<Cargo> loadCargoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public DeliveryOrder getOrder() {
        return order;
    }

    public void setOrder(DeliveryOrder order) {
        this.order = order;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Set<Cargo> getLoadCargoes() {
        return loadCargoes;
    }

    public void setLoadCargoes(Set<Cargo> loadCargoes) {
        this.loadCargoes = loadCargoes;
    }

    public Set<Cargo> getUnloadCargoes() {
        return unloadCargoes;
    }

    public void setUnloadCargoes(Set<Cargo> unloadCargoes) {
        this.unloadCargoes = unloadCargoes;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "unloadWaypoint")
    private Set<Cargo> unloadCargoes;

    @Override
    public int compareTo(OrderWaypoint o) {
        return Integer.compare(sequenceNumber, o.getSequenceNumber());
     }
}
