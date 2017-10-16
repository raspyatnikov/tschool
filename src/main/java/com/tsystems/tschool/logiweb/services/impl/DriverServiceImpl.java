package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.DriverDao;
import com.tsystems.tschool.logiweb.dao.TruckDao;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;
    private TruckDao truckDao;

    @Autowired
    public DriverServiceImpl(DriverDao driverDao, TruckDao truckDao) {
        this.driverDao = driverDao;
        this.truckDao = truckDao;
    }

    @Override
    @Transactional
    public List<Driver> findAllDrivers() {
        return driverDao.findAll();
    }

    @Override
    @Transactional
    public void editDriver(Driver editedDriver) {
        driverDao.update(editedDriver);
    }

    @Override
    @Transactional
    public int addDriver(Driver newDriver) {
        driverDao.create(newDriver);
        return newDriver.getId();
    }

    @Override
    @Transactional
    public void removeDriver(int driverId) {

    }

    @Override
    @Transactional
    public Driver findDriverById(int id) {
        return driverDao.findById(id);
    }

    @Transactional
    public Driver getCoDriver(Driver driver, Truck truck){
        for (Driver driverFromList: truck.getDrivers())
            if(!driver.equals(driverFromList)) return driverFromList;
        return null;
    }

    @Override
    @Transactional
    public Set<Driver> findUnassignedToTrucksDriversByMaxWorkingHoursAndCity(int cityId, float deliveryTime) {
        return null;
    }

    @Override
    @Transactional
    public void assignDriverToTruck(int driverId, int truckId) {

        Driver driver = driverDao.findById(driverId);
        Truck truck = truckDao.findById(truckId);

        if(driver == null || truck == null) {
            return;
            //throw new ServiceValidationException("Driver and truck must exist.");
        }

        Set<Driver> truckCrew = truck.getDrivers();
        if (truckCrew == null) {
            truckCrew = new HashSet<>();
            truck.setDrivers(truckCrew);
        }

        if (truckCrew.size() < 2) {
            driver.setCurrentTruck(truck);
            truckCrew.add(driver);
        }
        driverDao.update(driver);

    }

    @Override
    @Transactional
    public Driver findDriverByEmployeeId(int employeeId) {

            return driverDao.findByEmployeeId(employeeId);

    }
}
