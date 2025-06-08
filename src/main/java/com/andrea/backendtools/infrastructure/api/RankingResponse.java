package com.andrea.backendtools.infrastructure.api;

import lombok.Value;

import java.util.List;

@Value
public class RankingResponse {
    List<ProductDTO> rankedProducts;

    public RankingResponse(List<ProductDTO> rankedProducts) {
        this.rankedProducts = rankedProducts;
    }
}

