package com.diplomska_backend.service;

import com.diplomska_backend.model.dto.ProductDto;
import com.diplomska_backend.model.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    ProductDto getById(Long id);
    List<ProductDto> getExpectedUps();
    List<ProductDto> getExpectedDowns();

}
