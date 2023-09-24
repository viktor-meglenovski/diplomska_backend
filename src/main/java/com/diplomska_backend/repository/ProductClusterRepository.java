package com.diplomska_backend.repository;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.entities.ProductCluster;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductClusterRepository extends JpaRepository<ProductCluster,String> {
    List<ProductCluster> findAllByCategory(Category category);
    @Query("SELECT pc FROM ProductCluster pc JOIN pc.products p WHERE LOWER(p.name) LIKE %:text%")
    List<ProductCluster> findAllProductClustersContainingProductNameLike(String text);

    @Query("SELECT pc FROM ProductCluster pc JOIN pc.products p WHERE  p.store = :store")
    List<ProductCluster> findAllProductClustersContainingProductFromStore(Stores store);

    @Query("SELECT DISTINCT pc FROM ProductCluster pc " +
            "JOIN pc.products pr JOIN pr.currentPrice p " +
            "WHERE (:name IS NULL OR LOWER(pr.name) LIKE CONCAT('%', LOWER(:name), '%')) " +
            "AND (:category IS NULL OR pc.category = :category) " +
            "AND (:store IS NULL OR pr.store = :store) " +
            "AND (:lowerPrice IS NULL OR p.price >= :lowerPrice) " +
            "AND (:upperPrice IS NULL OR p.price <= :upperPrice)")
    Page<ProductCluster> filterProductClusters(
            String name,
            Category category,
            Stores store,
            Long lowerPrice,
            Long upperPrice,
            Pageable pageable);

    @Query("SELECT COUNT(DISTINCT pc.id) FROM ProductCluster pc " +
            "JOIN pc.products pr JOIN pr.currentPrice p " +
            "WHERE (:name IS NULL OR LOWER(pr.name) LIKE CONCAT('%', LOWER(:name), '%')) " +
            "AND (:category IS NULL OR pc.category = :category) " +
            "AND (:store IS NULL OR pr.store = :store) " +
            "AND (:lowerPrice IS NULL OR p.price >= :lowerPrice) " +
            "AND (:upperPrice IS NULL OR p.price <= :upperPrice)")
    Integer getPaginationInfoForFilteredClusters(String name,
                                            Category category,
                                            Stores store,
                                            Long lowerPrice,
                                            Long upperPrice);

    Page<ProductCluster> findAll(Pageable pageable);
}
