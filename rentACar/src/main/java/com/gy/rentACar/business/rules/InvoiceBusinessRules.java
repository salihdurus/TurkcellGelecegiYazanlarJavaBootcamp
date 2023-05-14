package com.gy.rentACar.business.rules;

import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.core.exceptions.BusinessException;
import com.gy.rentACar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;
    public void checkIfInvoiceExists(int id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Invoice.NotExists);
        }
    }
}
