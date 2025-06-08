package com.andrea.backendtools.infrastructure.mongo;

import com.andrea.backendtools.domain.model.Product;
import com.andrea.backendtools.domain.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductPersistenceAdapter implements ProductRepository {
    private final MongoProductRepository repository;
    public ProductPersistenceAdapter(MongoProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
            .map(doc -> new Product(
                doc.getId(), doc.getName(), doc.getSalesUnits(), doc.getStocks()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        ProductDocument doc = new ProductDocument();
        doc.setId(product.getId());
        doc.setName(product.getName());
        doc.setSalesUnits(product.getSalesUnits());
        doc.setStocks(product.getStocks());
        ProductDocument saved = repository.save(doc);
        return new Product(
            saved.getId(), saved.getName(), saved.getSalesUnits(), saved.getStocks()
        );
    }
}
