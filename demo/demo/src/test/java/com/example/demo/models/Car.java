package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String plateNumber; // Госномер
}