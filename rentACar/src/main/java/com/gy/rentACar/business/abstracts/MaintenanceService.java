package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.gy.rentACar.business.dto.responses.create.CreateMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.gy.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(int id);
    GetMaintenanceResponse returnCarFromMaintenance(int carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest updateMaintenanceRequest);
    void delete(int id);
}
