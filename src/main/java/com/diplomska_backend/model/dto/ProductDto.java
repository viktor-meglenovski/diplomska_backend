package com.diplomska_backend.model.dto;

import com.diplomska_backend.model.entities.CurrentPrice;
import com.diplomska_backend.model.entities.PastPrice;
import com.diplomska_backend.model.entities.Prediction;
import com.diplomska_backend.model.entities.ProductCluster;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    private Long id;
    private String name;
    private String link;
    private String image;
    private Stores store;
    private Category category;

    @JsonIgnoreProperties(value = {"product"})
    private List<PastPrice> pastPrices;

    @JsonIgnoreProperties(value = {"product"})
    private CurrentPrice currentPrice;

    @JsonIgnoreProperties(value = {"product"})
    private Prediction latestPrediction;
}
