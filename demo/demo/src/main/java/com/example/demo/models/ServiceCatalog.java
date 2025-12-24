package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "service_catalog")
public class ServiceCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Уникальное название услуги (например, нельзя дважды создать "Замена масла")
    @NotBlank(message = "Название услуги обязательно")
    @Column(unique = true)
    private String title;

    @NotNull(message = "Цена обязательна")
    @Min(value = 1, message = "Цена должна быть больше 0")
    private Double price;
}