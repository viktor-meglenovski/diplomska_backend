package com.diplomska_backend.model.entities;

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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String name;
    @Column(length = 1000)
    private String link;
    @Column(length = 1000)
    private String image;

    private String preprocessedName;

//    @JsonIgnore
//    @Column(name = "vectorized_name", columnDefinition = "jsonb")
//    private String vectorizedName;

    @Enumerated(value = EnumType.STRING)
    private Stores store;

    @Enumerated(value = EnumType.STRING)
    @Nullable
    private Category category;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    private ProductCluster productCluster;

    @JsonIgnoreProperties(value = {"product"})
    @OneToMany(mappedBy = "product")
    private List<PastPrice> pastPrices;

    @JsonIgnoreProperties(value = {"product"})
    @OneToOne
    private CurrentPrice currentPrice;

    @JsonIgnoreProperties(value = {"product"})
    @OneToMany(mappedBy="product")
    private List<Prediction> predictions;

}
