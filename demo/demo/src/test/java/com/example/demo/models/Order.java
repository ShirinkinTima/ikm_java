package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity @Table(name = "orders") @Data
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private String status;    // В работе / Завершен

    @ManyToOne
    private Client client;

    @ManyToOne
    private Master master;
}