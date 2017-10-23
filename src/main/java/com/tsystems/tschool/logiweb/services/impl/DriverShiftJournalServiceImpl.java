package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.DriverShiftJournalDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import com.tsystems.tschool.logiweb.services.DriverShiftJournalService;
import com.tsystems.tschool.logiweb.services.dto.DriverShiftRecordDTO;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DriverShiftJournalServiceImpl implements DriverShiftJournalService {
    private final DriverShiftJournalDao shiftJournalDao;
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);


    @Autowired
    public DriverShiftJournalServiceImpl(DriverShiftJournalDao shiftJournalDao) {
        this.shiftJournalDao = shiftJournalDao;
    }

    @Override
    @Transactional
    public int addDriverShiftJournal(DriverShiftJournal shiftJournal) throws ServiceException {
        try {
            return shiftJournalDao.create(shiftJournal).getId();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public List<DriverShiftJournal> findAllRecords() throws ServiceException {
        try {
            return shiftJournalDao.findAll();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<DriverShiftRecordDTO> getThisMonthShiftJournalRecordsForDriver(Driver driver) throws ServiceException {
        try {
            Set<DriverShiftJournal> records = shiftJournalDao.findThisMonthJournalsForDrivers(driver);
            List<DriverShiftRecordDTO> dtoRecords = new ArrayList<>();
            for(DriverShiftJournal record: records)
                dtoRecords.add(new DriverShiftRecordDTO(
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(record.getShiftBeggined()),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(record.getShiftEnded())));
            return dtoRecords;
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
