package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Master {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @ManyToOne
    private Position position; // Ссылка на должность
}