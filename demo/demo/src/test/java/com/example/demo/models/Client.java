package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phone;

    @ManyToOne
    private Car car; // Автомобиль клиента
}