package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.CityDao;
import com.tsystems.tschool.logiweb.entities.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl extends GenericDaoImpl<City> implements CityDao {
    @Override
    public City findByName(String cityName) {
        return em.createNamedQuery("City.findCityByName", City.class)
                .setParameter("cityName", cityName)
                .getSingleResult();
    }
}
