package com.andrea.backendtools.infrastructure.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "products")
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private int salesUnits;
    private Map<String, Integer> stocks;

    public ProductDocument() {
    }

    public ProductDocument(String id, String name, int salesUnits, Map<String, Integer> stocks) {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalesUnits(int salesUnits) {
        this.salesUnits = salesUnits;
    }

    public void setStocks(Map<String, Integer> stocks) {
        this.stocks = stocks;
    }
}