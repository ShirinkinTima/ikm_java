package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity @Data
public class WorkInOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Нужно выбрать заказ")
    @ManyToOne
    private Order order;

    @NotNull(message = "Нужно выбрать услугу")
    @ManyToOne
    private ServiceEntity service;

    @NotNull(message = "Укажите количество")
    @Min(value = 1, message = "Количество не может быть меньше 1")
    private Integer quantity;

    @NotNull(message = "Укажите цену")
    @Positive(message = "Цена должна быть больше 0")
    private Double pricePerUnit;

    @NotNull(message = "Укажите дату")
    private LocalDate priceDate;
}