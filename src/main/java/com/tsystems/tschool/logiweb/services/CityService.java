package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

import java.util.List;

public interface CityService {
    List<City> findAllCities() throws ServiceException;
    City getCityById(int id) throws ServiceException;
    City getCityByName(String cityName) throws ServiceException;
}
