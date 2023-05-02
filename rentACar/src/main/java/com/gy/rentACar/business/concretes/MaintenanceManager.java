package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.CarService;
import com.gy.rentACar.business.abstracts.MaintenanceService;
import com.gy.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.gy.rentACar.business.dto.responses.create.CreateMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.gy.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;
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

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances.stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        checkIfCarIsUnderMaintenance(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkIfCarUnderMaintenance(request);
        checkCarAvailabilityForMaintenance(request);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);
        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);
        repository.deleteById(id);
    }

    private void checkIfMaintenanceExists(int id){
        if(!repository.existsById(id))
            throw new RuntimeException("Böyle bir bakım bilgisine ulaşılamadı !");
    }

    private void checkIfCarIsUnderMaintenance(int carId) {
        if(!repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new RuntimeException("Bakımda böyle bir araç yok !");
        }
    }

    private void checkIfCarUnderMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        if(repository.existsByCarIdAndIsCompletedIsFalse(createMaintenanceRequest.getCarId())){
            throw new RuntimeException("Araç şuan bakımda !");
        }
    }

    private void checkCarAvailabilityForMaintenance(CreateMaintenanceRequest request) {
        if(carService.getById(request.getCarId()).getState().equals(State.RENTED)){
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }

    private void makeCarAvailableIfIsCompletedFalse(int id){
        int carId= repository.findById(id).get().getCar().getId();
        if(repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            carService.changeState(carId,State.AVAILABLE);
        }
    }
}
