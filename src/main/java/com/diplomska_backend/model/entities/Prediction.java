package com.diplomska_backend.model.entities;

import com.diplomska_backend.model.enums.PredictionResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date predictedOn;

    private Long previousPrice;

    private Long nextPredictedPrice;

    private Long nextActualPrice;

    @Enumerated(value = EnumType.STRING)
    private PredictionResult predictionResult;

    private Boolean predictionAccuracy;

}
