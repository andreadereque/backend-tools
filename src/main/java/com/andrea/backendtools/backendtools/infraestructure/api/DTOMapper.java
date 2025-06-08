package com.andrea.backendtools.backendtools.infraestructure.api;


import com.andrea.backendtools.backendtools.domain.criteria.SalesUnitCriteria;
import com.andrea.backendtools.backendtools.domain.criteria.StockRatioCriteria;
import com.andrea.backendtools.backendtools.domain.model.Product;
import com.andrea.backendtools.backendtools.domain.model.CriteriaWeight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DTOMapper {

    /**
     *  lista de ProductDTO a lista de Product
     */
    public List<Product> toDomain(List<ProductDTO> dtos) {
        return dtos.stream()
                .map(dto -> new Product(
                        dto.getId(),
                        dto.getName(),
                        dto.getSalesUnits(),
                        dto.getStocks()
                ))
                .collect(Collectors.toList());
    }

    /**
     * lista de  Product a lista de ProductDTO.
     */
    public List<ProductDTO> toDTOs(List<Product> products) {
        return products.stream()
                .map(p -> new ProductDTO(
                        p.getId(),
                        p.getName(),
                        p.getSalesUnits(),
                        p.getStocks()
                ))
                .collect(Collectors.toList());
    }

    /**
     * crea la lista de CriteriaWeight.
     * escalabilidad si aparecen más criterios
     */
    public List<CriteriaWeight> toCriteriaWeights(Map<String, Double> weightsMap) {
        return weightsMap.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    double weight = entry.getValue();
                    switch (key) {
                        case "salesUnits":
                            return new CriteriaWeight(new SalesUnitCriteria(), weight);
                        case "stockRatio":
                            return new CriteriaWeight(new StockRatioCriteria(), weight);
                        default:
                            throw new IllegalArgumentException("Criterio desconocido: " + key);
                    }
                })
                .collect(Collectors.toList());
    }
}
