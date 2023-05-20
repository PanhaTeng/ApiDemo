package com.example.apidemo.repositories;

import com.example.apidemo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndIsActiveTrue(Long id);
    @Query("SELECT e FROM Order e WHERE e.isActive = true")
    List<Order> findAllWhereIsActiveTrue();
    List<Order> findAllByIsActiveTrueAndId(Long id);
    @Query("SELECT o FROM Order o WHERE o.isActive = true AND o.customer_id = :id")
    List<Order> findAllByIsActiveTrueAndCustomerId(Long id);
}
