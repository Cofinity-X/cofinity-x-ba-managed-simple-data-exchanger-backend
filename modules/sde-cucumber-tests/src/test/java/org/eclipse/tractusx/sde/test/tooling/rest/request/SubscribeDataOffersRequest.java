package org.eclipse.tractusx.sde.test.tooling.rest.request;

import lombok.Builder;
import lombok.Data;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.edc.model.request.Offer;

import java.util.ArrayList;

@Builder
@Data
public class SubscribeDataOffersRequest {

    private String connectorId;
    private String providerUrl;
    private ArrayList<Offer> offers;
    private ArrayList<UsagePolicies> policies;

}

