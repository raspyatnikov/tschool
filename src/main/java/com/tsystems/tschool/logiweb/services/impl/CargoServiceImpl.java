package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.CargoDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Cargo;
import com.tsystems.tschool.logiweb.entities.Statuses.CargoStatus;
import com.tsystems.tschool.logiweb.entities.Statuses.OrderStatus;
import com.tsystems.tschool.logiweb.services.CargoService;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    CargoDao cargoDao;
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Transactional
    @Override
    public int addCargo(Cargo newCargo) throws ServiceException {

        try {
            return cargoDao.create(newCargo).getId();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public void setPickedUpStatus(int cargoId) throws IllegalStateException, ServiceException {
        try {
            Cargo cargo = cargoDao.findById(cargoId);
            if (cargo == null) {
                throw new ServiceException("Cargo not found!");
            }

            if (cargo.getOrderForThisCargo().getStatus() != OrderStatus.IN_PROCESS) {
                throw new IllegalStateException(
                        "Order for cargo must be in 'in progress' state");
            }

            if (cargo.getStatus() != CargoStatus.WAITING_FOR_PICKUP) {
                throw new IllegalStateException(
                        "Cargo must be in 'Waiting for pickup' status");
            }

            cargo.setStatus(CargoStatus.PICKED_UP);
            cargoDao.update(cargo);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }



    @Override
    @Transactional
    public void setDeliveredStatus(int cargoId) throws IllegalStateException, ServiceException {
        try {
            Cargo cargo = cargoDao.findById(cargoId);
            if (cargo == null) {
                throw new RuntimeException();
            }

            if (cargo.getOrderForThisCargo().getStatus() != OrderStatus.IN_PROCESS) {
                throw new IllegalStateException(
                        "Order for cargo must be in 'In process' state");
            }

            if (cargo.getStatus() != CargoStatus.PICKED_UP) {
                throw new IllegalStateException(
                        "Cargo must be in 'Picked up' status");
            }

            cargo.setStatus(CargoStatus.DELIVERED);
            cargoDao.update(cargo);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
