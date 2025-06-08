package com.andrea.backendtools.backendtools.infraestructure.api;

import lombok.Value;

import java.util.Map;

@Value
public class ProductDTO {
    String id;
    String name;
    int salesUnits;
    Map<String,Integer> stocks;
}

