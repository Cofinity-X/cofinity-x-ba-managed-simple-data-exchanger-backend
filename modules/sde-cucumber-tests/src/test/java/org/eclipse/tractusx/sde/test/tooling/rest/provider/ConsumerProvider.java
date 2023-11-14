package org.eclipse.tractusx.sde.test.tooling.rest.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.eclipse.tractusx.sde.test.tooling.rest.request.SubscribeDataOffersRequest;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.restassured.RestAssured.given;

public class ConsumerProvider {

    private ExtractableResponse<Response> response;

    private String processId = "";

    private final AuthenticationProvider authenticationProvider;

    public ConsumerProvider() {
        authenticationProvider = AuthenticationProvider.getInstance();

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (type, s) -> new ObjectMapper()
                        .registerModule(new JavaTimeModule())
                        .registerModule(new Jdk8Module())
                        .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                        .enable(READ_ENUMS_USING_TO_STRING)
                        .disable(FAIL_ON_IGNORED_PROPERTIES)
                        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                        .disable(WRITE_DATES_AS_TIMESTAMPS)
        ));
    }

    public void queryDataOffers(String providerUrl) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .queryParam("providerUrl", providerUrl)
                .when()
                .get("/api/query-data-offers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void subscribeDataOffers(SubscribeDataOffersRequest body) {
        response = given().log().body()
                .spec(authenticationProvider.getRequestSpecification())
                .body(body)
                .when()
                .post("/api/subscribe-data-offers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void downloadDataOffer() {
        processId = response.path("processId");
        given().spec(authenticationProvider.getRequestSpecification())
                .queryParam("processId", processId)
                .when()
                .post("/api/download-data-offers")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void getDownloadHistory() {
        given().spec(authenticationProvider.getRequestSpecification())
                .when()
                .post("/api/view-download-history/" + processId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }
}
