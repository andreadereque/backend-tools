package com.andrea.backendtools.integration;

import com.andrea.backendtools.infrastructure.api.ProductDTO;
import com.andrea.backendtools.infrastructure.api.RankingRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RankingControllerE2ETest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void rankEndpointIntegrationTest() {
        ProductDTO p1 = new ProductDTO("1", "A", 5, Map.of("S",1, "M",0));
        ProductDTO p2 = new ProductDTO("2", "B", 3, Map.of("S",0, "M",1));
        RankingRequest request = new RankingRequest(
                List.of(p1, p2), Map.of("salesUnits",1.0, "stockRatio",1.0)
        );

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/rank")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("rankedProducts[0].id", equalTo("1"))
                .body("rankedProducts[1].id", equalTo("2"));
    }
}
