package com.gy.rentACar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gy.rentACar.entities.Enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;

    @Enumerated(EnumType.STRING)
    private State state;

    private double dailyPrice;


    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;
}
