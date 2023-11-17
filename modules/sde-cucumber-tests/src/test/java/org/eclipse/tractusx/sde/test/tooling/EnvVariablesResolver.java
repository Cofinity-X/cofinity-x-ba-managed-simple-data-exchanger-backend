package org.eclipse.tractusx.sde.test.tooling;

public class EnvVariablesResolver {

    public static String getUsername() {
        return System.getenv("USER_NAME");
    }

    public static String getPassword() {
        return System.getenv("USER_PASSWORD");
    }

    public static String getClientId() {
        return System.getenv("CLIENT_ID");
    }

    public static String getKeycloakHost() {
        return System.getenv("KEYCLOAK_HOST");
    }

    public static String getSDE_A_Host() {
        return System.getenv("E2E_SDE_A_HOST");
    }

    public static String getSDE_B_Host() {
        return System.getenv("E2E_SDE_B_HOST");
    }

    public static String getApiKey() {
        return System.getenv("EDC_API_KEY");
    }
}
