package com.tsystems.tschool.logiweb.controllers;

import com.tsystems.tschool.logiweb.entities.Driver;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.DriverService;
import com.tsystems.tschool.logiweb.services.OrderService;
import com.tsystems.tschool.logiweb.services.TruckService;
import com.tsystems.tschool.logiweb.services.dto.CargoDto;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import com.tsystems.tschool.logiweb.services.dto.ShortOrderDto;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String newOrder(Model model) throws ServiceException {
        model.addAttribute("cityList",cityService.findAllCities());
        return "admin/orders/order_create";
    }

    @RequestMapping(value = "allOrders", method = RequestMethod.GET)
    public String ordersList(Model model) throws ServiceException {
        List<DeliveryOrderDto> orders = orderService.getAllOrdersDto();
        model.addAttribute("orderList", orders);
        return "admin/orders/order_list";
    }

    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public String createOrder(@RequestParam("json") String json, ModelMap model) {
        try {
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
            for (int i = 0; i < waypoint.length(); i++) {
                String waypointCityId = waypoint.getJSONObject(i).getString("city_id");
                waypoints.add(waypointCityId);
            }
            for (int i = 0; i < cargoes.length(); i++) {
                String cargoWeight = cargoes.getJSONObject(i).getString("cargo_weight");
                String cargoTitle = cargoes.getJSONObject(i).getString("cargo_title");
                cargo_load_city.add(cargoes.getJSONObject(i).getString("cargo_origin_city"));
                cargo_unload_city.add(cargoes.getJSONObject(i).getString("cargo_destination_city"));
                weights.add(cargoWeight);
                titles.add(cargoTitle);
            }

            orderService.addOrder(orderNumber, truckLicencePlate, driverEmployeeId, waypoints, weights, titles, cargo_load_city, cargo_unload_city);
        }catch (Exception e){
            model.addAttribute("exception", "Warning!!!");
            return "message";
        }
        model.addAttribute("message", "The order has been successfully created!");
            return "message";
    }

    @RequestMapping(value="/getSuitableTrucks", method = RequestMethod.GET)
    public @ResponseBody
    AjaxResponseBody getTrucks(@RequestParam(value = "json") String json) {

        AjaxResponseBody responseBody = new AjaxResponseBody();
        try {
            JSONObject object = new JSONObject(json);
            DeliveryOrderDto order = new DeliveryOrderDto();
            JSONArray waypoint = object.getJSONArray("waypoints");
            JSONArray cargoes = object.getJSONArray("cargoes");
            List<CargoDto> cargoDtos = new ArrayList<>();
            List<String> waypoints = new ArrayList<>();
            for (int i = 0; i < waypoint.length(); i++) {
                waypoints.add(waypoint.getJSONObject(i).getString("city_id"));
            }

            for (int i = 0; i < cargoes.length(); i++) {
                CargoDto cargoDto = new CargoDto();
                cargoDto.setWeight(Float.parseFloat(cargoes.getJSONObject(i).getString("cargo_weight")));
                cargoDto.setCityToLoadName(cargoes.getJSONObject(i).getString("cargo_origin_city"));
                cargoDto.setCityToUnLoadName(cargoes.getJSONObject(i).getString("cargo_destination_city"));
                cargoDtos.add(cargoDto);
            }

            Float maxWeight = orderService.calculateMaxLoadWeight(new ShortOrderDto(waypoints, cargoDtos));
            responseBody.setResult(truckService.findFreeAndUnbrokenByCargoCapacityAndCity(maxWeight));
        }
        catch (Exception e){
            responseBody.setCode("400");
            responseBody.setMsg("Error!");
            return responseBody;
        }

        return responseBody;
    }

    @RequestMapping(value="/getSuitableDrivers", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getDrivers(@RequestParam(value = "route_length") Float routeLength,@RequestParam(value = "city") String startCity ) throws ServiceException {
        List<String> driversList = new ArrayList<>();
        for(Driver driver : driverService.findUnassignedToTrucksDriversByMaxWorkingHoursAndCity(cityService.getCityByName(startCity).getId(), routeLength))
            driversList.add(driver.getName() + " " + driver.getSurname() +"(" +driver.getEmployeeId() + ")");

        return driversList;
    }
}
