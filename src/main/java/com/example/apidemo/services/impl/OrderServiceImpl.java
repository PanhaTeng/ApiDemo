package com.example.apidemo.services.impl;

import com.example.apidemo.dtos.OrderDtoResponse;
import com.example.apidemo.dtos.ProductDtoResponse;
import com.example.apidemo.models.Category;
import com.example.apidemo.models.Customer;
import com.example.apidemo.models.Order;
import com.example.apidemo.models.Product;
import com.example.apidemo.repositories.CustomerRepository;
import com.example.apidemo.repositories.OrderRepository;
import com.example.apidemo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    @Override
    public Order findById(Long aLong) {
        return orderRepository.findById(aLong).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public Order update(Order entity, Long id) {
        var order=orderRepository.findByIdAndIsActiveTrue(id).orElse(null);
        Customer customer=customerRepository.findByIdAndIsActiveTrue(order.getCustomer_id()).orElse(null);
        order.setId(entity.getId());
        order.setName(entity.getName());
        order.setCustomer_id(entity.getCustomer_id());
        order.setIsActive(entity.getIsActive());
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long aLong) {
        var order=orderRepository.findByIdAndIsActiveTrue(aLong).orElse(null);
        if(order==null){
            return;
        }
        order.setIsActive(false);
        orderRepository.save(order);
    }

    @Override
    public OrderDtoResponse findProDtoById(Long id) {
        var order=orderRepository.findByIdAndIsActiveTrue(id).orElse(null);
        var customer=customerRepository.findByIdAndIsActiveTrue(order.getCustomer_id()).orElse(null);
        OrderDtoResponse orderDtoResponse=new OrderDtoResponse();
        orderDtoResponse.setId(order.getId());
        orderDtoResponse.setName(order.getName());
        orderDtoResponse.setCustomer(customer);
        orderDtoResponse.setIsActive(order.getIsActive());
        return orderDtoResponse;
    }

    @Override
    public List<OrderDtoResponse> findAllProDto() {
        var orders=orderRepository.findAllWhereIsActiveTrue();
        if (orders.isEmpty()){
            return null;
        }
        List<OrderDtoResponse> orderDtoResponses=new ArrayList<>();
        for(Order order:orders){
            Customer customer=customerRepository.findByIdAndIsActiveTrue(order.getCustomer_id()).orElse(null);
            var orderDtoResponse=new OrderDtoResponse();
            orderDtoResponse.setId(order.getId());
            orderDtoResponse.setName(order.getName());
            orderDtoResponse.setCustomer(customer);
            orderDtoResponse.setIsActive(order.getIsActive());
            orderDtoResponses.add(orderDtoResponse);
        }
        return orderDtoResponses;
    }
}
