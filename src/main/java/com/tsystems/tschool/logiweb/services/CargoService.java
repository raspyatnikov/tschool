package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.Cargo;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

public interface CargoService {

    int addCargo(Cargo newCargo) throws ServiceException;

    @Transactional
    void setPickedUpStatus(int cargoId) throws IllegalStateException, ServiceException;

    @Transactional
    void setDeliveredStatus(int cargoId) throws IllegalStateException, ServiceException;
}
