package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Марка и модель обязательны")
    private String model;

    // Уникальный госномер — нельзя добавить две разные машины с одним номером
    @NotBlank(message = "Госномер обязателен")
    @Column(unique = true)
    private String plateNumber;

    @ManyToOne
    @NotNull(message = "Укажите владельца")
    @JoinColumn(name = "owner_id")
    private Client owner;
}