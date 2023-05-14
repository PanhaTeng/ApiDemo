package com.example.apidemo.repositories;

import com.example.apidemo.models.Customer;
import com.example.apidemo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByIdAndIsActiveTrue(Long id);
    @Query("SELECT e FROM Customer e WHERE e.isActive = true")
    List<Customer> findAllWhereIsActiveTrue();

}
