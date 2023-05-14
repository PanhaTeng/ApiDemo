package com.example.apidemo.services.impl;

import com.example.apidemo.dtos.CustomerDtoResponse;
import com.example.apidemo.models.Customer;
import com.example.apidemo.models.Order;
import com.example.apidemo.repositories.CustomerRepository;
import com.example.apidemo.repositories.OrderRepository;
import com.example.apidemo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    @Override
    public Customer findById(Long aLong) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer create(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public Customer update(Customer entity, Long id) {
        var customer=customerRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if(customer==null){
            return null;
        }
        List<Order> orders=orderRepository.findAllByIsActiveTrueAndId(customer.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setGender(entity.getGender());
        customer.setIsActive(true);
        customer.setOrderId(entity.getOrderId());
        for(Order order:orders){
            order.setIsActive(true);
            order.setId(entity.getOrderId());
            orderRepository.save(order);
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        var customer=customerRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if(customer==null){
            return;
        }
        customer.setIsActive(false);
        customerRepository.save(customer);
    }

    @Override
    public CustomerDtoResponse findCusDtoById(Long id) {
        var customer=customerRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if (customer==null){
            return null;
        }
        CustomerDtoResponse customerDtoResponse=new CustomerDtoResponse();
        List<Order> orders=orderRepository.findAllByIsActiveTrueAndId(customer.getId());
        customerDtoResponse.setId(customer.getId());
        customerDtoResponse.setName(customer.getName());
        customerDtoResponse.setIsActive(customer.getIsActive());
        customerDtoResponse.setGender(customer.getGender());
        customerDtoResponse.setEmail(customer.getEmail());
        customerDtoResponse.setIsActive(customer.getIsActive());
        customerDtoResponse.setOrders(orders);
        return customerDtoResponse;
    }

    @Override
    public List<CustomerDtoResponse> findAllCusDto() {
        List<Customer> customers=customerRepository.findAllWhereIsActiveTrue();
        if (customers.isEmpty()){
            return null;
        }
        List<CustomerDtoResponse> customerDtoResponses=new ArrayList<>();
        for(Customer customer:customers){
            List<Order> orders=orderRepository.findAllByIsActiveTrueAndCustomerId(customer.getId());
            var customerDtoResponse=new CustomerDtoResponse();
            customerDtoResponse.setId(customer.getId());
            customerDtoResponse.setName(customer.getName());
            customerDtoResponse.setIsActive(customer.getIsActive());
            customerDtoResponse.setGender(customer.getGender());
            customerDtoResponse.setEmail(customer.getEmail());
            customerDtoResponse.setIsActive(customer.getIsActive());
            customerDtoResponse.setOrders(orders);
            customerDtoResponses.add(customerDtoResponse);
        }
        return customerDtoResponses;
    }
}
