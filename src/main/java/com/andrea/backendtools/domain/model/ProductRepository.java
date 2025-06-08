package com.andrea.backendtools.domain.model;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product save(Product product);
}