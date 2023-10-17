package com.diplomska_backend.service;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.dto.ProductDto;
import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.model.enums.PredictionResult;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    ProductDto getById(Long id);
    List<ProductDto> getExpected(PredictionResult type, Integer pageNumber);

    PaginationInfo getPaginationInfo(PredictionResult type);

}
