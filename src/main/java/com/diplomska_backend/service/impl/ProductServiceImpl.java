package com.diplomska_backend.service.impl;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.dto.ProductClusterDto;
import com.diplomska_backend.model.dto.ProductDto;
import com.diplomska_backend.model.entities.*;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.PredictionResult;
import com.diplomska_backend.model.enums.Stores;
import com.diplomska_backend.model.helpers.Constants;
import com.diplomska_backend.repository.ProductRepository;
import com.diplomska_backend.service.ProductService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        ProductDto productDto = mapProductToDto(product);
        return productDto;
    }

    @Override
    public List<ProductDto> getExpected(PredictionResult type, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, Constants.EXPECTED_PAGESIZE);
        Page<Product> products = productRepository.getExpected(type,pageable);
        List<ProductDto> productDtos = products.stream()
                .map(x->mapProductToDto(x))
//                .sorted(Comparator.comparing(x->x.getLatestPrediction().getPredictedPercentage()))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public PaginationInfo getPaginationInfo(PredictionResult type) {
        Integer numberOfResults = productRepository.getExpectedPaginationInfo(type);
        PaginationInfo paginationInfo=new PaginationInfo(numberOfResults, Constants.EXPECTED_PAGESIZE);
        return paginationInfo;
    }

    public static ProductDto mapProductToDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setLink(product.getLink());
        productDto.setImage(product.getImage());
        productDto.setStore(product.getStore());
        productDto.setCategory(product.getCategory());
        productDto.setPastPrices(product.getPastPrices());
        productDto.setCurrentPrice(product.getCurrentPrice());
        Prediction latestPrediction = product.getPredictions().stream()
                .sorted(Comparator.comparing(Prediction::getPredictedOn).reversed())
                .findFirst()
                .orElse(null);
        productDto.setLatestPrediction(latestPrediction);
        return productDto;
    }
}
