package com.gy.rentACar.business.rules;

import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.core.exceptions.BusinessException;
import com.gy.rentACar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRepository repository;
    public void checkIfBrandExistsById(int id) {
        if (!repository.existsById(id)) throw new
                BusinessException(Messages.Brand.NotExists);
    }

    public void checkIfBrandExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(Messages.Brand.Exists);
        }
    }
}
