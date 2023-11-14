package org.eclipse.tractusx.sde.test.stepDefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.RoleManagementProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class RoleManagementStepDefinitions {

    private RoleManagementProvider roleManagementProvider;
    @Before
    public void setup() {
        roleManagementProvider = new RoleManagementProvider();
    }

    @ParameterType("User|Creator|Admin")
    public String role(final String role) {
        return role;
    }

    @When("i request all user roles")
    public void iRequestAllUserRoles() {
        roleManagementProvider.getAllPermissions();
    }

    @When("i request the {role} permissions")
    public void iRequestTheUserPermissions(final String role) {
        roleManagementProvider.getRolePermissions(role);
    }

    @Then("i verify that all permissions are returned")
    public void iVerifyAllReturnedRoles() {
        roleManagementProvider.verifyAllPermissions();
    }

    @Then("i verify that the {role} permissions are returned")
    public void iVerifyThatTheUserPermissionsAreReturned(final String role) {
        roleManagementProvider.verifyRolePermissions(role);
    }

    @When("i create {string} as a new  role")
    public void iCreateANewRole(final String role) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("role", role);
        body.put("description", "e2e test");

        roleManagementProvider.createRole(body);
    }

    @When("i add permissions to {string}")
    public void iAddPermissionsToTheRole(final String role) throws JSONException {
        JSONArray body = new JSONArray();
        body.put("provider_view_history");
        roleManagementProvider.addPermissionsToRole(role, body);
    }

    @Then("i verify that the  role {string} is created")
    public void iVerifyThatTheRoleIsCreated(final String role) {
        roleManagementProvider.verifyUserIsCreated(role);
    }

    @When("i delete the {string} role")
    public void iDeleteTheRole(String role) {
        roleManagementProvider.deleteRole(role);
    }

    @Then("i verify that the {string} role is deleted")
    public void iVerifyThatTheRoleIsDeleted(String role) {
        roleManagementProvider.verifyRoleIsDeleted(role);
    }
}
