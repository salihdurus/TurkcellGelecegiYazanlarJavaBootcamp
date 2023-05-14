package com.gy.rentACar.business.rules;

import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.core.exceptions.BusinessException;
import com.gy.rentACar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository repository;
    public void checkIfModelExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Model.NotExists);
        }
    }

    public void checkIfModelExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(Messages.Model.Exists);
        }
    }
}
