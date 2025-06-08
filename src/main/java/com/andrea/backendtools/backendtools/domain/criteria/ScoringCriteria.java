package com.andrea.backendtools.backendtools.domain.criteria;

import com.andrea.backendtools.backendtools.domain.model.Product;

public interface ScoringCriteria {
    /**
     * return puntuación que el criterio aporta al producto
     * queda escalable en caso de querer añadir más criterios
     */
    double score(Product product);
}
