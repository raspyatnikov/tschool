package com.tsystems.tschool.logiweb.services;

import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.services.dto.DeliveryOrderDto;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderService {

    List<DeliveryOrder> findAllOrders();

    void editOrder(DeliveryOrder editedTruckModel);

    void removeOrder(int orderId);

    @Transactional
    List<DeliveryOrderDto> getAllOrdersDto();

    DeliveryOrder addOrder(String orderNumber, String truck, String driver, List<String> waypoints, List<String> weights, List<String> titles, List<String> cargoLoadCity, List<String> cargoUnloadCity);
}
