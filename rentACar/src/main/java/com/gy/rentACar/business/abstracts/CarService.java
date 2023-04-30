package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreateCarRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateCarRequest;
import com.gy.rentACar.business.dto.responses.create.CreateCarResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllCarsResponse;
import com.gy.rentACar.business.dto.responses.get.GetCarResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateCarResponse;
import com.gy.rentACar.entities.Enums.State;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    void delete(int id);
    void changeState(int carId, State state);
}
