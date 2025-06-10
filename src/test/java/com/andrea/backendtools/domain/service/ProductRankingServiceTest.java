package com.andrea.backendtools.domain.service;

import com.andrea.backendtools.domain.criteria.NormalizedSalesUnitsCriteria;
import com.andrea.backendtools.domain.criteria.StockRatioCriteria;
import com.andrea.backendtools.domain.model.CriteriaWeight;
import com.andrea.backendtools.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class ProductRankingServiceTest {
    private ProductRankingService rankingService;
    private List<CriteriaWeight> defaultWeights;

    @BeforeEach
    void setUp() {
        rankingService = new ProductRankingService(Collections.emptyList());
        defaultWeights = Arrays.asList(
            new CriteriaWeight(new NormalizedSalesUnitsCriteria(100), 1.0),
            new CriteriaWeight(new StockRatioCriteria(), 1.0)
        );
    }

    @Test
    void rankProductsWithMultipleCriteria() {
        List<Product> products = Arrays.asList(
            new Product("1", "V-NECK BASIC SHIRT", 100, Map.of("S", 4, "M", 9, "L", 0)),
            new Product("2", "CONTRASTING FABRIC T-SHIRT", 50, Map.of("S", 35, "M", 9, "L", 9)),
            new Product("3", "RAISED PRINT T-SHIRT", 80, Map.of("S", 20, "M", 2, "L", 20))
        );
        List<Product> result = rankingService.rank(products, defaultWeights);
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getId()).isEqualTo("3");
        assertThat(result.get(1).getId()).isEqualTo("1");
        assertThat(result.get(2).getId()).isEqualTo("2");
    }
} 