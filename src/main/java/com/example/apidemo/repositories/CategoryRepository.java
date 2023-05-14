package com.example.apidemo.repositories;

import com.example.apidemo.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndIsActiveTrue(Long id);
    @Query("SELECT e FROM Category e WHERE e.isActive = true")
    List<Category> findAllWhereIsActiveTrue();
}
