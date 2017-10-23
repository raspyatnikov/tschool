package com.tsystems.tschool.logiweb.services.dto;

import com.tsystems.tschool.logiweb.entities.Truck;
import com.tsystems.tschool.logiweb.services.CityService;
import com.tsystems.tschool.logiweb.services.exceptions.ServiceException;

public class DtoToEntityConverter {

    public Truck convertDtoToEntity(TruckDTO truckModel, CityService cityService) throws ServiceException {
        Truck truckEntity = new Truck();
        truckEntity.setLicencePlate(truckModel.getLicencePlate());
        truckEntity.setStatus(truckModel.getStatus());
        truckEntity.setCurrentCity(cityService.getCityByName(truckModel.getCurrentCity()));
        truckEntity.setCargoCapacity(truckModel.getCargoCapacity());
        return truckEntity;
    }

    public void copyDtoToEntity(TruckDTO dto, Truck entity, CityService cityService) throws ServiceException {
        entity.setLicencePlate(dto.getLicencePlate());
        entity.setStatus(dto.getStatus());
        entity.setCurrentCity(cityService.getCityByName(dto.getCurrentCity()));
        entity.setCargoCapacity(dto.getCargoCapacity());
    }

}
