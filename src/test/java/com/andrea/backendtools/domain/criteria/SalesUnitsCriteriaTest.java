package com.andrea.backendtools.domain.criteria;

import com.andrea.backendtools.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SalesUnitsCriteriaTest {
    @Test
    void scoreReturnsSalesUnits() {
        Product product = new Product("1", "Test", 7, Map.of());
        SalesUnitCriteria criteria = new SalesUnitCriteria();

        double result = criteria.score(product);

        assertThat(result).isEqualTo(7);
    }
}