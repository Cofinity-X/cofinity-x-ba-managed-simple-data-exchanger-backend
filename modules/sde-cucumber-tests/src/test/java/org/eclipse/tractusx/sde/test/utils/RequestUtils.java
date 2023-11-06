package org.eclipse.tractusx.sde.test.utils;

import io.cucumber.datatable.DataTable;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.common.enums.DurationEnum;
import org.eclipse.tractusx.sde.common.enums.PolicyAccessEnum;
import org.eclipse.tractusx.sde.common.enums.UsagePolicyEnum;
import org.eclipse.tractusx.sde.edc.model.request.Offer;
import org.eclipse.tractusx.sde.test.tooling.rest.request.SubscribeDataOffersRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class RequestUtils {

    public static ArrayList<UsagePolicies> buildUsagePolicies(final Map<String, String> input) {
        final String type = input.get("type") == null ? "DURATION" : input.get("type");
        final String value = input.get("value") == null ? "value" : input.get("value");
        final String typeOfAccess = input.get("type_of_access") == null ? "RESTRICTED" : input.get("type_of_access");
        final String durationUnit = input.get("durationUnit") == null ? "YEAR" : input.get("durationUnit");
        final UsagePolicies policy = UsagePolicies.builder()
                .value(value)
                .typeOfAccess(PolicyAccessEnum.valueOf(typeOfAccess))
                .type(UsagePolicyEnum.valueOf(type))
                .durationUnit(DurationEnum.valueOf(durationUnit))
                .build();
        return new ArrayList<>(Collections.singletonList(policy));
    }

    public static ArrayList<Offer> buildOffers(final Map<String, String> input) {
        final String offerId = input.get("offerId") == null ? "TestId5784" : input.get("offerId");
        final String assetId = input.get("assetId") == null ? "AssetId5784" : input.get("assetId");
        final String policyId = input.get("policyId") == null ? "PoId5784" : input.get("policyId");

        final Offer offer = Offer.builder()
                .offerId(offerId)
                .assetId(assetId)
                .policyId(policyId)
                .build();
        return new ArrayList<>(Collections.singletonList(offer));
    }
}
