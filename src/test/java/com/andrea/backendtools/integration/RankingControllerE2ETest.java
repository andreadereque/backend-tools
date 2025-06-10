package com.andrea.backendtools.integration;

import com.andrea.backendtools.infrastructure.api.ProductDTO;
import com.andrea.backendtools.infrastructure.api.RankingRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RankingControllerE2ETest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should rank products correctly with default weights")
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

    @Test
    @DisplayName("Should handle empty product list")
    void handleEmptyProductList() {
        RankingRequest request = new RankingRequest(
                List.of(), Map.of("salesUnits", 1.0, "stockRatio", 1.0)
        );

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/rank")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("rankedProducts", hasSize(0));
    }

    @Test
    @DisplayName("Should handle zero weights")
    void handleZeroWeights() {
        ProductDTO p1 = new ProductDTO("1", "Product 1", 100, Map.of("S", 1));
        ProductDTO p2 = new ProductDTO("2", "Product 2", 50, Map.of("S", 1));
        RankingRequest request = new RankingRequest(
                List.of(p1, p2), Map.of("salesUnits", 0.0, "stockRatio", 0.0)
        );

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/rank")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("rankedProducts", hasSize(2));
    }

    @Test
    @DisplayName("Should handle invalid request format")
    void handleInvalidRequestFormat() {
        String invalidJson = "{ invalid json }";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(invalidJson)
                .when()
                .post("/api/rank")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should handle products with negative sales units")
    void handleNegativeSalesUnits() {
        ProductDTO p1 = new ProductDTO("1", "Product 1", -100, Map.of("S", 1));
        ProductDTO p2 = new ProductDTO("2", "Product 2", 50, Map.of("S", 1));
        RankingRequest request = new RankingRequest(
                List.of(p1, p2), Map.of("salesUnits", 1.0, "stockRatio", 1.0)
        );

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/rank")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("rankedProducts", hasSize(2))
                .body("rankedProducts[0].id", equalTo("2"))
                .body("rankedProducts[1].id", equalTo("1"));
    }

    @Test
    @DisplayName("Should handle products with no stock")
    void handleProductsWithNoStock() {
        ProductDTO p1 = new ProductDTO("1", "Product 1", 100, Map.of());
        ProductDTO p2 = new ProductDTO("2", "Product 2", 50, Map.of("S", 1));
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
                .body("rankedProducts[0].id", equalTo("2"))
                .body("rankedProducts[1].id", equalTo("1"));
    }

    @Test
    @DisplayName("Should handle missing required fields")
    void handleMissingRequiredFields() {
        String requestWithoutProducts = """
            {
                "weights": {
                    "salesUnits": 1.0,
                    "stockRatio": 1.0
                }
            }
            """;

        given()
                .contentType("application/json")
                .body(requestWithoutProducts)
                .when()
                .post("/api/rank")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
