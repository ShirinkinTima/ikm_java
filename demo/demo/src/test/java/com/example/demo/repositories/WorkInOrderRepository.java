package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkInOrderRepository extends JpaRepository<WorkInOrder, Long> {
    // Здесь при желании можно будет добавить метод для поиска работ только по ID конкретного заказа
    // List<WorkInOrder> findByOrderId(Long orderId);
}