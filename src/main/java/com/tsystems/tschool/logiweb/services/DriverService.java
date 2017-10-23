package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface DriverService {

    List<Driver> findAllDrivers() throws ServiceException;

    void editDriver(Driver editedDriver) throws ServiceException;

    int addDriver(Driver newDriver) throws ServiceException;

    void removeDriver(int driverId) throws ServiceException;

    Driver findDriverById(int id) throws ServiceException;

    Driver getCoDriver(Driver driver, Truck truck) throws ServiceException;

    Set<Driver> findUnassignedToTrucksDriversByMaxWorkingHoursAndCity(
            int cityId, float routeLength) throws ServiceException;

    @Transactional
    Driver findDriverByEmployeeId(int employeeId) throws ServiceException;

    @Transactional
    List<DeliveryOrderDto> findAllOrdersForDriver(int driverId) throws ServiceException;
}
