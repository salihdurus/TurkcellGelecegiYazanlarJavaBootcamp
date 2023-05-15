package com.gy.rentACar.repository;

import com.gy.rentACar.entities.Car;
import com.gy.rentACar.entities.Enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findAllByStateIsNot(State state);
    boolean existsByPlate(String plate);
}
