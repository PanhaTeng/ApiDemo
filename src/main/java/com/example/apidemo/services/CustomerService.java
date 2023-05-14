package com.example.apidemo.services;

import com.example.apidemo.dtos.CustomerDtoResponse;
import com.example.apidemo.dtos.OrderDtoResponse;
import com.example.apidemo.models.Customer;


import java.util.List;

public interface CustomerService extends BaseService<Long, Customer>{
    public CustomerDtoResponse findCusDtoById(Long id);
    public List<CustomerDtoResponse> findAllCusDto();
}
