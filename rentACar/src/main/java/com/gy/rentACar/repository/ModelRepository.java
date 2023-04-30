package com.gy.rentACar.repository;

import com.gy.rentACar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    boolean existsByNameIgnoreCase(String name);
}
