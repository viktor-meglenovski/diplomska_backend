package com.diplomska_backend.repository;

import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.model.entities.ProductCluster;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.PredictionResult;
import com.diplomska_backend.model.enums.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN p.predictions pred " +
            "WHERE pred.predictionResult = :type " +
            "AND pred.isPassed = FALSE " +
            "ORDER BY p.name ASC")
    Page<Product> getExpected(PredictionResult type, Pageable pageable);
    @Query("SELECT COUNT(DISTINCT p.id) FROM Product p " +
            "LEFT JOIN p.predictions pred " +
            "WHERE pred.predictionResult = :type " +
            "AND pred.isPassed = FALSE ")
    Integer getExpectedPaginationInfo(PredictionResult type);
}
