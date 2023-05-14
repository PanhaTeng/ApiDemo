package com.example.apidemo.dtos;

import com.example.apidemo.models.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoResponse {
    private Long id;
    private String name;
    private Customer customer;
    private Boolean isActive;
}
