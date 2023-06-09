package com.gy.rentACar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
    private int carId;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDateTime startDate;
}
