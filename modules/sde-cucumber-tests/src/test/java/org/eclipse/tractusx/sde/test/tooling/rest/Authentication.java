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

    private String clientId;
    private String clientSecret;
    private String keycloakHost;

    public Authentication() {
        clientId = EnvVariablesResolver.getSupervisorClientId();
        clientSecret = EnvVariablesResolver.getSupervisorPassword();
        keycloakHost = EnvVariablesResolver.getKeycloakHost();
    }

    public String obtainAccessToken() {
        final Map<String, String> oauth2Payload = new HashMap<>();
        oauth2Payload.put("grant_type", "client_credentials");
        oauth2Payload.put("client_id", clientId);
        oauth2Payload.put("client_secret", clientSecret);

        return given()
                .params(oauth2Payload)
                .header(new Header("Content-Type", "application/x-www-form-urlencoded"))
                .post(keycloakHost)
                .then()
                .extract().jsonPath().getString("access_token");
    }
}
