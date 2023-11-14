package org.eclipse.tractusx.sde.test.tooling.rest.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.eclipse.tractusx.sde.test.tooling.SubmodelEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.request.CreateDataRequest;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.restassured.RestAssured.given;

public class SubModelProcessProvider {

    private String processId = "";
    private ExtractableResponse<Response> response;
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

    public void uploadDataManual(SubmodelEnum subModel, CreateDataRequest body) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/" + subModel.toString() + "/manualentry")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();

    }

    public void getData(SubmodelEnum subModel, String uuid) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .get("/api/" + subModel.toString() + "/public/" + uuid)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void deleteData(SubmodelEnum subModel) {
        processId = response.path("processId");
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .delete("/api/" + subModel.toString() + "/delete/" + processId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }


    public void checkIfDataIsUploaded(SubmodelEnum subModel, String uuid) {
    }

    public void checkIfDataIsDeleted(SubmodelEnum subModel, String uuid) {
    }
}
