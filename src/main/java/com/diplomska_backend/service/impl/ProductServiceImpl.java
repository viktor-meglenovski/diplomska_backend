package com.diplomska_backend.service.impl;

import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.repository.ProductRepository;
import com.diplomska_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
