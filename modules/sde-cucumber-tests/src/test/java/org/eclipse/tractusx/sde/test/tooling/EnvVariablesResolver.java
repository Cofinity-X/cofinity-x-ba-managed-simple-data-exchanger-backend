package org.eclipse.tractusx.sde.test.tooling;

public class EnvVariablesResolver {

    public static String getSupervisorClientId() {
        return System.getenv("SUPERVISOR_CLIENT_ID");
    }

    public static String getSupervisorPassword() {
        return System.getenv("SUPERVISOR_PASSWORD");
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
}
