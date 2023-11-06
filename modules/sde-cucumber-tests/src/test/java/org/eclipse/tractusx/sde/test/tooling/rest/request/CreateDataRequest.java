package org.eclipse.tractusx.sde.test.tooling.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;

import java.util.ArrayList;

@Builder
@Data
public class CreateDataRequest {
    @JsonProperty("type_of_access")
    private String typeOfAccess;
    @JsonProperty("usage_policies")
    private ArrayList<UsagePolicies> usagePolicies;
    @JsonProperty("bpn_numbers")
    private ArrayList<String> bpnNumbers;
    @JsonProperty("row_data")
    private Object rowData;



}
