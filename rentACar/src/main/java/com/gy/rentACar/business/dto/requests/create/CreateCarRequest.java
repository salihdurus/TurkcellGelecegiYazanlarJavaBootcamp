package com.gy.rentACar.business.dto.requests.create;

import com.gy.rentACar.common.constants.Regex;
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
    @NotNull
    private int modelId;

    @NotNull
    @Min(1990)
    @Max(2023)
    private int modelYear;

    @Pattern(regexp = Regex.Plate,message = "Plate number must match the pattern")
    private String plate;

    @NotNull
    @Min(1)
    @Max(100000)
    private double dailyPrice;
}
