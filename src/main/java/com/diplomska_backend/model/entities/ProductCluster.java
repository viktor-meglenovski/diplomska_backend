package com.diplomska_backend.model.entities;

import com.diplomska_backend.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProductCluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @JsonIgnoreProperties(value = {"productCluster"})
    @OneToMany(mappedBy = "productCluster", fetch = FetchType.LAZY)
    private List<Product> products;

    public Product getCheapestProduct() {
        return products.stream()
                .min(Comparator.comparing(x -> x.getCurrentPrice().getPrice()))
                .orElse(null);
    }
}
