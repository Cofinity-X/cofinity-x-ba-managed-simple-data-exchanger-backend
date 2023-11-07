package org.eclipse.tractusx.sde.test.utils;

import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.common.enums.DurationEnum;
import org.eclipse.tractusx.sde.common.enums.PolicyAccessEnum;
import org.eclipse.tractusx.sde.common.enums.UsagePolicyEnum;
import org.eclipse.tractusx.sde.edc.model.request.Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class RequestUtils {

    public static ArrayList<UsagePolicies> buildUsagePolicies(final Map<String, String> input) {
        final String type = input.getOrDefault("type", "DURATION");
        final String value = input.getOrDefault("value", "value");
        final String typeOfAccess = input.getOrDefault("type_of_access", "RESTRICTED");
        final String durationUnit = input.getOrDefault("durationUnit", "YEAR");
        final UsagePolicies policy = UsagePolicies.builder()
                .value(value)
                .typeOfAccess(PolicyAccessEnum.valueOf(typeOfAccess))
                .type(UsagePolicyEnum.valueOf(type))
                .durationUnit(DurationEnum.valueOf(durationUnit))
                .build();
        return new ArrayList<>(Collections.singletonList(policy));
    }

    public static ArrayList<Offer> buildOffers(final Map<String, String> input) {
        final String offerId = input.getOrDefault("offerId", "TestId5784");
        final String assetId = input.getOrDefault("assetId", "AssetId5784");
        final String policyId = input.getOrDefault("policyId", "PoId5784");

        final Offer offer = Offer.builder()
                .offerId(offerId)
                .assetId(assetId)
                .policyId(policyId)
                .build();
        return new ArrayList<>(Collections.singletonList(offer));
    }
}
