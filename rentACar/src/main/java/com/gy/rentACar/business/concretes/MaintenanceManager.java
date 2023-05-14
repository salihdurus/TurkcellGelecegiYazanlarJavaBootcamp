package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.CarService;
import com.gy.rentACar.business.abstracts.MaintenanceService;
import com.gy.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.gy.rentACar.business.dto.responses.create.CreateMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.gy.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.gy.rentACar.business.rules.MaintenanceBusinessRules;
import com.gy.rentACar.entities.Enums.State;
import com.gy.rentACar.entities.Maintenance;
import com.gy.rentACar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final MaintenanceBusinessRules rules;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        return maintenances.stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        rules.checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        rules.checkIfCarIsNotUnderMaintenance(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkIfCarUnderMaintenance(request.getCarId());
        rules.checkCarAvailabilityForMaintenance(carService.getById(request.getCarId()).getState());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        return mapper.map(maintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        return mapper.map(maintenance, UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);
        repository.deleteById(id);
    }
    private void makeCarAvailableIfIsCompletedFalse(int id){
        int carId= repository.findById(id).get().getCar().getId();
        if(repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            carService.changeState(carId,State.AVAILABLE);
        }
    }
}
