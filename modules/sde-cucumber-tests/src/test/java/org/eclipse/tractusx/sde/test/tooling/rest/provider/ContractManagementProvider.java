package org.eclipse.tractusx.sde.test.tooling.rest.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.restassured.RestAssured.given;

public class ContractManagementProvider {

    private final AuthenticationProvider authenticationProvider;

    public ContractManagementProvider() {
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

    public ResponseEntity getContractAgreements(final String type) {
        return given().log().body()
                .spec(authenticationProvider.getRequestSpecification())
                .queryParam("maxLimit", 10)
                .queryParam("offset", 0)
                .when()
                .get("/api/contract-agreements/" + type)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }

    public ResponseEntity updateContractAgreement(final String action, final String type, final String id) {
        if (action.equals("decline") && type.equals("consumer")) {
            return given().log().body()
                    .spec(authenticationProvider.getRequestSpecification())
                    .when()
                    .get("/api/contract-agreements/" + id + "/" + type + "/" + action)
                    .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(ResponseEntity.class);
        }

        return given().log().body()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .post("/api/contract-agreements/" + id + "/" + type + "/" + action)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }
}
