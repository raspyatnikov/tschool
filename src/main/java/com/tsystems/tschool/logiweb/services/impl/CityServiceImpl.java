package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.CityDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.City;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityDao cityDao;
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);


    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    @Transactional
    public List<City> findAllCities() throws ServiceException {
        try {
            return cityDao.findAll();
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public City getCityById(int id) throws ServiceException {
        try {
            return cityDao.findById(id);
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public City getCityByName(String cityName) throws ServiceException {
        try {
            City city = cityDao.findByName(cityName);
            if (city == null) throw new ServiceException("City not found!");
            return city;
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
