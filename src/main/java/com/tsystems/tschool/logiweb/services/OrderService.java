package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;
import com.tsystems.tschool.logiweb.services.dto.DriverOrderDto;
import com.tsystems.tschool.logiweb.services.dto.ShortOrderDto;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderService {

    List<DeliveryOrder> findAllOrders() throws ServiceException;

    void editOrder(DeliveryOrder editedTruckModel);

    void removeOrder(int orderId);

    @Transactional
    DriverOrderDto getDriverOrderDto(int driverId) throws ServiceException;

    List<DeliveryOrderDto> getAllOrdersDto() throws ServiceException;

    @Transactional
    DeliveryOrderDto getOrderDto(int orderId) throws ServiceException;

    DeliveryOrder addOrder(String orderNumber, String truck, String driver, List<String> waypoints, List<String> weights, List<String> titles, List<String> cargoLoadCity, List<String> cargoUnloadCity) throws ServiceException;

    Float calculateMaxLoadWeight(ShortOrderDto shortOrderDto);

    @Transactional
    void setInProgressStatusForOrder(int orderId)
            throws ServiceException;

    @Transactional
    boolean isAllCargoesInOrderDelivered(int orderId)
            throws ServiceException;

    @Transactional
    boolean setStatusDeliveredForOrder(int orderId)
            throws ServiceException;
}
