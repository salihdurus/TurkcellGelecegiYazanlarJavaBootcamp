package com.gy.rentACar.api.controller;

import com.gy.rentACar.business.abstracts.RentalService;
import com.gy.rentACar.business.dto.requests.create.CreateModelRequest;
import com.gy.rentACar.business.dto.requests.create.CreateRentalRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateRentalRequest;
import com.gy.rentACar.business.dto.responses.create.CreateModelResponse;
import com.gy.rentACar.business.dto.responses.create.CreateRentalResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllRentalsResponse;
import com.gy.rentACar.business.dto.responses.get.GetRentalResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateModelResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rentals")
@AllArgsConstructor
public class RentalsController {
    private final RentalService service;

    @GetMapping
    public List<GetAllRentalsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public CreateRentalResponse add(@RequestBody CreateRentalRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@PathVariable int id, @RequestBody UpdateRentalRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
