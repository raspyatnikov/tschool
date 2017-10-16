package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.entities.Statuses.TruckStatus;
import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class TruckController {

    private TruckService truckService;
    private CityService cityService;

    @Autowired
    public TruckController(TruckService truckService, CityService cityService) {
        this.truckService = truckService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "/allTrucks", method = RequestMethod.GET)
    public String showTrucks(Model model) {
        List<Truck> trucks = truckService.findAllTrucks();
        List<City> cities = cityService.findAllCities();
        model.addAttribute("truck", new Truck());
        model.addAttribute("trucklist", trucks);
        model.addAttribute("cityList", cities);
        model.addAttribute("statuses", Arrays.asList(TruckStatus.values()));
        return "admin/trucks/truck_list";
    }


    @RequestMapping(value = "/addTruck", method = RequestMethod.POST)
    public String submit(@RequestParam String licencePlate,@RequestParam float cargoCapacity,
                         @RequestParam int currentCity, @RequestParam String status, ModelMap model) {

        Truck truck = new Truck();
        truck.setCargoCapacity(cargoCapacity);
        truck.setLicencePlate(licencePlate);
        truck.setCurrentCity(cityService.getCityById(currentCity));
        truck.setStatus(TruckStatus.valueOf(status));
        truckService.addTruck(truck);
        return "redirect:/allTrucks";
    }


}
