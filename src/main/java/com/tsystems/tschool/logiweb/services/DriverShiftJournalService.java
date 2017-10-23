package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.dao.DriverShiftJournalDao;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import com.tsystems.tschool.logiweb.services.dto.DriverShiftRecordDTO;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverShiftJournalService {

    int addDriverShiftJournal(DriverShiftJournal shiftJournal) throws ServiceException;

    List<DriverShiftJournal> findAllRecords() throws ServiceException;

    List<DriverShiftRecordDTO> getThisMonthShiftJournalRecordsForDriver(Driver driver) throws ServiceException;
}
