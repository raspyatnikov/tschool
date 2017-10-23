package com.tsystems.tschool.logiweb.dao.impl;

import com.tsystems.tschool.logiweb.dao.OrderWaypointDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.entities.OrderWaypoint;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class OrderWaypointDaoImpl extends GenericDaoImpl<OrderWaypoint> implements OrderWaypointDao {

    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public OrderWaypoint findByOrderAndSequenceNumber(DeliveryOrder order, int seqNumber) throws DaoException{
        try {
            return em.createNamedQuery("OrderWaypoint.findWaypointByOrderAndSeqNumber", OrderWaypoint.class)
                    .setParameter("order", order)
                    .setParameter("seqNumber", seqNumber)
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
