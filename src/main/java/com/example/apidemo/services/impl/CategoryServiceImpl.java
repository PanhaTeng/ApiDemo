package com.example.apidemo.services.impl;

import com.example.apidemo.models.Category;
import com.example.apidemo.repositories.CategoryRepository;
import com.example.apidemo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category findById(Long id) {
        return categoryRepository.findByIdAndIsActiveTrue(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllWhereIsActiveTrue();
    }

    @Override
    public Category create(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Category update(Category entity, Long id) {
        var category = categoryRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if(category == null){
            return null;
        }
        category.setName(entity.getName());
        category.setIsActive(true);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        var category= categoryRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if(category == null){
            return ;
        }
        category.setIsActive(false);
        categoryRepository.save(category);

    }
}
