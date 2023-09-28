package com.diplomska_backend.model.dto;

import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductClusterDto {
    private String id;
    private Category category;
    @JsonIgnoreProperties(value = {"productCluster"})
    private List<ProductDto> products;
    private Integer numberOfResults;
}
