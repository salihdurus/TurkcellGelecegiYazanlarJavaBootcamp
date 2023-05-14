package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.PaymentService;
import com.gy.rentACar.business.abstracts.PosService;
import com.gy.rentACar.business.dto.requests.create.CreatePaymentRequest;
import com.gy.rentACar.business.dto.requests.update.UpdatePaymentRequest;
import com.gy.rentACar.business.dto.responses.create.CreatePaymentResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllPaymentsResponse;
import com.gy.rentACar.business.dto.responses.get.GetPaymentResponse;
import com.gy.rentACar.business.dto.responses.update.UpdatePaymentResponse;
import com.gy.rentACar.business.rules.PaymentBusinessRules;
import com.gy.rentACar.common.dto.CreateRentalPaymentRequest;
import com.gy.rentACar.entities.Payment;
import com.gy.rentACar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        return payments
                .stream().map(payment -> mapper.map(payment, GetAllPaymentsResponse.class)).toList();
    }

    @Override
    public GetPaymentResponse getById(int id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        return mapper.map(payment, GetPaymentResponse.class);
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        return mapper.map(payment, CreatePaymentResponse.class);
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        return mapper.map(payment, UpdatePaymentResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
        posService.pay(); // fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }


}
