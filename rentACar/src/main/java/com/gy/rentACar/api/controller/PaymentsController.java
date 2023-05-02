package com.gy.rentACar.api.controller;

import com.gy.rentACar.business.abstracts.PaymentService;
import com.gy.rentACar.business.dto.requests.create.CreatePaymentRequest;
import com.gy.rentACar.business.dto.requests.create.CreateRentalRequest;
import com.gy.rentACar.business.dto.requests.update.UpdatePaymentRequest;
import com.gy.rentACar.business.dto.responses.create.CreatePaymentResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllPaymentsResponse;
import com.gy.rentACar.business.dto.responses.get.GetPaymentResponse;
import com.gy.rentACar.business.dto.responses.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id,@Valid @RequestBody UpdatePaymentRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
