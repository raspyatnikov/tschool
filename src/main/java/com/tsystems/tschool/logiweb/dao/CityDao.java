package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.entities.City;

public interface CityDao extends GenericDao<City>{

    City findByName(String s);
}
