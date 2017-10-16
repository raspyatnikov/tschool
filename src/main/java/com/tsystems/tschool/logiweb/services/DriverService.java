package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.Truck;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface DriverService {

    List<Driver> findAllDrivers();

    void editDriver(Driver editedDriver);

    int addDriver(Driver newDriver);

    void removeDriver(int driverId);

    Driver findDriverById(int id);

    Driver getCoDriver(Driver driver, Truck truck);

    Set<Driver> findUnassignedToTrucksDriversByMaxWorkingHoursAndCity(
            int cityId, float deliveryTime);

    void assignDriverToTruck(int driverId, int truckId);

    @Transactional
    Driver findDriverByEmployeeId(int employeeId);
}
