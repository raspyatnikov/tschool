package com.tsystems.tschool.logiweb.dao;

import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.entities.OrderWaypoint;

public interface OrderWaypointDao extends GenericDao<OrderWaypoint> {
    OrderWaypoint findByOrderAndSequenceNumber(DeliveryOrder order, int seqNumber) throws DaoException;
}
