package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreateRentalRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateRentalRequest;
import com.gy.rentACar.business.dto.responses.create.CreateRentalResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllRentalsResponse;
import com.gy.rentACar.business.dto.responses.get.GetRentalResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(int id, UpdateRentalRequest request);
    void delete(int id);
}
