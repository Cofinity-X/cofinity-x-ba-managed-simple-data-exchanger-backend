package org.eclipse.tractusx.sde.test.stepDefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.AuthenticationProvider;

@Slf4j
public class AuthenticationStepDefinitions {

    private AuthenticationProvider authenticationProvider;

    @Before
    public void setup() {
        authenticationProvider = AuthenticationProvider.getInstance();
    }
    @ParameterType("SDE_A|SDE_B")
    public SdeEnvironmentEnum sdeApplication(String environment) {
        return SdeEnvironmentEnum.valueOf(environment);
    }

    @Given("I am logged into {sdeApplication} application")
    public void iAmLoggedIntoApplication(SdeEnvironmentEnum environment) {
        authenticationProvider.loginToEnvironment(environment);
    }


}
