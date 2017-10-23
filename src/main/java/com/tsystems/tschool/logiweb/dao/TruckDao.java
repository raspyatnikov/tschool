package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Truck;


public interface TruckDao extends GenericDao<Truck>{

    Truck findByLicencePlate(String i) throws DaoException;
}
