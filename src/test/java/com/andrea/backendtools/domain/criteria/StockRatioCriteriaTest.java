package com.andrea.backendtools.domain.criteria;

import com.andrea.backendtools.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StockRatioCriteriaTest {
    @Test
    void scoreCalculatesCorrectRatio() {
        Map<String,Integer> stocks = Map.of("S",1, "M",0, "L",1);
        Product product = new Product("1", "Test", 0, stocks);
        StockRatioCriteria criteria = new StockRatioCriteria();

        double result = criteria.score(product);

        assertThat(result).isEqualTo(2.0/3);
    }
}
