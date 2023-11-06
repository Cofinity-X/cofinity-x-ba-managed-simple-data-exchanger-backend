package org.eclipse.tractusx.sde.test;

import groovy.util.logging.Slf4j;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.edc.model.request.Offer;
import org.eclipse.tractusx.sde.test.tooling.SdeEnvironmentEnum;
import org.eclipse.tractusx.sde.test.tooling.SubmodelEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.RestProvider;
import org.eclipse.tractusx.sde.test.tooling.rest.request.CreateDataRequest;
import org.eclipse.tractusx.sde.test.tooling.rest.request.SubscribeDataOffersRequest;
import org.eclipse.tractusx.sde.test.utils.RequestUtils;
import org.eclipse.tractusx.sde.test.utils.SubmodelUtils;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;

import static org.eclipse.tractusx.sde.test.utils.TestUtils.normalize;

@Slf4j
public class SdeStepDefinition {

    private RestProvider restProvider;

    @ParameterType("SDE_A|SDE_B")
    public SdeEnvironmentEnum sdeApplication(String environment) {
        return SdeEnvironmentEnum.valueOf(environment);
    }

    @ParameterType("consumer|provider")
    public String companyType(String type) {
        return type;
    }

    @ParameterType("cancel|decline")
    public String contractAction(String action) {
        return action;
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

    @Given("I am logged into {sdeApplication} application")
    public void iAmLoggedIntoApplication(SdeEnvironmentEnum environment) {
        restProvider.loginToEnvironment(environment);
    }

    @When("I upload Data with the {subModel} submodel")
    public void iUploadDataWithTheSubModel(final SubmodelEnum subModel, final DataTable dataTable) {
        final Map<String, String> input = normalize(dataTable.asMap());
        Object rowData;
        switch (subModel) {
            case BATCH -> rowData = SubmodelUtils.buildBatchRowData(input);
            default -> rowData = new Object();
        }

        final ArrayList<UsagePolicies> policies = RequestUtils.buildUsagePolicies(input);
        final ArrayList<String> bpnNumbers = SubmodelUtils.buildBpnNumbers(input);
        final String typeOfAccess = "ACCESS";

        final CreateDataRequest body = CreateDataRequest.builder()
                .rowData(rowData)
                .usagePolicies(policies)
                .bpnNumbers(bpnNumbers)
                .typeOfAccess(typeOfAccess)
                .build();

        ResponseEntity response = restProvider.uploadDataManual(subModel, body);
    }

    @When("I search for the {companyType} contract agreements")
    public void iSearchForTheContractAgreements(final String type) {
        ResponseEntity contractAgreements = restProvider.getContractAgreements(type);
    }

    @When("I {contractAction} the {companyType} contract with the id {string}")
    public void iUpdateContractAgreements(final String action, final String type, final String id) {
        ResponseEntity contractAgreements = restProvider.updateContractAgreement(action, type, id);
    }

    @When("I query the data offers from {string}")
    public void iQueryTheDataOffersFrom(final String providerUrl) {
        ResponseEntity contractAgreements = restProvider.queryDataOffers(providerUrl);
    }

    @When("I subscribe the data offers")
    public void iSubscribeTheDataOffers(final DataTable dataTable) {
        final Map<String, String> input = normalize(dataTable.asMap());

        final String connectorId = input.get("connectorId") == null ? "TestID234234" : input.get("connectorId");
        final String providerUrl = input.get("providerUrl") == null ? "http://localhost" : input.get("providerUrl");

        final ArrayList<UsagePolicies> policies = RequestUtils.buildUsagePolicies(input);
        final ArrayList<Offer> offers = RequestUtils.buildOffers(input);

        final SubscribeDataOffersRequest body = SubscribeDataOffersRequest.builder()
                .connectorId(connectorId)
                .providerUrl(providerUrl)
                .offers(offers)
                .policies(policies)
                .build();

        ResponseEntity response = restProvider.subscribeDataOffers(body);

    }
    @When("I request all user roles")
    public void iRequestAllUserRoles() {
        ResponseEntity response = restProvider.getAllUserRoles();

    }




    @Then("I check, if the {subModel} data is uploaded")
    public void iCheckIfTheDataIsUploaded(final SubmodelEnum subModel, final DataTable dataTable) {
        final Map<String, String> input = normalize(dataTable.asMap());


    }

}
