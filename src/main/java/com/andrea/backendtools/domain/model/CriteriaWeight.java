package com.andrea.backendtools.domain.model;

import com.andrea.backendtools.domain.criteria.ScoringCriteria;
import lombok.Value;

@Value
public class CriteriaWeight {
    ScoringCriteria criteria;
    double weight;

    /**
     * puntuación ponderada de este criterio para el producto
     */
    public double weightScore(Product product) {
        return weight * criteria.score(product);
    }
}
