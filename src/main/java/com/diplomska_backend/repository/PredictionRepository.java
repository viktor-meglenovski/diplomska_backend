package com.diplomska_backend.repository;

import com.diplomska_backend.model.entities.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction,Long> {
}
