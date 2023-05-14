package com.example.apidemo.services;

import com.example.apidemo.dtos.OrderDtoResponse;
import com.example.apidemo.dtos.ProductDtoResponse;
import com.example.apidemo.models.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderService extends BaseService<Long, Order>{
    public OrderDtoResponse findProDtoById(Long id);
    public List<OrderDtoResponse> findAllProDto();
}
