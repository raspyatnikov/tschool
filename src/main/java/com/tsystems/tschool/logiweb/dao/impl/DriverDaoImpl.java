package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.DriverDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao{

    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public Driver findByEmployeeId(int employeeId) throws DaoException{
        try {
            return em.createNamedQuery("Driver.findDriverByEmplyeeId", Driver.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
        catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Set<Driver> findByCityWhereNotAssignedToTruck(City city) throws DaoException{
        try {
            Query query = em.createQuery("SELECT driver FROM "
                     + "Driver driver"
                    + " WHERE currentTruck IS NULL"
                    + " AND currentCity = :city", Driver.class);
            query.setParameter("city", city);

            @SuppressWarnings("unchecked")
            List<Driver> result = query.getResultList();

            return new HashSet<Driver>(result);
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }
}
