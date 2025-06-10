package com.andrea.backendtools.domain.model;

import lombok.Value;
import java.util.Map;

@Value
public class Product {
    String id;
    String name;
    int salesUnits;
    Map<String, Integer> stocks;

    /**
     * # tallas disponibles
     */
    public long sizeWithStock() {
        return stocks.values().stream().filter(q -> q > 0).count();
    }

    /**
     * ratio de tallas con stock sobre tallas totales
     */
    public double stockRatio() {
        if (stocks.isEmpty()) return 0;
        return (double) sizeWithStock() / stocks.size();
    }
}
