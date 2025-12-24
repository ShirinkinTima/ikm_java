package com.example.demo.repositories;

import com.example.demo.models.ServiceOrder;
import com.example.demo.models.Vehicle;
import com.example.demo.models.ServiceCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    // Ищем заказ, где совпадают только дата, машина и услуга
    @Query("SELECT o FROM ServiceOrder o WHERE o.orderDate = :date " +
            "AND o.vehicle = :vehicle AND o.service = :service")
    Optional<ServiceOrder> findSimilar(@Param("date") LocalDate date,
                                       @Param("vehicle") Vehicle vehicle,
                                       @Param("service") ServiceCatalog service);
}