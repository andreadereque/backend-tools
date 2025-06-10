package com.andrea.backendtools.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class RankingResponse {
    @JsonProperty("rankedProducts")
    List<ProductDTO> rankedProducts;

    public RankingResponse(List<ProductDTO> rankedProducts) {
        this.rankedProducts = rankedProducts;
    }
}

