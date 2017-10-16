package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.DriverShiftJournalDao;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import com.tsystems.tschool.logiweb.services.DriverShiftJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverShiftJournalServiceImpl implements DriverShiftJournalService {
    private final DriverShiftJournalDao shiftJournalDao;

    @Autowired
    public DriverShiftJournalServiceImpl(DriverShiftJournalDao shiftJournalDao) {
        this.shiftJournalDao = shiftJournalDao;
    }

    @Override
    @Transactional
    public int addDriverShiftJournal(DriverShiftJournal shiftJournal) {
        return shiftJournalDao.create(shiftJournal).getId();
    }

    @Override
    @Transactional
    public List<DriverShiftJournal> findAllRecords() {
        return shiftJournalDao.findAll();
    }
}
