package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.Truck;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface TruckService {

    List<Truck> findAllTrucks();

    void editTruck(Truck editedTruckModel);

    int addTruck(Truck newTruckModel);

    void removeTruck(int truckId);
    public List<String> findFreeAndUnbrokenByCargoCapacityAndCity(Float weight);

    @Transactional
    void assignOrderToTruck(int truckId, int orderId);
}
