package com.andrea.backendtools.backendtools.infraestructure.api;

import lombok.Value;

import java.util.List;

@Value
public class RankingResponse {
    List<ProductDTO> rankedProducts;
}

