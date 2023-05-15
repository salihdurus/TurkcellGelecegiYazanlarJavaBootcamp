package com.gy.rentACar.api.controller;

import com.gy.rentACar.business.abstracts.BrandService;
import com.gy.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateBrandRequest;
import com.gy.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.gy.rentACar.business.dto.responses.get.GetBrandResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateBrandResponse;
import com.gy.rentACar.entities.Brand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private final BrandService service;

    @GetMapping()
    public List<GetAllBrandsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
