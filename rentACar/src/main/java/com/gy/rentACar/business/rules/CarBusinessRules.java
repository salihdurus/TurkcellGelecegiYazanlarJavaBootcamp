package com.gy.rentACar.business.rules;

import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.core.exceptions.BusinessException;
import com.gy.rentACar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    private final CarRepository repository;
    public void checkIfCarExists(int id) {
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Car.NotExists);
        }
    }
    public void checkIfCarExistsByPlate(String plate){
        if(repository.existsByPlate(plate)){
            throw new BusinessException(Messages.Car.PlateExists);
        }
    }
}
