package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.BrandService;
import com.gy.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateBrandRequest;
import com.gy.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.gy.rentACar.business.dto.responses.get.GetBrandResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateBrandResponse;
import com.gy.rentACar.business.rules.BrandBusinessRules;
import com.gy.rentACar.entities.Brand;
import com.gy.rentACar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;
    private final BrandBusinessRules rules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();

        return brands
                .stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class))
                .toList();
    }

    @Override
    public GetBrandResponse getById(int id) {
        rules.checkIfBrandExistsById(id);
//        return brandRepository.findById(id).orElseThrow();
        Brand brand = repository.findById(id).get();
        return mapper.map(brand, GetBrandResponse.class);
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        rules.checkIfBrandExistsByName(request.getName());
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        return mapper.map(repository.save(brand), CreateBrandResponse.class);
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        rules.checkIfBrandExistsById(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        return mapper.map(brand, UpdateBrandResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfBrandExistsById(id);
        repository.deleteById(id);
    }
}
