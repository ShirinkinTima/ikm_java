package com.example.demo.repositories;

import com.example.demo.models.ServiceCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCatalogRepository extends JpaRepository<ServiceCatalog, Long> {
    // Управление списком услуг и цен
}