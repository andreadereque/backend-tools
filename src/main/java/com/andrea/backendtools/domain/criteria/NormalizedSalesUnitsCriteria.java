package com.andrea.backendtools.domain.criteria;

import com.andrea.backendtools.domain.model.Product;

public class NormalizedSalesUnitsCriteria implements ScoringCriteria<Product>{
    private final int maxSales;

    public NormalizedSalesUnitsCriteria(int maxSales) {
        this.maxSales = Math.max(1, maxSales);
    }

    @Override
    public double score(Product p) {
        return (double) p.getSalesUnits() / maxSales;
    }
}
