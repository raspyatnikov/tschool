package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.entities.Driver;

import java.util.Set;

public interface DriverDao extends GenericDao<Driver>{
    Driver findByEmployeeId(int employeeId) throws DaoException;

    Set<Driver> findByCityWhereNotAssignedToTruck(City city) throws DaoException;
}
