package com.gy.rentACar.business.rules;

import com.gy.rentACar.entities.Enums.State;
import com.gy.rentACar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    public void checkIfRentalExists(int id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Kiralama bilgisine ulaşılamadı !");
    }
    public void checkIfCarAvailable(State state) {
        if (!state.equals(State.AVAILABLE))
            throw new RuntimeException("Araç müsait değil.");
    }
}
