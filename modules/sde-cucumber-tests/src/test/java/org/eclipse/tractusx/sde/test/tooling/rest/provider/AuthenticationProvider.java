package org.eclipse.tractusx.sde.test.tooling.rest.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.eclipse.tractusx.sde.test.tooling.EnvVariablesResolver;
import org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.Authentication;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum.SDE_A;
import static org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum.SDE_B;

public class AuthenticationProvider {

    private static AuthenticationProvider singleton;

    private String host;
    @Getter
    private SdeEnvironmentEnum currentEnv;
    private final Authentication authentication;

    public static AuthenticationProvider getInstance() {
        if (singleton == null) {
            singleton = new AuthenticationProvider();
        }
        return singleton;
    }

    private AuthenticationProvider() {
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



    public void loginToEnvironment(SdeEnvironmentEnum environment) {
        if (environment.equals(SDE_A)) {
            host = EnvVariablesResolver.getSDE_A_Host();
            currentEnv = SDE_A;
        } else if (environment.equals(SDE_B)) {
            host = EnvVariablesResolver.getSDE_B_Host();
            currentEnv = SDE_B;
        }
    }

    public RequestSpecification getRequestSpecification() {
        final String accessToken = authentication.obtainAccessToken();

        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", "Bearer " + accessToken);
        builder.addHeader("Access-Control-Allow-Origin", "*");
        builder.addHeader("x-api-key",EnvVariablesResolver.getApiKey());
        builder.setBaseUri(host);

        return builder.build();
    }
}
