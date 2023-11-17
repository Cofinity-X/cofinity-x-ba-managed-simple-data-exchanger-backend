package org.eclipse.tractusx.sde.test.stepDefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.ContractManagementProvider;
@Slf4j
public class ContractManagementStepDefinitions {

    private ContractManagementProvider contractManagementProvider;
    @Before
    public void setup() {
        contractManagementProvider = new ContractManagementProvider();
    }

    @ParameterType("consumer|provider")
    public String companyType(String type) {
        return type;
    }

    @ParameterType("cancel|decline")
    public String contractAction(String action) {
        return action;
    }


    @When("I search for the {companyType} contract agreements")
    public void iSearchForTheContractAgreements(final String type) {
        contractManagementProvider.getContractAgreements(type);
    }

    @When("I {contractAction} the {companyType} contract with the id {string}")
    public void iUpdateContractAgreements(final String action, final String type, final String id) {
        contractManagementProvider.updateContractAgreement(action, type, id);
    }

    @Then("I check if contracts are returned")
    public void iCheckIfContractsAreReturned() {
        contractManagementProvider.checkIfContractsAreReturned();
    }
}
