package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ФИО клиента обязательно")
    private String fullName;

    // unique = true гарантирует, что в базе не будет двух клиентов с одинаковым телефоном
    @NotBlank(message = "Телефон обязателен")
    @Column(unique = true)
    private String phone;
}