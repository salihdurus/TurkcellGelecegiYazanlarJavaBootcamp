package com.gy.rentACar.business.abstracts;

import com.gy.rentACar.business.dto.requests.create.CreateInvoiceRequest;
import com.gy.rentACar.business.dto.requests.update.UpdateInvoiceRequest;
import com.gy.rentACar.business.dto.responses.create.CreateInvoiceResponse;
import com.gy.rentACar.business.dto.responses.get.GetAllInvoiceResponse;
import com.gy.rentACar.business.dto.responses.get.GetInvoiceResponse;
import com.gy.rentACar.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoiceResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);
    void delete(int id);
}
