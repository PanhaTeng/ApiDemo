package com.example.apidemo.services;

import java.util.List;

public interface BaseService<ID,T> {
    T findById(ID id);

    List<T> findAll();

    T create(T entity);

    T update(T entity,ID id);

    void deleteById(ID id);
}
