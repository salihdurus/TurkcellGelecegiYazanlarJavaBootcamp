package com.gy.rentACar.repository;

import com.gy.rentACar.entities.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
//    @Transactional
//    void deleteByRentalId(int rentalId);
}
