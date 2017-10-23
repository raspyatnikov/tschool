package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.TruckDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.Truck;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {
    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public Truck findByLicencePlate(String truckLicencePlate) throws DaoException{
        try {
            return em.createNamedQuery("Truck.findTruckByLicencePlate", Truck.class)
                    .setParameter("licencePlate", truckLicencePlate)
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
