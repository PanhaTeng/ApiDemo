package com.example.apidemo.dtos;

import com.example.apidemo.models.Order;
import com.example.apidemo.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoResponse {
    private Long id;
    private String name;
    private Gender gender;
    private String email;
    private List<Order> orders;
    private Boolean isActive;
}
