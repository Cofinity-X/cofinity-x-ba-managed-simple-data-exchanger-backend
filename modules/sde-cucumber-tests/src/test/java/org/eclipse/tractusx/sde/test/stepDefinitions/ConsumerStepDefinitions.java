package org.eclipse.tractusx.sde.test.stepDefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.edc.model.request.Offer;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.ConsumerProvider;
import org.eclipse.tractusx.sde.test.tooling.rest.request.SubscribeDataOffersRequest;
import org.eclipse.tractusx.sde.test.utils.RequestUtils;

import java.util.ArrayList;
import java.util.Map;

import static org.eclipse.tractusx.sde.test.utils.TestUtils.normalize;

@Slf4j
public class ConsumerStepDefinitions {

    private ConsumerProvider consumerProvider;
    @Before
    public void setup() {
        consumerProvider = new ConsumerProvider();
    }

    @When("I query the data offers from {string}")
    public void iQueryTheDataOffersFrom(final String providerUrl) {
        consumerProvider.queryDataOffers(providerUrl);
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

        consumerProvider.subscribeDataOffers(body);

    }

    @When("I download data offers")
    public void iDownloadDataOffers() {
        consumerProvider.downloadDataOffer();
    }

    @Then("I check the download history")
    public void iCheckTheDownloadHistory() {
        consumerProvider.getDownloadHistory();
    }
}
