package com.andrea.backendtools.domain.model;

import java.util.Map;

public final class Product {
    private final String id;
    private final String name;
    private final int salesUnits;
    private final Map<String, Integer> stocks;

    public Product(String id, String name, int salesUnits, Map<String, Integer> stocks) {
        this.id = id;
        this.name = name;
        this.salesUnits = salesUnits;
        this.stocks = stocks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalesUnits() {
        return salesUnits;
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

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
