package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.*;
import com.tsystems.tschool.logiweb.entities.Statuses.DriverStatus;
import com.tsystems.tschool.logiweb.entities.Statuses.UserRole;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DriverController {

    private  DriverService driverService;
    private  CityService cityService;
    private UserService userService;

    @Autowired
    public DriverController(DriverService driverService, CityService cityService, UserService userService) {
        this.driverService = driverService;
        this.cityService = cityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public String showDriverDashBoard(Model model){
        Driver driver = driverService.findDriverById(1);
        Truck truck = driver.getCurrentTruck();
        model.addAttribute("employeeId", driver.getEmployeeId());
        model.addAttribute("truckLicencePlate", truck.getLicencePlate());
        model.addAttribute("commander", driverService.getCoDriver(driver, truck));
        List<OrderWaypoint> list = new ArrayList<>(truck.getAssignedDeliveryOrder().getOrderWaypoints());
        Collections.sort(list);
        model.addAttribute("currentOrder", list);
        return "driver_start_page";
    }

    @RequestMapping(value = "/allDrivers", method = RequestMethod.GET)
    public String showDrivers(Model model) {
        List<Driver> drivers = driverService.findAllDrivers();
        List<City> cities = cityService.findAllCities();
        model.addAttribute("driver", new Driver());
        model.addAttribute("driverList", drivers);
        model.addAttribute("cityList", cities);
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
        model.addAttribute("success", "Driver successfully added!");}
        catch (Exception ex){
            model.addAttribute("error", ex.toString());
        }

        return "admin/drivers/add_driver";
    }

    @RequestMapping(value = "/addDriver", method = RequestMethod.GET)
    public String addDriverPage(Model model){
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("driver", new Driver());
        return "admin/drivers/add_driver";
    }

    @RequestMapping(value = "editDriver/{id}", method = RequestMethod.GET)
    public String editDriverInfo(@PathVariable("id") int id, Model model){
        Driver driver = driverService.findDriverById(id);
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("driver", driver);
        model.addAttribute("id",id);
        return "admin/drivers/edit_driver";
    }

    @RequestMapping(value = "/updateDriver/{id}", method = RequestMethod.POST)
    public String updateDriverInfo(@PathVariable("id") int id, @RequestParam int employeeId,
                                   @RequestParam int currentCity, @RequestParam String name, @RequestParam String surname, ModelMap model) {

        Driver driver = driverService.findDriverById(id);
        driver.setCurrentCity(cityService.getCityById(currentCity));
        driver.setName(name);
        driver.setSurname(surname);
        driver.setEmployeeId(employeeId);
        driverService.editDriver(driver);
        return "redirect:/admin/allDrivers";
    }
}
