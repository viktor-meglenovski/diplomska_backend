package com.diplomska_backend.service;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.dto.ProductClusterDto;
import com.diplomska_backend.model.entities.ProductCluster;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductClusterService {
    List<ProductClusterDto> getAll(Integer pageNumber);
    List<ProductClusterDto> getAllContainingProductNameLike(String name);
    List<ProductClusterDto> getAllByCategory(Category category);
    List<ProductClusterDto> getAllContainingProductFromStore(Stores store);
    List<ProductClusterDto> filterProductClusters(String name, Category category, Stores store, Long lowerPrice, Long upperPrice, Integer pageNumber);
    PaginationInfo getPaginationInfo(String name, Category category, Stores store, Long lowerPrice, Long upperPrice);
    ProductClusterDto getById(String id);
}
