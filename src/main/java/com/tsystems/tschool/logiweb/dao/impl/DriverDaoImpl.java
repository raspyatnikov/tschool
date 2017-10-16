package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.DriverDao;
import com.tsystems.tschool.logiweb.entities.Driver;
import org.springframework.stereotype.Repository;

@Repository
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao{
    @Override
    public Driver findByEmployeeId(int employeeId) {
        return em.createNamedQuery("Driver.findDriverByEmplyeeId", Driver.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }
}
