package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.entities.Statuses.TruckStatus;
import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.dto.TruckDTO;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TruckController {

    private TruckService truckService;
    private CityService cityService;

    @Autowired
    public TruckController(TruckService truckService, CityService cityService) {
        this.truckService = truckService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "/allTrucks", method = RequestMethod.GET)
    public String showTrucks(Model model) throws ServiceException {
        List<Truck> trucks = truckService.findAllTrucks();
        List<City> cities = cityService.findAllCities();
        model.addAttribute("truck", new Truck());
        model.addAttribute("trucklist", trucks);
        model.addAttribute("cityList", cities);
        model.addAttribute("statuses", Arrays.asList(TruckStatus.values()));
        return "admin/trucks/truck_list";
    }

    @RequestMapping(value = "/addTruck", method = RequestMethod.GET)
    public String addDriverPage(Model model) throws ServiceException {
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("statuses", TruckStatus.values());
        model.addAttribute("truck", new Truck());
        return "admin/trucks/add_truck";
    }

    @RequestMapping(value = "/addTruck", method = RequestMethod.POST)
    public String submit(@RequestParam String licencePlate, @RequestParam float cargoCapacity,
                         @RequestParam String currentCity, @RequestParam String status, ModelMap model, RedirectAttributes redirectAttributes) throws ServiceException {

        try {
            TruckDTO truck = new TruckDTO();
            truck.setCargoCapacity(cargoCapacity);
            truck.setLicencePlate(licencePlate);
            truck.setCurrentCity(currentCity);
            truck.setStatus(TruckStatus.valueOf(status));
            truckService.addTruck(truck);
            redirectAttributes.addFlashAttribute("message", "Truck " + licencePlate + " successfully added");
            return "redirect:/admin/addTruck";
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            redirectAttributes.addFlashAttribute("licencePlate", licencePlate);
            redirectAttributes.addFlashAttribute("cargoCapacity", cargoCapacity);
            return "redirect:/admin/addTruck";
        }
    }


    @RequestMapping(value = "editTruck/{id}", method = RequestMethod.GET)
    public String editDriverInfo(@PathVariable("id") int id, Model model) throws ServiceException {
        Truck truck = truckService.findTruckById(id);
        model.addAttribute("truck", truck);
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("statuses", TruckStatus.values());
        model.addAttribute("city", truck.getCurrentCity());
        model.addAttribute("status", truck.getStatus());
        return "admin/trucks/edit_truck";
    }

    @RequestMapping(value = "/updateTruck/{id}", method = RequestMethod.POST)
    public String updateDriverInfo(@PathVariable("id") int id, @RequestParam String licencePlate,
                                   @RequestParam String currentCity, @RequestParam Float cargoCapacity, @RequestParam String status, ModelMap model,
                                   RedirectAttributes redirectAttributes) throws ServiceException {

        try {
            TruckDTO truck = new TruckDTO();
            truck.setCargoCapacity(cargoCapacity);
            truck.setCurrentCity(currentCity);
            truck.setStatus(TruckStatus.valueOf(status));
            truck.setLicencePlate(licencePlate);
            truckService.editTruck(id, truck);
            redirectAttributes.addFlashAttribute("message", "Truck " + licencePlate + " info successfully updated");
            return "redirect:/admin/allTrucks";
        } catch (ServiceException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            return "redirect:/admin/editTruck/"+ id;
        }
    }

    @RequestMapping(value = "/removeTruck/{id}", method = RequestMethod.GET)
    public String deleteDriver(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            truckService.removeTruck(id);
            redirectAttributes.addFlashAttribute("message", "Truck successfully removed!");
            return "redirect:/admin/allTrucks";
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            return "redirect:/admin/removeTruck/" + id;
        }
    }
}
