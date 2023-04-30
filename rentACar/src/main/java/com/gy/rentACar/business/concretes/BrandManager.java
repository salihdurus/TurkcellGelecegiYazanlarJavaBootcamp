package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.BrandService;
import com.gy.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateBrandRequest;
import com.gy.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.gy.rentACar.business.dto.responses.get.GetBrandResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateBrandResponse;
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

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();

        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExistsById(id);
//        return brandRepository.findById(id).orElseThrow();
        Brand brand = repository.findById(id).get();
        return mapper.map(brand, GetBrandResponse.class);
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        checkIfBrandExistsByName(request.getName());
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        return mapper.map(repository.save(brand), CreateBrandResponse.class);
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        checkIfBrandExistsById(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        UpdateBrandResponse response = mapper.map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExistsById(id);
        repository.deleteById(id);
    }

    //Business Rules
    private void checkIfBrandExistsById(int id) {
        if (!repository.existsById(id)) throw new
                IllegalArgumentException("Böyle bir marka mevcut değil!");
    }

    private void checkIfBrandExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Böyle bir marka sistemde kayıtlı!");
        }
    }
}
