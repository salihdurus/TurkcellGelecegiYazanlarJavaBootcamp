package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.PaymentService;
import com.gy.rentACar.business.abstracts.PosService;
import com.gy.rentACar.business.dto.requests.create.CreatePaymentRequest;
import com.gy.rentACar.business.dto.requests.update.UpdatePaymentRequest;
import com.gy.rentACar.business.dto.responses.create.CreatePaymentResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllPaymentsResponse;
import com.gy.rentACar.business.dto.responses.get.GetPaymentResponse;
import com.gy.rentACar.business.dto.responses.update.UpdatePaymentResponse;
import com.gy.rentACar.core.dto.CreateRentalPaymentRequest;
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
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream().map(payment -> mapper.map(payment, GetAllPaymentsResponse.class)).toList();
        return response;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        checkIfCardExists(request);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response = mapper.map(payment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
        posService.pay(); // fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }

    private void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new RuntimeException("Kart bilgileriniz hatalı !");
        }
    }

    private void checkIfBalanceIsEnough(double price, double balance) {
        if (balance < price) {
            throw new RuntimeException("Yetersiz bakiye.");
        }
    }

    private void checkIfCardExists(CreatePaymentRequest request) {
        if(repository.existsByCardNumber(request.getCardNumber())){
            throw new RuntimeException("Kart numarası zaten kayıtlı !");
        }
    }

    private void checkIfPaymentExists(int id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Ödeme bilgisi bulunamadı !");
        }
    }
}
