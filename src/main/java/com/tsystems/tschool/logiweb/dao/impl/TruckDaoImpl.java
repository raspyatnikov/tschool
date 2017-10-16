package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.TruckDao;
import com.tsystems.tschool.logiweb.entities.Truck;
import org.springframework.stereotype.Repository;

@Repository
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {

    @Override
    public Truck findByLicencePlate(String truckLicencePlate) {
        return em.createNamedQuery("Truck.findTruckByLicencePlate", Truck.class)
                .setParameter("licencePlate", truckLicencePlate)
                .getSingleResult();
    }
}
