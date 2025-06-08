package com.andrea.backendtools.domain.criteria;

import com.andrea.backendtools.domain.model.Product;

public class SalesUnitCriteria implements ScoringCriteria{


    @Override
    public double score(Product product) {
        return product.getSalesUnits();
    }
}
