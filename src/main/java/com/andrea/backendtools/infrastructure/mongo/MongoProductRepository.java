package com.andrea.backendtools.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductRepository extends MongoRepository<ProductDocument, String> {
}
