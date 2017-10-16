package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.CityDao;
import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    @Transactional
    public List<City> findAllCities() {
        return cityDao.findAll();
    }

    @Transactional
    public City getCityById(int id){return cityDao.findById(id);}
}
