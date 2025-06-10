package com.andrea.backendtools.infrastructure.api;

import com.andrea.backendtools.domain.model.Product;
import com.andrea.backendtools.domain.model.CriteriaWeight;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DTOMapperTest {
    private final DTOMapper mapper = new DTOMapper();

    @Test
    void toDomainAndBack() {
        ProductDTO dto = new ProductDTO("1", "Test", 5, Map.of("S", 2));

        List<Product> domainList = mapper.toDomain(List.of(dto));
        Product p = domainList.get(0);

        assertThat(p.getId()).isEqualTo(dto.getId());
        assertThat(p.getName()).isEqualTo(dto.getName());
        assertThat(p.getSalesUnits()).isEqualTo(dto.getSalesUnits());
        assertThat(p.getStocks()).isEqualTo(dto.getStocks());

        List<ProductDTO> dtoList = mapper.toDTOs(domainList);
        assertThat(dtoList).containsExactly(dto);
    }

    @Test
    void toCriteriaWeightsRecognizesValidKeys() {
        Map<String, Double> map = Map.of("salesUnits", 2.0, "stockRatio", 3.0);

        List<CriteriaWeight> weights = mapper.toCriteriaWeights(map);

        assertThat(weights).hasSize(2)
                .extracting(CriteriaWeight::getWeight)
                .containsExactlyInAnyOrder(2.0, 3.0);
    }

    @Test
    void toCriteriaWeightsUnknownKeyThrows() {
        Map<String, Double> map = Map.of("unknown", 1.0);

        assertThatThrownBy(() -> mapper.toCriteriaWeights(map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Criterio desconocido");
    }

    @Test
    void validateProductData() {
        // Test de validación de datos de producto
    }

    @Test
    void validateWeightConstraints() {
        // Test de restricciones de pesos
    }
}