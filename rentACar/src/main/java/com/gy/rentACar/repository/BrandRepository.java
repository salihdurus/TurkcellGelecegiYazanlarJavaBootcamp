package com.gy.rentACar.repository;

import com.gy.rentACar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    //Custom quaries
    boolean existsByNameIgnoreCase(String name);
}
