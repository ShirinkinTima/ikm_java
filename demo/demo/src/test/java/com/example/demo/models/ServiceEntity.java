package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class ServiceEntity { // Названо ServiceEntity, чтобы не путать с Service в Spring
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;      // Название услуги
    private Double normTime;  // Норматив времени
}