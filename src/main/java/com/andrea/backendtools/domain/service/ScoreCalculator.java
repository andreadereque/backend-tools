package com.andrea.backendtools.domain.service;

import com.andrea.backendtools.domain.model.CriteriaWeight;
import com.andrea.backendtools.domain.model.Product;

import java.util.List;

public class ScoreCalculator {
    private  final List<CriteriaWeight> criteriaWeights;
    public ScoreCalculator(List<CriteriaWeight> criteriaWeights){
        this.criteriaWeights = criteriaWeights;
    }

    public double calculate(Product product){
        return criteriaWeights.stream()
                .mapToDouble(cw -> cw.weightScore(product)).sum();
    }
}
