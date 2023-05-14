package com.example.apidemo.dtos;

import com.example.apidemo.models.Category;
import com.example.apidemo.models.Order;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoResponse {

    private Long id;
    private String name;
    private Order order;
    private Category category;
    private Boolean isActive;
}
