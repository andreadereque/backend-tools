package com.andrea.backendtools.domain.service;

import com.andrea.backendtools.domain.criteria.SalesUnitCriteria;
import com.andrea.backendtools.domain.criteria.StockRatioCriteria;
import com.andrea.backendtools.domain.model.CriteriaWeight;
import com.andrea.backendtools.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreCalculatorTest {

    @Test
    void calculateSumOfWeightedScores() {
        Product product = new Product(
                "1",
                "Test Shirt",
                10,
                Map.of("S", 1, "M", 0)
        );
        CriteriaWeight w1 = new CriteriaWeight(new SalesUnitCriteria(), 2.0);
        CriteriaWeight w2 = new CriteriaWeight(new StockRatioCriteria(), 3.0);
        ScoreCalculator calculator = new ScoreCalculator(List.of(w1, w2));

        double score = calculator.calculate(product);

        assertThat(score).isEqualTo(21.5);
    }
}
