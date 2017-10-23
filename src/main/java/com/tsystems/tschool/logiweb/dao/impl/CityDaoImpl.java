package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.CityDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.City;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class CityDaoImpl extends GenericDaoImpl<City> implements CityDao {
    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public City findByName(String cityName) throws DaoException{
        try {
            return em.createNamedQuery("City.findCityByName", City.class)
                    .setParameter("cityName", cityName)
                    .getSingleResult();
        }
            catch (NoResultException e){
                return null;
        } catch (Exception e) {
            LOGGER.warn("Error in dao layer", e);
            throw new DaoException(e);
        }
    }
}
