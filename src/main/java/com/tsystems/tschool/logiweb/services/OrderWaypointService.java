package com.tsystems.tschool.logiweb.services;


import com.tsystems.tschool.logiweb.services.dto.OrderWaypointDto;
import com.tsystems.tschool.logiweb.services.dto.WaypointCargoesDto;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

public interface OrderWaypointService {

    WaypointCargoesDto getWaypointDto(int orderId, int seqNumber) throws ServiceException;

}
