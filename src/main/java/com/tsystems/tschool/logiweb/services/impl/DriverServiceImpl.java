package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.*;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.*;
import com.tsystems.tschool.logiweb.entities.Statuses.DriverStatus;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import com.tsystems.tschool.logiweb.services.dto.EntityToDtoConverter;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;
    private TruckDao truckDao;
    private CityDao cityDao;
    private DriverShiftJournalDao shiftJournalDao;
    private EntityToDtoConverter converter = new EntityToDtoConverter();
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);


    @Autowired
    public DriverServiceImpl(DriverDao driverDao, TruckDao truckDao, CityDao cityDao, DriverShiftJournalDao shiftJournalDao) {
        this.driverDao = driverDao;
        this.truckDao = truckDao;
        this.cityDao = cityDao;
        this.shiftJournalDao = shiftJournalDao;
    }

    @Override
    @Transactional
    public List<Driver> findAllDrivers() throws ServiceException {

        try {
            return driverDao.findAll();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void editDriver(Driver editedDriver) throws ServiceException {

        try {
            driverDao.update(editedDriver);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public int addDriver(Driver newDriver) throws ServiceException {
        try {
            driverDao.create(newDriver);
            return newDriver.getId();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void removeDriver(int driverId) throws ServiceException {

        try {
            Driver driver = driverDao.findById(driverId);
            if(driver == null) throw new ServiceException("Driver doesn`t exist!");
            if(driver.getStatus().equals(DriverStatus.DRIVING)||driver.getCurrentOrder()!=null) throw new
                    ServiceException("Driver has active order and can`t be deleted!");
            driverDao.delete(driver);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Driver findDriverById(int id) throws ServiceException {

        try {
            return driverDao.findById(id);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public Driver getCoDriver(Driver driver, Truck truck) throws ServiceException {
        Driver coDriver = null;
        for (Driver driverFromList: truck.getDrivers())
            if(!driver.equals(driverFromList)) coDriver = driverFromList;
        if(coDriver == null) throw new ServiceException("CoDriver not found!");
        return coDriver;
    }

    private float calculateMinHoursDriverShouldHave(float hoursToDeliver) {
        float diff = DateUtil.getHoursUntilEndOfMonth() - hoursToDeliver;
        if (diff < 0 && 176 + diff > 0) {
            return DateUtil.getHoursUntilEndOfMonth();
        } else {
            return 176
                    - hoursToDeliver;
        }
    }

    @Override
    @Transactional
    public Set<Driver> findUnassignedToTrucksDriversByMaxWorkingHoursAndCity(int cityId, float routeLength) throws ServiceException {

        try {
            City city = cityDao.findById(cityId);
            if (city == null) {
                throw new ServiceException("City not found!");
            }

            float workingHoursMaxLimit = calculateMinHoursDriverShouldHave(routeLength/60);
            Set<Driver> freeDriversInCity = driverDao
                    .findByCityWhereNotAssignedToTruck(city);
            Set<DriverShiftJournal> journals = shiftJournalDao
                    .findThisMonthJournalsForDrivers(freeDriversInCity);

            Map<Driver, Float> workingHoursData = sumWorkingHoursForThisMonth(journals);
            for (Driver driver : freeDriversInCity) {
                workingHoursData.putIfAbsent(driver, 0f);
            }

            filterDriversByMaxWorkingHours(workingHoursData, workingHoursMaxLimit);

            return workingHoursData.keySet();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }


    private void filterDriversByMaxWorkingHours(
            Map<Driver, Float> workingHoursToFilter, float maxWorkingHours) {

        workingHoursToFilter.entrySet().removeIf(e -> 176 - e.getValue() < maxWorkingHours);
    }


    private Map<Driver, Float> sumWorkingHoursForThisMonth(
            Collection<DriverShiftJournal> journal) {

        Map<Driver, Float> workingHoursForDrivers = new HashMap<>();

        Date firstDayOfCurrentMonth = DateUtil.getFirstDayOfCurrentMonth();
        Date firstDayOfNextMonth = DateUtil.getFirstDayOfNextMonth();

        for (DriverShiftJournal j : journal) {
            Driver driver = j.getDriverForThisRecord();
            Date shiftBeggined = j.getShiftBeggined();
            Date shiftEnded = j.getShiftEnded();


            if (shiftBeggined.getTime() < firstDayOfCurrentMonth.getTime()) {
                shiftBeggined = firstDayOfCurrentMonth;
            }
            if (shiftEnded.getTime() > firstDayOfNextMonth.getTime()) {
                shiftEnded = firstDayOfNextMonth;
            }

            float shiftDuration = DateUtil.diffInHours(shiftBeggined, shiftEnded);

            workingHoursForDrivers.merge(driver, shiftDuration, (a, b) -> a + b);
        }

        return workingHoursForDrivers;
    }

    @Override
    @Transactional
    public Driver findDriverByEmployeeId(int employeeId) throws ServiceException {

        try {
            return driverDao.findByEmployeeId(employeeId);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    @Transactional
    public List<DeliveryOrderDto> findAllOrdersForDriver(int driverId) throws ServiceException{
        Driver driver = this.findDriverById(driverId);
        if(driver == null) throw new ServiceException("Driver not found!");
        List<DeliveryOrder> orders = new ArrayList<>(driver.getOrders());
        if(orders.isEmpty()) throw new ServiceException("Order history is empty!");
        return converter.convertOrdersToDto(orders);
    }
}
