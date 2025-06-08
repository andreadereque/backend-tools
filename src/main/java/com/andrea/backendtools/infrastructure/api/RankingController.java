package com.andrea.backendtools.infrastructure.api;

import com.andrea.backendtools.domain.model.CriteriaWeight;
import com.andrea.backendtools.domain.model.Product;
import com.andrea.backendtools.domain.service.ProductRankingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rank")
public class RankingController {

    private final ProductRankingService rankingService;
    private final DTOMapper mapper;

    public RankingController(ProductRankingService rankingService, DTOMapper mapper) {
        this.rankingService = rankingService;
        this.mapper = mapper;
    }

    @PostMapping
    public RankingResponse rank(@RequestBody RankingRequest request) {

        List<Product> domainProducts = mapper.toDomain(request.getProducts());
        List<CriteriaWeight> weights = mapper.toCriteriaWeights(request.getWeights());
        List<Product> ranked = rankingService.rank(domainProducts);
        return new RankingResponse(mapper.toDTOs(ranked));
    }
}
