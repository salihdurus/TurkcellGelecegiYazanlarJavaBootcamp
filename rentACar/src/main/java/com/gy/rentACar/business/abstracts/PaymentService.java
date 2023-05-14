package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreatePaymentRequest;
import com.gy.rentACar.business.dto.requests.update.UpdatePaymentRequest;
import com.gy.rentACar.business.dto.responses.create.CreatePaymentResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllPaymentsResponse;
import com.gy.rentACar.business.dto.responses.get.GetPaymentResponse;
import com.gy.rentACar.business.dto.responses.update.UpdatePaymentResponse;
import com.gy.rentACar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}
