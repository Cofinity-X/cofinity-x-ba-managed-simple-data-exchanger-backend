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

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractManagementProvider {

    private ExtractableResponse<Response> response;

    private String currentType;
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

    public void getContractAgreements(String type) {
        currentType = type;
        response =  given()
                .spec(authenticationProvider.getRequestSpecification())
                .queryParam("maxLimit", 10)
                .queryParam("offset", 0)
                .when()
                .get("/api/contract-agreements/" + type)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void updateContractAgreement(String action, String type, String id) {
        currentType = type;
        if (action.equals("decline") && type.equals("consumer")) {
            response = given()
                    .spec(authenticationProvider.getRequestSpecification())
                    .when()
                    .get("/api/contract-agreements/" + id + "/" + type + "/" + action)
                    .then().log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract();
        } else {
            response = given()
                    .spec(authenticationProvider.getRequestSpecification())
                    .when()
                    .post("/api/contract-agreements/" + id + "/" + type + "/" + action)
                    .then().log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract();
        }
    }

    public void checkIfContractsAreReturned() {
        String type = response.jsonPath().getString("contracts[0].type");
        assertEquals(type.toLowerCase(), currentType);
    }

}
