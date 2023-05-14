package com.gy.rentACar.business.rules;

import com.gy.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.core.exceptions.BusinessException;
import com.gy.rentACar.entities.Enums.State;
import com.gy.rentACar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;
    public void checkIfMaintenanceExists(int id){
        if(!repository.existsById(id))
            throw new BusinessException(Messages.Maintenance.NotExists);
    }

    public void checkIfCarIsNotUnderMaintenance(int carId) {
        if(!repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new BusinessException(Messages.Maintenance.CarNotExists);
        }
    }

    public void checkIfCarUnderMaintenance(int carId) {
        if(repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new BusinessException(Messages.Maintenance.CarExists);
        }
    }

//    public void checkCarAvailabilityForMaintenance(CreateMaintenanceRequest request) {
//        if(carService.getById(request.getCarId()).getState().equals(State.RENTED)){
//            throw new BusinessException(Messages.Maintenance.CarIsRented);
//        }
//    }

    public void checkCarAvailabilityForMaintenance(State state) {
        if(state.equals(State.RENTED)){
            throw new BusinessException(Messages.Maintenance.CarIsRented);
        }
    }
}
