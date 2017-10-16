package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.City;

import java.util.List;

public interface CityService {
    List<City> findAllCities();
    public City getCityById(int id);
}
