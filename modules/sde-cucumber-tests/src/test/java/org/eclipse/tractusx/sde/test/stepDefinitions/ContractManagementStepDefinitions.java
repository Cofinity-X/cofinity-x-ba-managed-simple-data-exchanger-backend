package org.eclipse.tractusx.sde.test.stepDefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.ContractManagementProvider;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity contractAgreements = contractManagementProvider.getContractAgreements(type);
    }

    @When("I {contractAction} the {companyType} contract with the id {string}")
    public void iUpdateContractAgreements(final String action, final String type, final String id) {
        ResponseEntity contractAgreements = contractManagementProvider.updateContractAgreement(action, type, id);
    }
}
