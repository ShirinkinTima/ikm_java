package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "service_order", uniqueConstraints = {
        // Гарантируем, что нельзя создать два одинаковых заказа на одну дату для одной машины
        @UniqueConstraint(columnNames = {"orderDate", "vehicle_id", "service_id"})
})
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Выберите автомобиль")
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @NotNull(message = "Выберите услугу")
    @JoinColumn(name = "service_id")
    private ServiceCatalog service;

    @NotNull(message = "Дата обязательна")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @NotBlank(message = "Укажите статус")
    private String status;
}