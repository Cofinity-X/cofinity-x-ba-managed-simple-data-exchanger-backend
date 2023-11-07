package org.eclipse.tractusx.sde.test.tooling.rest.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.eclipse.tractusx.sde.test.tooling.SubmodelEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.request.CreateDataRequest;
import org.springframework.http.ResponseEntity;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.restassured.RestAssured.given;

public class SubModelProcessProvider {

    private final AuthenticationProvider authenticationProvider;

    public SubModelProcessProvider() {
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

    public ResponseEntity uploadDataManual(final SubmodelEnum subModel, final CreateDataRequest body) {
        return given().log().body()
                .spec(authenticationProvider.getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/" + subModel.toString() + "/manualentry")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);

    }

    public ResponseEntity getData(SubmodelEnum subModel, String uuid) {
        return given().log().body()
                .spec(authenticationProvider.getRequestSpecification())
                .contentType(ContentType.JSON)
                .when()
                .get("/api/" + subModel.toString() + "/public/" + uuid)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }

    public ResponseEntity deleteData(SubmodelEnum subModel, String processId) {
        return given().log().body()
                .spec(authenticationProvider.getRequestSpecification())
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/" + subModel.toString() + "/delete/" + processId)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ResponseEntity.class);
    }


}
