package com.diplomska_backend.model.dto;

import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductClusterDto {
    private Long id;
    private Category category;
    @JsonIgnoreProperties(value = {"productCluster"})
    private Product cheapestProduct;
}
