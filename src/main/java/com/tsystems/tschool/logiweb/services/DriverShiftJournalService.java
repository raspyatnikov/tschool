package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.dao.DriverShiftJournalDao;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverShiftJournalService {

    int addDriverShiftJournal(DriverShiftJournal shiftJournal);

    List<DriverShiftJournal> findAllRecords();
}
