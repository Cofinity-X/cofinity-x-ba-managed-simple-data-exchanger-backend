package org.eclipse.tractusx.sde.test;

import groovy.util.logging.Slf4j;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum;
import org.eclipse.tractusx.sde.test.tooling.SubmodelEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.RestProvider;

@Slf4j
public class SdeStepDefinition {

    private RestProvider restProvider;

    @ParameterType("SDE_A|SDE_B")
    public SdeEnvironmentEnum SdeApplication(String environment) {
        return SdeEnvironmentEnum.valueOf(environment);
    }

    @ParameterType("batch|pcf|assembly-part-relationship|" +
            "part-as-planned|part-site-information-as-planned|serial-part-typization|" +
            "single-level-bom-as-planned|single-level-usage-as-built")
    public SubmodelEnum subModel(String subModel) {
        return SubmodelEnum.getEnumByString(subModel);
    }

    @Before
    public void setup() {
        restProvider = new RestProvider();
    }

    @Given("I am logged into {SdeApplication} application")
    public void iAmLoggedIntoApplication(SdeEnvironmentEnum environment) {
        restProvider.loginToEnvironment(environment);
    }

    @When("I upload Data with the {subModel} submodel")
    public void iUploadDataWithTheSubModel(SubmodelEnum subModel, DataTable dataTable) {

    }
}
