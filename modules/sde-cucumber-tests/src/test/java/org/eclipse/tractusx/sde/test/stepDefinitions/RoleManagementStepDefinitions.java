package org.eclipse.tractusx.sde.test.stepDefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.RoleManagementProvider;
import org.springframework.http.ResponseEntity;

@Slf4j
public class RoleManagementStepDefinitions {

    private RoleManagementProvider roleManagementProvider;
    @Before
    public void setup() {
        roleManagementProvider = new RoleManagementProvider();
    }

    @ParameterType("user|creator|admin")
    public String role(final String role) {
        return role;
    }

    @When("I request all user roles")
    public void iRequestAllUserRoles() {
        ResponseEntity response = roleManagementProvider.getAllPermissions();

    }

    @When("i request the {role} permissions")
    public void iRequestTheUserPermissions(final String role) {
        ResponseEntity response = roleManagementProvider.getRolePermissions(role);
    }
}
