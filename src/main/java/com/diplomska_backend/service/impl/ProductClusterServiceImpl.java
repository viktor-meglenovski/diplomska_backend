package com.diplomska_backend.service.impl;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.dto.ProductClusterDto;
import com.diplomska_backend.model.dto.ProductDto;
import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.model.entities.ProductCluster;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import com.diplomska_backend.model.helpers.Constants;
import com.diplomska_backend.repository.ProductClusterRepository;
import com.diplomska_backend.service.ProductClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductClusterServiceImpl implements ProductClusterService{
    private final ProductClusterRepository productClusterRepository;
    @Override
    public List<ProductClusterDto> getAll(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGESIZE);
        Page<ProductCluster> productClusters = productClusterRepository.findAll(pageable);
        return productClusters.stream()
                .map(productCluster -> mapProductClusterToDto(productCluster)).collect(Collectors.toList());
    }

    @Override
    public List<ProductClusterDto> getAllContainingProductNameLike(String name) {
        List<ProductCluster> productClusters = productClusterRepository.findAllProductClustersContainingProductNameLike(name.toLowerCase());
        return productClusters.stream()
                .map(productCluster -> mapProductClusterToDto(productCluster)).collect(Collectors.toList());
    }

    @Override
    public List<ProductClusterDto> getAllByCategory(Category category) {
        List<ProductCluster> productClusters = productClusterRepository.findAllByCategory(category);
        return productClusters.stream()
                .map(productCluster -> mapProductClusterToDto(productCluster)).collect(Collectors.toList());
    }

    @Override
    public List<ProductClusterDto> getAllContainingProductFromStore(Stores store) {
        List<ProductCluster> productClusters = productClusterRepository.findAllProductClustersContainingProductFromStore(store);
        return productClusters.stream()
                .map(productCluster -> mapProductClusterToDto(productCluster)).collect(Collectors.toList());
    }

    @Override
    public List<ProductClusterDto> filterProductClusters(String name, Category category, Stores store, Long lowerPrice, Long upperPrice, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGESIZE);
        Page<ProductCluster> filteredProductClusters = productClusterRepository.filterProductClusters(name,category,store,lowerPrice,upperPrice,pageable);
        return filteredProductClusters.stream()
                .map(productCluster -> mapProductClusterToDto(productCluster)).collect(Collectors.toList());
    }

    @Override
    public PaginationInfo getPaginationInfo(String name, Category category, Stores store, Long lowerPrice, Long upperPrice) {
        Integer numberOfFilteredResults = productClusterRepository.getPaginationInfoForFilteredClusters(name,category,store,lowerPrice,upperPrice);
        PaginationInfo paginationInfo=new PaginationInfo(numberOfFilteredResults);
        return paginationInfo;
    }

    @Override
    public ProductClusterDto getById(String id) {
        ProductCluster productCluster = productClusterRepository.findById(id).orElse(null);

        if (productCluster != null) {
            List<Product> products = productCluster.getProducts();
            products.sort(Comparator.comparingLong(p -> p.getCurrentPrice().getPrice()));
            productCluster.setProducts(products);
        }
        ProductClusterDto productClusterDto = mapProductClusterToDto(productCluster);
        return productClusterDto;
    }

    public static ProductClusterDto mapProductClusterToDto(ProductCluster productCluster){
        ProductClusterDto productClusterDto=new ProductClusterDto();
        productClusterDto.setId(productCluster.getId());
        productClusterDto.setCategory(productCluster.getCategory());
        List<ProductDto> products =productCluster.getProducts().stream()
                                    .map(product -> ProductServiceImpl.mapProductToDto(product))
                .collect(Collectors.toList());
        productClusterDto.setProducts(products);
        productClusterDto.setNumberOfResults(productCluster.getNumberOfResults());
        return productClusterDto;
    }
}
