package com.andrea.backendtools.infrastructure.api;

import lombok.Getter;
import lombok.Value;

import java.util.Map;

@Value
public class ProductDTO {

    String id;
    String name;
    int salesUnits;

    Map<String,Integer> stocks;

    public ProductDTO(String id, String name, int salesUnits, Map<String, Integer> stocks) {
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
}

