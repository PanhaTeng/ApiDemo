package com.example.apidemo.repositories;

import com.example.apidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndIsActiveTrue(Long id);
    @Query("SELECT e FROM Product e WHERE e.isActive = true")
    List<Product> findAllWhereIsActiveTrue();

}
