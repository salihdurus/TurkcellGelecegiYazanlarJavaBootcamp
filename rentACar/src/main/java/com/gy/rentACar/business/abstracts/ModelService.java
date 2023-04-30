package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreateModelRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.gy.rentACar.business.dto.responses.create.CreateModelResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.gy.rentACar.business.dto.responses.get.GetModelResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(int id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(int id, UpdateModelRequest request);
    void delete(int id);
}
