package com.diplomska_backend.service;

import com.diplomska_backend.model.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
}
