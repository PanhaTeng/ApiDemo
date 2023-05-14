package com.example.apidemo.services;

import com.example.apidemo.dtos.ProductDtoResponse;
import com.example.apidemo.models.Product;

import java.util.List;

public interface ProductService extends BaseService<Long, Product>{
    public ProductDtoResponse findProDtoById(Long id);
    public List<ProductDtoResponse> findAllProDto();
}
