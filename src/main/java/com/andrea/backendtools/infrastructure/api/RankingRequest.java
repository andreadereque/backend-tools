package com.andrea.backendtools.infrastructure.api;


import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class RankingRequest {
    List<ProductDTO> products;
    Map<String,Double> weights;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public Map<String, Double> getWeights() {
        return weights;
    }
}
