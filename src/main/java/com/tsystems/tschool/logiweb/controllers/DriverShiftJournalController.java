package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.dao.DateUtil;
import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.DriverShiftJournal;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.DriverShiftJournalService;
import com.tsystems.tschool.logiweb.services.UserService;
import com.tsystems.tschool.logiweb.services.dto.DriverShiftRecordDTO;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/driver")
public class DriverShiftJournalController {

    private final DriverShiftJournalService shiftJournalService;
    private final
    DriverService driverService;
    private UserService userService;

    @Autowired
    public DriverShiftJournalController(DriverShiftJournalService driverShiftJournalService, DriverService driverService, UserService userService) {
        this.shiftJournalService = driverShiftJournalService;
        this.driverService = driverService;
        this.userService = userService;
    }

    private Driver getDriverFromSession() throws ServiceException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(userDetails.getUsername()).getAttachedDriver();
    }


    @RequestMapping(value = "/shifts", method = RequestMethod.GET)
    public String showDriverDashBoard(Model model) throws ServiceException {
        List<DriverShiftRecordDTO> journalList = shiftJournalService.getThisMonthShiftJournalRecordsForDriver(getDriverFromSession());
        model.addAttribute("shift_records", journalList);
        model.addAttribute("shift_record", new DriverShiftJournal());
        return "driver/shift_journal";
    }

    @RequestMapping(value = "addShift", method = RequestMethod.POST)
    public String addNewShift(@RequestParam String shiftBeggined,@RequestParam String shiftEnded, Model model,
    final RedirectAttributes redirectAttributes) {
        try {
            if(Objects.equals(shiftBeggined, "") || Objects.equals(shiftEnded, "")) throw new ServiceException("Empty data field(s)!");
            DriverShiftJournal shiftJournal = new DriverShiftJournal();
            Driver driver = driverService.findDriverById(getDriverFromSession().getId());
            if(driver == null) throw new ServiceException("Driver not found!");
            shiftJournal.setDriverForThisRecord(driver);
            Date dateBeginned  = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(shiftBeggined);
            Date dateEnded  = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(shiftEnded);
            if(DateUtil.diffInHours(dateBeginned, dateEnded) < 0 || DateUtil.diffInHours(dateBeginned, dateEnded) > 9)
                 throw new ServiceException("Shift must have length more than 0 and less than 9 hours!");
            shiftJournal.setShiftBeggined(dateBeginned);
            shiftJournal.setShiftEnded(dateEnded);
            shiftJournalService.addDriverShiftJournal(shiftJournal);

        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("exception", "Wrong date format!");
            return "redirect:/driver/shifts";
        }
        catch (ServiceException e){
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            return "redirect:/driver/shifts";
        }

        redirectAttributes.addFlashAttribute("message","Shift successfully added!");
        return "redirect:/driver/shifts";
    }

}
