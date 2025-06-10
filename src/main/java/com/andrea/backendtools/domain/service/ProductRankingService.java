package com.andrea.backendtools.domain.service;

import com.andrea.backendtools.domain.criteria.NormalizedSalesUnitsCriteria;
import com.andrea.backendtools.domain.criteria.SalesUnitCriteria;
import com.andrea.backendtools.domain.model.CriteriaWeight;
import com.andrea.backendtools.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRankingService {
    private final List<CriteriaWeight> criteriaWeightList;
    public ProductRankingService(List<CriteriaWeight> criteriaWeightList) {
        this.criteriaWeightList = criteriaWeightList;
    }

    /**
     * ordernar lista de productos de mayor a menor puntuación
     * @param products a ordenar
     * @return lista ordenada segun pesos y criterios
     */
    public List<Product> rank(List<Product> products, List<CriteriaWeight> criteriaWeights) {
        int maxSales = products.stream()
                .mapToInt(Product::getSalesUnits)
                .max()
                .orElse(1);

        List<CriteriaWeight> normalizedWeights = criteriaWeights.stream()
                .map(cw -> {
                    if (cw.getCriteria() instanceof SalesUnitCriteria) {
                        return new CriteriaWeight(
                                new NormalizedSalesUnitsCriteria(maxSales),
                                cw.getWeight()
                        );
                    } else {
                        return cw;
                    }
                })
                .collect(Collectors.toList());

        ScoreCalculator calculator = new ScoreCalculator(normalizedWeights);
        return products.stream()
                .sorted(Comparator.comparingDouble(calculator::calculate).reversed())
                .collect(Collectors.toList());
    }
}


