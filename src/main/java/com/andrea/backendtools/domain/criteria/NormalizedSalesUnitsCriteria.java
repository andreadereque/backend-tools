package com.andrea.backendtools.domain.criteria;

import com.andrea.backendtools.domain.model.Product;

public class NormalizedSalesUnitsCriteria implements ScoringCriteria {
    private final int maxSales;

    public NormalizedSalesUnitsCriteria(int maxSales) {
        this.maxSales = Math.max(1, maxSales);
    }

    @Override
    public double score(Product product) {
        return (double) product.getSalesUnits() / maxSales;
    }
}
