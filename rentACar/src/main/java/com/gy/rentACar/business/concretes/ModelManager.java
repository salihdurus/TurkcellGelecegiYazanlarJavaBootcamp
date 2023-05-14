package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.*;
import com.gy.rentACar.business.dto.requests.create.CreateModelRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.gy.rentACar.business.dto.responses.create.CreateModelResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.gy.rentACar.business.dto.responses.get.GetModelResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateModelResponse;
import com.gy.rentACar.business.rules.ModelBusinessRules;
import com.gy.rentACar.entities.Model;
import com.gy.rentACar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;
    private final ModelBusinessRules rules;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        return models
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();
    }

    @Override
    public GetModelResponse getById(int id) {
        rules.checkIfModelExistsById(id);
        Model model = repository.findById(id).orElseThrow();
        return mapper.map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        rules.checkIfModelExistsByName(request.getName());
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        repository.save(model);

        return mapper.map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        rules.checkIfModelExistsById(id);
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        repository.save(model);

        return mapper.map(model, UpdateModelResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfModelExistsById(id);
        repository.deleteById(id);
    }


}
