package com.gy.rentACar.business.dto.requests.create;

import com.gy.rentACar.common.constants.Messages;
import com.gy.rentACar.common.constants.Regex;
import com.gy.rentACar.common.utils.annotations.NotFutureYear;
import com.gy.rentACar.entities.Model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    @Min(0)
    private int modelId;
    @Min(1990)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate ,message = Messages.Car.PlateNotValid)
    private String plate;
    @Min(1)
    @Max(100000)
    private double dailyPrice;
}
