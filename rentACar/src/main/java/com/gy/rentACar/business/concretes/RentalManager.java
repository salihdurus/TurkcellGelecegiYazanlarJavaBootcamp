package com.gy.rentACar.business.concretes;

import com.gy.rentACar.business.abstracts.CarService;
import com.gy.rentACar.business.abstracts.InvoiceService;
import com.gy.rentACar.business.abstracts.PaymentService;
import com.gy.rentACar.business.abstracts.RentalService;
import com.gy.rentACar.business.dto.requests.create.CreateInvoiceRequest;
import com.gy.rentACar.business.dto.requests.create.CreateRentalRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateRentalRequest;
import com.gy.rentACar.business.dto.responses.create.CreateRentalResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllRentalsResponse;
import com.gy.rentACar.business.dto.responses.get.GetCarResponse;
import com.gy.rentACar.business.dto.responses.get.GetRentalResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateRentalResponse;
import com.gy.rentACar.business.rules.RentalBusinessRules;
import com.gy.rentACar.common.dto.CreateRentalPaymentRequest;
import com.gy.rentACar.entities.Enums.State;
import com.gy.rentACar.entities.Rental;
import com.gy.rentACar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final RentalBusinessRules rules;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalsResponse> response = rentals.stream().map(rental -> mapper.map(rental, GetAllRentalsResponse.class)).toList();
        return response;
    }

    @Override
    public GetRentalResponse getById(int id) {
        rules.checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.checkIfCarAvailable(carService.getById(request.getCarId()).getState());
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setStartDate(LocalDateTime.now());


        //Create Payment
        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);
        repository.save(rental);
        carService.changeState(request.getCarId(), State.RENTED);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);

        //Create invoice

        // Bu şekilde kullanım daha güzel olur.
//        Car car = mapper.map(carService.getById(request.getCarId()), Car.class);
//        rental.setCar(car);
//        System.err.println(rental.getCar().getModel().getBrand().getName());
//        System.err.println(rental.getCar().getModel().getName());

        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request, response, invoiceRequest, rental);

        invoiceService.add(invoiceRequest);
        return response;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRentalExists(id);
        int carId = repository.findById(id).get().getCar().getId();
        carService.changeState(carId, State.AVAILABLE);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void createInvoiceRequest(CreateRentalRequest request, CreateRentalResponse response, CreateInvoiceRequest invoiceRequest, Rental rental) {
        GetCarResponse car = carService.getById(request.getCarId());

        invoiceRequest.setRentedAt(rental.getStartDate());
        invoiceRequest.setModelName(car.getModel().getName());
        invoiceRequest.setBrandName(car.getModel().getBrand().getName());
        invoiceRequest.setDailyPrice(request.getDailyPrice());
        invoiceRequest.setPlate(car.getPlate());
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setModelYear(car.getModelYear());
        invoiceRequest.setRentedForDays(response.getRentedForDays());
    }
}
