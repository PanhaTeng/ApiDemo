package com.example.apidemo.services.impl;

import com.example.apidemo.dtos.ProductDtoResponse;
import com.example.apidemo.models.Category;
import com.example.apidemo.models.Order;
import com.example.apidemo.models.Product;
import com.example.apidemo.repositories.CategoryRepository;
import com.example.apidemo.repositories.OrderRepository;
import com.example.apidemo.repositories.ProductRepository;
import com.example.apidemo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    @Override
    public Product findById(Long id) {
        return productRepository.findByIdAndIsActiveTrue(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAllWhereIsActiveTrue();
    }

    @Override
    public Product create(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product entity, Long id) {
        var product=productRepository.findByIdAndIsActiveTrue(id).orElse(null);
        Category category=categoryRepository.findByIdAndIsActiveTrue(product.getCategory_id()).orElse(null);
        if(product==null){
            return null;
        }
        product.setName(entity.getName());
        product.setOrder_id(entity.getOrder_id());
        product.setCategory_id(entity.getCategory_id());
        product.setIsActive(true);
        category.setIsActive(true);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long aLong) {
        var product=productRepository.findByIdAndIsActiveTrue(aLong).orElse(null);
        if(product==null){
            return;
        }
        product.setIsActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductDtoResponse findProDtoById(Long id) {
        var product=productRepository.findByIdAndIsActiveTrue(id).orElse(null);
        Category category=categoryRepository.findByIdAndIsActiveTrue(product.getCategory_id()).orElse(null);
        Order order=orderRepository.findByIdAndIsActiveTrue(product.getOrder_id()).orElse(null);
        if(product==null){
            return null;
        }
        ProductDtoResponse productDtoResponse=new ProductDtoResponse();
        productDtoResponse.setId(product.getId());
        productDtoResponse.setName(product.getName());
        productDtoResponse.setOrder(order);
        productDtoResponse.setCategory(category);
        productDtoResponse.setIsActive(product.getIsActive());
        return productDtoResponse;
    }

    @Override
    public List<ProductDtoResponse> findAllProDto() {
        var products=productRepository.findAllWhereIsActiveTrue();
        if (products.isEmpty()){
            return null;
        }
        List<ProductDtoResponse> productDtoResponse=new ArrayList<>();
        for(Product product:products){
            Order order=orderRepository.findByIdAndIsActiveTrue(product.getOrder_id()).orElse(null);
            Category category=categoryRepository.findByIdAndIsActiveTrue(product.getCategory_id()).orElse(null);
            var productDtoResponse1=new ProductDtoResponse();
            productDtoResponse1.setId(product.getId());
            productDtoResponse1.setName(product.getName());
            productDtoResponse1.setOrder(order);
            productDtoResponse1.setCategory(category);
            productDtoResponse1.setIsActive(product.getIsActive());
            productDtoResponse.add(productDtoResponse1);
            }
        return productDtoResponse;
    }
}
