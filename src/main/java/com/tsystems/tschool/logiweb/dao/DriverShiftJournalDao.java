package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;

import java.util.Set;

public interface DriverShiftJournalDao extends GenericDao<DriverShiftJournal> {
    Set<DriverShiftJournal> findThisMonthJournalsForDrivers(
            Set<Driver> drivers) throws DaoException;

    Set<DriverShiftJournal> findThisMonthJournalsForDrivers(Driver driver) throws DaoException;
}
