package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.entities.User;
import com.tsystems.tschool.logiweb.services.*;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import com.tsystems.tschool.logiweb.services.dto.DriverOrderDto;
import com.tsystems.tschool.logiweb.services.dto.WaypointCargoesDto;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("driver")
public class DriverUserController {

    private DriverService driverService;
    private OrderService orderService;
    private OrderWaypointService orderWaypointService;
    private CargoService cargoService;
    private UserService userService;

    @Autowired
    public DriverUserController(DriverService driverService, OrderService orderService, OrderWaypointService orderWaypointService, CargoService cargoService, UserService userService) {
        this.driverService = driverService;
        this.orderService = orderService;
        this.orderWaypointService = orderWaypointService;
        this.cargoService = cargoService;

        this.userService = userService;
    }

    private Driver getDriverFromSession() throws ServiceException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(userDetails.getUsername()).getAttachedDriver();
    }

    @RequestMapping(value = "/myorder", method = RequestMethod.GET)
    public String showDriverDashBoard(Model model){
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findUserByEmail(userDetails.getUsername());
            model.addAttribute("usermail", user.getMail());
            if(getDriverFromSession().getCurrentOrder() == null) model.addAttribute("hasActiveOrder", 0);
            else             model.addAttribute("hasActiveOrder", 1);
            DriverOrderDto orderDto = orderService.getDriverOrderDto(user.getAttachedDriver().getId());
            model.addAttribute("orderNumber", orderDto.getOrderNumber());
            model.addAttribute("commander", orderDto.getCommanderFullName());
            model.addAttribute("truck", orderDto.getTruckLicencePlate());
            model.addAttribute("waypoints", orderDto.getOrderWaypoints());
            model.addAttribute("orderId", orderDto.getOrderId());
        }
        catch(Exception e){

            model.addAttribute("message", e.getMessage());
        }
        return "driver/driver_my_order";
    }

    @RequestMapping(value = "/waypoint/{seq_number}/{orderId}", method = RequestMethod.GET)
    public String showWaypointCargoes(@PathVariable("seq_number") int sequenceNumber, @PathVariable("orderId") int orderId, Model model) throws ServiceException {

        WaypointCargoesDto waypointCargoesDto = orderWaypointService.getWaypointDto(orderId, sequenceNumber);
        model.addAttribute("city", waypointCargoesDto.getCityName());
        model.addAttribute("loadCargoes", waypointCargoesDto.getCargoesToload());
        model.addAttribute("unloadCargoes", waypointCargoesDto.getCargoesToUnload());
        model.addAttribute("order_id", orderId);
        return "driver/waypoint_cargoes";
    }

    @RequestMapping(value = "waypoint/setCargoLoaded/{cargo_id}/{order_id}", method = RequestMethod.GET)
    public String setCargoPickedUp(@PathVariable("cargo_id") int cargoId, @PathVariable("order_id") int orderId, Model model) throws ServiceException {

        cargoService.setPickedUpStatus(cargoId);
       return "redirect:/driver/myorder";
    }

    @RequestMapping(value = "waypoint/setCargoDelivered/{cargo_id}/{order_id}", method = RequestMethod.GET)
    public String setCargoDelivered(@PathVariable("cargo_id") int cargoId, @PathVariable("order_id") int orderId, Model model) throws ServiceException {

        cargoService.setDeliveredStatus(cargoId);
        if(orderService.setStatusDeliveredForOrder(orderId)){
            model.addAttribute("message", "Order successfully completed!");
        }

        return "redirect:/driver/myorder";
    }


    @RequestMapping(value = "orderHistory", method = RequestMethod.GET)
    public String viewOrderHistory(Model model){
        try {
            Driver driver = getDriverFromSession();
            List<DeliveryOrderDto> orders = driverService.findAllOrdersForDriver(driver.getId());
            model.addAttribute("orderList", orders);
            return "driver/order_history";
        } catch (ServiceException e) {
            e.printStackTrace();
            return "";
        }
    }
}
