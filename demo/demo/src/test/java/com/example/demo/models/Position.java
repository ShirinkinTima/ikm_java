package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Position {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;     // Название должности
    private Integer grade;   // Разряд
    private Double rate;     // Ставка (руб/час)
}