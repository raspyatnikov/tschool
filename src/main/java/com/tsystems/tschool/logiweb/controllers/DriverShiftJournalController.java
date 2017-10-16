package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.DriverShiftJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping(value = "/driver")
public class DriverShiftJournalController {

    private final DriverShiftJournalService shiftJournalService;
    private final
    DriverService driverService;

    @Autowired
    public DriverShiftJournalController(DriverShiftJournalService driverShiftJournalService, DriverService driverService) {
        this.shiftJournalService = driverShiftJournalService;
        this.driverService = driverService;
    }


    @RequestMapping(value = "/shifts", method = RequestMethod.GET)
    public String showDriverDashBoard(Model model){
        List<DriverShiftJournal> journalList = shiftJournalService.findAllRecords();
        List<String > begins = new ArrayList<>();
        List<String> ends = new ArrayList<>();
        journalList.forEach(shift -> {
            begins.add(new SimpleDateFormat("yyyy-MM-dd").format(shift.getShiftBeggined()));
            ends.add(new SimpleDateFormat("yyyy-MM-dd").format(shift.getShiftEnded()));
        });
        model.addAttribute("shift_records_begin", begins);
        model.addAttribute("shift_record_end", ends);
        model.addAttribute("shift_record", new DriverShiftJournal());
        return "shift_journal";
    }

    @RequestMapping(value = "/addShift", method = RequestMethod.POST)
    public String addNewShift(@RequestParam String shiftBeggined,@RequestParam String shiftEnded, Model model){
        DriverShiftJournal shiftJournal = new DriverShiftJournal();
        Driver driver = driverService.findDriverById(1);
        shiftJournal.setDriverForThisRecord(driver);
        Date dateBeginned = null;
        Date dateEnded = null;
        try {
           dateBeginned = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(shiftBeggined);
           dateEnded = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(shiftEnded);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        shiftJournal.setShiftBeggined(dateBeginned);
        shiftJournal.setShiftEnded(dateEnded);
        int i = shiftJournalService.addDriverShiftJournal(shiftJournal);
        return "redirect:/shifts";
    }

}
