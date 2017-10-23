package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.dto.TruckDTO;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface TruckService {

    List<Truck> findAllTrucks() throws ServiceException;

    void editTruck(int id, TruckDTO editedTruckModel) throws ServiceException;

    int addTruck(TruckDTO newTruckModel) throws ServiceException;

    void removeTruck(int truckId) throws ServiceException;

    List<String> findFreeAndUnbrokenByCargoCapacityAndCity(Float weight) throws ServiceException;

    Truck findTruckById(int id) throws ServiceException;
}
