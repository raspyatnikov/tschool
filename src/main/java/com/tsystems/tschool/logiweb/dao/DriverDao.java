package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.entities.Driver;

public interface DriverDao extends GenericDao<Driver>{
    Driver findByEmployeeId(int employeeId);
}
