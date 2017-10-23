package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.TruckDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Statuses.TruckStatus;
import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.dto.DtoToEntityConverter;
import com.tsystems.tschool.logiweb.services.dto.TruckDTO;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {

    private TruckDao truckDao;
    private CityService cityService;
    private DtoToEntityConverter converter = new DtoToEntityConverter();
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);


    @Autowired
    public TruckServiceImpl(TruckDao truckDao, CityService cityService) {
        this.truckDao = truckDao;
        this.cityService = cityService;
    }

    @Override
    @Transactional
    public List<Truck> findAllTrucks() throws ServiceException {

        try {
            return truckDao.findAll();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void editTruck(int id, TruckDTO truck) throws ServiceException {
        try {
            Truck truckEntity = this.findTruckById(id);
            converter.copyDtoToEntity(truck, truckEntity, cityService);
            validateTruck(truckEntity);
            truckDao.update(truckEntity);
            LOGGER.info("Truck with id " + truckEntity.getId() + " updated");
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public int addTruck(TruckDTO truck) throws ServiceException {
        try {
            if(truckDao.findByLicencePlate(truck.getLicencePlate()) != null) throw new ServiceException("Truck with Licence plate " + truck.getLicencePlate() + " already exist!");
            Truck truckEntity = converter.convertDtoToEntity(truck, cityService);
            validateTruck(truckEntity);
            truckDao.create(truckEntity);
            LOGGER.info("Truck with id " + truckEntity.getId() + " created");
            return truckEntity.getId();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void validateTruck(Truck truck) throws ServiceException {
        if(truck == null) throw new ServiceException("Cannot add empty truck model!");
        if(truck.getCargoCapacity() < 1) throw new ServiceException("Truck`s cargo capacity must be above 0!");
        if(!truck.getLicencePlate().matches("[A-Z]{2}\\d{5}")) throw new ServiceException("Truck`s licence plate must be like AA00000!");
        if(truck.getStatus() == null || truck.getCurrentCity() == null) throw new ServiceException("Truck must have status and city!");
    }
    @Transactional
    @Override
    public void removeTruck(int truckId) throws ServiceException {
        try {
            if(!this.findTruckById(truckId).getDrivers().isEmpty()) throw new ServiceException("Truck  has active order and cannot be removed!");
            truckDao.delete(this.findTruckById(truckId));
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public List<String> findFreeAndUnbrokenByCargoCapacityAndCity(Float weight) throws ServiceException {
        try {
            if(weight < 0) throw new ServiceException("Error in calculating total weight!");
            List<String> result = new ArrayList<>();
            for(Truck truck : this.findAllTrucks())
                if (truck.getCargoCapacity() >= weight && truck.getStatus().equals(TruckStatus.OK) && truck.getDrivers().isEmpty()) result.add(truck.getLicencePlate());
            return result;
        } catch (ServiceException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public Truck findTruckById(int id) throws ServiceException {

        try {
            return truckDao.findById(id);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
