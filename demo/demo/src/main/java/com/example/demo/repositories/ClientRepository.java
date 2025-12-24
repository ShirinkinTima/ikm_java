package com.example.demo.repositories;

import com.example.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Здесь уже есть методы для поиска, удаления и сохранения клиентов
}