package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.DateUtil;
import com.tsystems.tschool.logiweb.dao.DriverShiftJournalDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

@Repository
public class DriverShiftJournalDaoImpl extends GenericDaoImpl<DriverShiftJournal> implements DriverShiftJournalDao {

    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public Set<DriverShiftJournal> findThisMonthJournalsForDrivers(Set<Driver> drivers) throws DaoException{
        if(drivers == null || drivers.isEmpty()) {
            return new HashSet<DriverShiftJournal>(0);
        }

        try {
            Date firstDateOfCurrentMonth = DateUtil.getFirstDayOfCurrentMonth();
            Date firstDateOfNextMonth = DateUtil.getFirstDayOfNextMonth();

            String queryString = "SELECT shiftJournal FROM " + "DriverShiftJournal shiftJournal"
                    + " WHERE driverForThisRecord IN :drivers"
                    + " AND ( (shiftEnded BETWEEN :firstDayOfMonth AND :firstDayOfNextMonth)"
                    + " OR (shiftBeggined BETWEEN :firstDayOfMonth AND :firstDayOfNextMonth) )";

            Query query = em.createQuery(queryString, DriverShiftJournal.class)
                    .setHint("org.hibernate.cacheable", false);
            query.setParameter("drivers", drivers);
            query.setParameter("firstDayOfMonth", firstDateOfCurrentMonth);
            query.setParameter("firstDayOfNextMonth", firstDateOfNextMonth);


            @SuppressWarnings("unchecked")
            List<DriverShiftJournal> result = query.getResultList();

            return new HashSet<>(result);
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }


    @Override
    public Set<DriverShiftJournal> findThisMonthJournalsForDrivers(Driver driver) throws DaoException{
        try {
            if (driver == null) {
                return new HashSet<>();
            }

            Set<Driver> drivers = new HashSet<>();
            drivers.add(driver);
            return findThisMonthJournalsForDrivers(drivers);
        } catch (DaoException e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }
}
