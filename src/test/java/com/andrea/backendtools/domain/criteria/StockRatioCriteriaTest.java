package com.andrea.backendtools.domain.criteria;

import com.andrea.backendtools.domain.model.Product;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

class StockRatioCriteriaTest {
    private final StockRatioCriteria criteria = new StockRatioCriteria();

    @Test
    void scoreCalculatesCorrectRatio() {
        Product product = new Product("1", "Test Product", 100, Map.of("S", 2, "M", 1, "L", 0));
        double result = criteria.score(product);
        assertThat(result).isEqualTo(2.0/3.0);
    }
}
