package com.gy.rentACar.business.dto.requests.create;

import com.gy.rentACar.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    private int modelId;
    private int modelYear;
    private String plate;
    private double dailyPrice;
}
