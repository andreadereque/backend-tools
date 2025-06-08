package com.andrea.backendtools.backendtools.infraestructure.api;

import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class RankingRequest {
    List<ProductDTO> products;
    Map<String,Double> weights;
}
