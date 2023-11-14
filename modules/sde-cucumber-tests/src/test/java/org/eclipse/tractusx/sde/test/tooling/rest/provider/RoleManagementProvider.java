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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleManagementProvider {
    private final AuthenticationProvider authenticationProvider;

    private ExtractableResponse<Response> response;

    public RoleManagementProvider() {
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

    public void getAllPermissions() {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .get("/api/user/role/permissions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void getRolePermissions(String role) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .get("/api/role/" + role + "/permissions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void createRole(JSONObject body) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/api/role")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void verifyAllPermissions() {
        final ArrayList permissions = response.as(ArrayList.class);
        assertEquals(permissions.size(), 30);
    }

    public void verifyRolePermissions(String role) {
        final ArrayList permissions = response.as(ArrayList.class);
        int permissionsCount;
        switch (role) {
            case "User" -> permissionsCount = 11;
            case "Admin" -> permissionsCount = 6;
            case "Creator" -> permissionsCount = 13;
            default -> permissionsCount = 0;
        }
        assertEquals(permissionsCount, permissions.size());
    }


    public void addPermissionsToRole(String role, JSONArray body) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/api/role/" + role + "/permissions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
    }

    public void verifyUserIsCreated(String role) {
        response = given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .get("/api/role/" + role + "/permissions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();

        final ArrayList permissions = response.as(ArrayList.class);
        assertEquals(1, permissions.size());
    
    }

    public void deleteRole(String role) {
        given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .delete("/api/role/" + role)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public void verifyRoleIsDeleted(String role) {
         given()
                .spec(authenticationProvider.getRequestSpecification())
                .when()
                .get("/api/role/" + role + "/permissions")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
