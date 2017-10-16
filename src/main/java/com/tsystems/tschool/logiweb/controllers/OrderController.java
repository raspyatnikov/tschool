package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.OrderService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class OrderController {
    private final
    OrderService orderService;
    private final
    DriverService driverService;
    private final
    TruckService truckService;
    private final CityService cityService;

    @Autowired
    public OrderController(OrderService orderService, DriverService driverService, TruckService truckService, CityService cityService) {
        this.orderService = orderService;
        this.driverService = driverService;
        this.truckService = truckService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "newOrder", method = RequestMethod.GET)
    public String newOrder(Model model) {

        model.addAttribute("driverList", driverService.findAllDrivers());
        model.addAttribute("cityList",cityService.findAllCities());
        return "admin/orders/order_create";
    }

    @RequestMapping(value = "allOrders", method = RequestMethod.GET)
    public String ordersList(Model model) {
        List<DeliveryOrderDto> orders = orderService.getAllOrdersDto();
        model.addAttribute("orderList", orders);
        return "admin/orders/order_list";
    }

    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public String createOrder(@RequestParam("json") String json) {
        JSONObject object = new JSONObject(json);
        String truckLicencePlate = object.getString("truck_licennce_plate");
        String driverEmployeeId = object.getString("driver");
        String orderNumber = object.getString("order_number");
        JSONArray waypoint = object.getJSONArray("waypoints");
        JSONArray cargoes = object.getJSONArray("cargoes");
        List<String> waypoints = new ArrayList<>();
        List<String> weights = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> cargo_load_city = new ArrayList<>();
        List<String> cargo_unload_city = new ArrayList<>();
        for(int i = 0; i < waypoint.length(); i++){
            String waypointCityId = waypoint.getJSONObject(i).getString("city_id");
            waypoints.add(waypointCityId);}
        for(int i = 0; i < cargoes.length(); i++) {
            String cargoWeight = cargoes.getJSONObject(i).getString("cargo_weight");
            String cargoTitle = cargoes.getJSONObject(i).getString("cargo_title");
            cargo_load_city.add(cargoes.getJSONObject(i).getString("cargo_origin_city"));
            cargo_unload_city.add(cargoes.getJSONObject(i).getString("cargo_destination_city"));
            weights.add(cargoWeight);
            titles.add(cargoTitle);
        }

        orderService.addOrder(orderNumber, truckLicencePlate, driverEmployeeId, waypoints, weights, titles, cargo_load_city, cargo_unload_city);
            return "OK";
    }

    @RequestMapping(value="/getSuitableTrucks", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getVersionsByProjectKey(@RequestParam(value = "weight") String weight) {

        Float weight_ = Float.valueOf(weight);
        return truckService.findFreeAndUnbrokenByCargoCapacityAndCity(weight_);
    }
}
