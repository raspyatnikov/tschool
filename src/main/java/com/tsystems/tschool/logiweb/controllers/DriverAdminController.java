package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.*;
import com.tsystems.tschool.logiweb.entities.Statuses.DriverStatus;
import com.tsystems.tschool.logiweb.entities.Statuses.UserRole;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.UserService;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DriverAdminController {

    private  DriverService driverService;
    private  CityService cityService;
    private UserService userService;

    @Autowired
    public DriverAdminController(DriverService driverService, CityService cityService, UserService userService) {
        this.driverService = driverService;
        this.cityService = cityService;
        this.userService = userService;
    }


    @RequestMapping(value = "/allDrivers", method = RequestMethod.GET)
    public String showDrivers(Model model, @RequestParam(required = false) String message) throws ServiceException {
        List<Driver> drivers = driverService.findAllDrivers();
        List<City> cities = cityService.findAllCities();
        model.addAttribute("driver", new Driver());
        model.addAttribute("driverList", drivers);
        model.addAttribute("cityList", cities);
        model.addAttribute("message", message);
        model.addAttribute("statuses", Arrays.asList(DriverStatus.values()));
        return "admin/drivers/drivers_list";
    }

    @RequestMapping(value = "/addDriver", method = RequestMethod.POST)
    public String submitDriver(@RequestParam int employeeId,@RequestParam String email, @RequestParam String password,
                         @RequestParam int currentCity,@RequestParam String name,@RequestParam String surname, ModelMap model) {

        try{
        Driver driver = new Driver();
        User user = new User(email, UserRole.ROLE_DRIVER, password);
        driver.setCurrentCity(cityService.getCityById(currentCity));
        driver.setName(name);
        driver.setSurname(surname);
        driver.setStatus(DriverStatus.FREE);
        driver.setEmployeeId(employeeId);
        userService.addUser(user);
        driver.setUserbAccount(user);
        driverService.addDriver(driver);
        model.addAttribute("success", "Driver successfully added!");
        return "redirect:/admin/allDrivers";
        }
        catch (Exception ex){
            model.addAttribute("error", ex.toString());
            return "admin/drivers/add_driver";
        }

    }

    @RequestMapping(value = "/addDriver", method = RequestMethod.GET)
    public String addDriverPage(Model model) throws ServiceException {
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("driver", new Driver());
        return "admin/drivers/add_driver";
    }

    @RequestMapping(value = "editDriver/{id}", method = RequestMethod.GET)
    public String editDriverInfo(@PathVariable("id") int id, Model model) throws ServiceException {
        Driver driver = driverService.findDriverById(id);
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("driver", driver);
        model.addAttribute("id",id);
        return "admin/drivers/edit_driver";
    }

    @RequestMapping(value = "/updateDriver/{id}", method = RequestMethod.POST)
    public String updateDriverInfo(@PathVariable("id") int id, @RequestParam int employeeId,
                                   @RequestParam int currentCity, @RequestParam String name, @RequestParam String surname, ModelMap model) throws ServiceException {

        Driver driver = driverService.findDriverById(id);
        driver.setCurrentCity(cityService.getCityById(currentCity));
        driver.setName(name);
        driver.setSurname(surname);
        driver.setEmployeeId(employeeId);
        driverService.editDriver(driver);
        return "redirect:/admin/allDrivers";
    }

    @RequestMapping(value = "/removeDriver/{id}", method = RequestMethod.GET)
    public String deleteDriver(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            driverService.removeDriver(id);
            redirectAttributes.addAttribute("message", "Driver successfully removed!");
            return "redirect:/admin/allDrivers";
        } catch (ServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "removeDriver/" + id;
        }
    }

}
