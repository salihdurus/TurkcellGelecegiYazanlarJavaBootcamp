package com.gy.rentACar.business.dto.responses.get;

import com.gy.rentACar.entities.Enums.State;
import com.gy.rentACar.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
    private Model model;
}
