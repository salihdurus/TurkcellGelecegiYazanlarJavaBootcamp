package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateBrandRequest;
import com.gy.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.gy.rentACar.business.dto.responses.get.GetBrandResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateBrandResponse;
import com.gy.rentACar.entities.Brand;

import java.util.List;

public interface BrandService {
    // CRUD Operations
    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(int id);

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(int id, UpdateBrandRequest request);

    void delete(int id);
}
