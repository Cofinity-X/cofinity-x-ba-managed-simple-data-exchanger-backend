package org.eclipse.tractusx.sde.test.tooling.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.eclipse.tractusx.sde.test.tooling.EnvVariablesResolver;
import org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum;
import org.eclipse.tractusx.sde.test.tooling.SubmodelEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.request.CreateDataRequest;
import org.apache.http.HttpStatus;
import org.eclipse.tractusx.sde.test.tooling.rest.request.SubscribeDataOffersRequest;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum.SDE_A;
import static org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum.SDE_B;

public class RestProvider {

    private String host;
    @Getter
    private SdeEnvironmentEnum currentEnv;
    private final Authentication authentication;

    public RestProvider() {
        host = null;
        authentication = new Authentication();

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

    public void loginToEnvironment(final SdeEnvironmentEnum environment) {
        if (environment.equals(SDE_A)) {
            host = EnvVariablesResolver.getSDE_A_Host();
            currentEnv = SDE_A;
        } else if (environment.equals(SDE_B)) {
            host = EnvVariablesResolver.getSDE_B_Host();
            currentEnv = SDE_B;
        }
        System.out.println(host);
    }

    private RequestSpecification getRequestSpecification() {
        final String accessToken = authentication.obtainAccessToken();

        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", "Bearer " + accessToken);
        builder.setBaseUri(host);

        return builder.build();
    }

    public ResponseEntity uploadDataManual(final SubmodelEnum subModel, final CreateDataRequest body) {
        return given().log().body()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/" + subModel.toString() + "/manualentry")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);

    }

    public ResponseEntity getContractAgreements(final String type) {
        return given().log().body()
                .spec(getRequestSpecification())
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
                    .spec(getRequestSpecification())
                    .when()
                    .get("/api/contract-agreements/" + id + "/" + type + "/" + action)
                    .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(ResponseEntity.class);
        }

        return given().log().body()
                .spec(getRequestSpecification())
                .when()
                .post("/api/contract-agreements/" + id + "/" + type + "/" + action)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }

    public ResponseEntity queryDataOffers(final String providerUrl) {
        return given().log().body()
                .spec(getRequestSpecification())
                .queryParam("providerUrl", providerUrl)
                .when()
                .get("/api/query-data-offers")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }

    public ResponseEntity subscribeDataOffers(final SubscribeDataOffersRequest body) {
        return given().log().body()
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .post("/api/subscribe-data-offers")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }

    public ResponseEntity getAllUserRoles() {
        return given().log().body()
                .spec(getRequestSpecification())
                .when()
                .get("/api/user/role/permission")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }
}
