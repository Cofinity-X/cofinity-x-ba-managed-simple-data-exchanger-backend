package org.eclipse.tractusx.sde.test.tooling.rest;

import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.eclipse.tractusx.sde.test.tooling.EnvVariablesResolver;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Data
@Builder
@AllArgsConstructor
public class Authentication {

    private String username;
    private String password;
    private String clientId;
    private String keycloakHost;

    public Authentication() {

        username = EnvVariablesResolver.getUsername();
        password = EnvVariablesResolver.getPassword();
        clientId = EnvVariablesResolver.getClientId();
        keycloakHost = EnvVariablesResolver.getKeycloakHost();
    }

    public String obtainAccessToken() {
        final Map<String, String> oauth2Payload = new HashMap<>();
        oauth2Payload.put("grant_type", "password");
        oauth2Payload.put("username", username);
        oauth2Payload.put("password", password);
        oauth2Payload.put("client_id", clientId);

        return given()
                .params(oauth2Payload)
                .header(new Header("Content-Type", "application/x-www-form-urlencoded"))
                .post(keycloakHost)
                .then()
                .extract().jsonPath().getString("access_token");
    }
}
