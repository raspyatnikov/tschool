package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.OrderDao;
import com.tsystems.tschool.logiweb.dao.OrderWaypointDao;
import com.tsystems.tschool.logiweb.dao.exceptions.DaoException;
import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.entities.OrderWaypoint;
import com.tsystems.tschool.logiweb.services.OrderWaypointService;
import com.tsystems.tschool.logiweb.services.dto.CargoDto;
import com.tsystems.tschool.logiweb.services.dto.EntityToDtoConverter;
import com.tsystems.tschool.logiweb.services.dto.OrderWaypointDto;
import com.tsystems.tschool.logiweb.services.dto.WaypointCargoesDto;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderWaypointServiceImpl implements OrderWaypointService {
    private OrderWaypointDao waypointDao;
    private OrderDao orderDao;
    private EntityToDtoConverter converter = new EntityToDtoConverter();
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);


    @Autowired
    public OrderWaypointServiceImpl(OrderWaypointDao waypointDao, OrderDao orderDao) {
        this.waypointDao = waypointDao;
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public WaypointCargoesDto getWaypointDto(int orderId, int seqNumber) throws ServiceException {
        try {
            OrderWaypoint waypoint = waypointDao.findByOrderAndSequenceNumber(orderDao.findById(orderId), seqNumber);
            if(waypoint == null) throw new ServiceException("Waypoint not found!");
            List<CargoDto> loadedCargoes = converter.convertCargoesToDto(new ArrayList<>(waypoint.getLoadCargoes()));
            List<CargoDto> unLoadedCargoes = converter.convertCargoesToDto(new ArrayList<>(waypoint.getUnloadCargoes()));
            WaypointCargoesDto waypointCargoesDto = new WaypointCargoesDto();
            waypointCargoesDto.setCargoesToload(loadedCargoes);
            waypointCargoesDto.setCargoesToUnload(unLoadedCargoes);
            waypointCargoesDto.setCityName(waypoint.getCity().getName());
            return waypointCargoesDto;
        } catch (DaoException e) {
            LOGGER.warn("Error in service layer");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
