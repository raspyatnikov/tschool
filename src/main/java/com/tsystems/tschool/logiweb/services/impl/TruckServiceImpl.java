package com.tsystems.tschool.logiweb.services.impl;

import com.tsystems.tschool.logiweb.dao.OrderDao;
import com.tsystems.tschool.logiweb.dao.TruckDao;
import com.tsystems.tschool.logiweb.entities.DeliveryOrder;
import com.tsystems.tschool.logiweb.entities.Statuses.TruckStatus;
import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {

    private TruckDao truckDao;
    private final OrderDao orderDao;

    @Autowired
    public TruckServiceImpl(TruckDao truckDao, OrderDao orderDao) {
        this.truckDao = truckDao;
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public List<Truck> findAllTrucks() {
        return truckDao.findAll();
    }

    @Transactional
    @Override
    public void editTruck(Truck editedTruckModel) {

    }

    @Transactional
    @Override
    public int addTruck(Truck newTruckModel) {
        truckDao.create(newTruckModel);
        return newTruckModel.getId();
    }

    @Transactional
    @Override
    public void removeTruck(int truckId) {

    }

    @Transactional
    @Override
    public List<String> findFreeAndUnbrokenByCargoCapacityAndCity(Float weight){
        List<String> result = new ArrayList<>();
        for(Truck truck : this.findAllTrucks())
            if (truck.getCargoCapacity() >= weight && truck.getStatus().equals(TruckStatus.OK) && truck.getAssignedDeliveryOrder() == null) result.add(truck.getLicencePlate());
        return result;
    }

    @Override
    @Transactional
    public void assignOrderToTruck(int truckId, int orderId){
        Truck truck = truckDao.findById(truckId);
        if(truck == null) {
//            throw new ServiceValidationException("Truck does not exist.");
        }

        if(truck.getStatus() != TruckStatus.OK) {
//            throw new ServiceValidationException("Truck must have OK status.");
//        } else if (truck.getAssignedDeliveryOrder() != null ) {
//            throw new ServiceValidationException("Truck must not have assigned orders.");
        }

        DeliveryOrder order = orderDao.findById(orderId);

//        if(order == null) {
//            throw new ServiceValidationException("Order " + orderId + " does not exist.");
//        } else if (order.getAssignedTruck() != null) {
//            throw new ServiceValidationException("Order " + orderId + " must not be assigned to another truck.");
//        } else if (order.getAssignedCargoes() == null
//                || order.getAssignedCargoes().isEmpty()) {
//            throw new ServiceValidationException("Order " + orderId
//                    + " must have cargo.");
//        }

        truck.setAssignedDeliveryOrder(order);
        truckDao.update(truck);
    }

}
