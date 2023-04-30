package com.gy.rentACar.business.dto.responses.create;

import com.gy.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.gy.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.gy.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceResponse {
    private int id;
    private int carId;
    private String information;
    private boolean isCompleted;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
