package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.CarService;
import com.gy.rentACar.business.dto.requests.create.CreateCarRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateCarRequest;
import com.gy.rentACar.business.dto.responses.create.CreateCarResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllCarsResponse;
import com.gy.rentACar.business.dto.responses.get.GetCarResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateCarResponse;
import com.gy.rentACar.business.rules.CarBusinessRules;
import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.entities.Car;
import com.gy.rentACar.entities.Enums.State;
import com.gy.rentACar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;
    private final CarBusinessRules rules;

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        List<Car> cars= filterCarsByMaintenanceState(includeMaintenance);
        List<GetAllCarsResponse> responses = cars.stream().map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        rules.checkIfCarExists(id);
        Car car =repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car,GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car=mapper.map(request,Car.class);
        car.setState(State.AVAILABLE);
        repository.save(car);
        CreateCarResponse response = mapper.map(car,CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        rules.checkIfCarExists(id);
        Car car = mapper.map(request,Car.class);
        car.setId(id);
        return mapper.map(repository.save(car),UpdateCarResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
    }


    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if(includeMaintenance){
            return repository.findAll();
        }else{
            return repository.findAllByStateIsNot(State.MAINTENANCE);
        }
    }

    @Override
    public void changeState(int carId, State state) {
        rules.checkIfCarExists(carId);
        Car car = repository.findById(carId).orElseThrow();
        car.setState(state);
        repository.save(car);
    }
}
