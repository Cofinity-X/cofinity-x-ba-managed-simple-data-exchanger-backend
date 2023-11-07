package org.eclipse.tractusx.sde.test.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.test.tooling.SubmodelEnum;
import org.eclipse.tractusx.sde.test.tooling.rest.provider.SubModelProcessProvider;
import org.eclipse.tractusx.sde.test.tooling.rest.request.CreateDataRequest;
import org.eclipse.tractusx.sde.test.utils.RequestUtils;
import org.eclipse.tractusx.sde.test.utils.SubmodelUtils;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;

import static org.eclipse.tractusx.sde.test.utils.TestUtils.normalize;

public class SubModelProcessStepDefinitions {

    private SubModelProcessProvider subModelProcessProvider;

    @Before
    public void setup() {
        subModelProcessProvider = new SubModelProcessProvider();
    }

    @ParameterType("batch|pcf|assembly-part-relationship|" +
            "part-as-planned|part-site-information-as-planned|serial-part-typization|" +
            "single-level-bom-as-planned|single-level-usage-as-built")
    public SubmodelEnum subModel(String subModel) {
        return SubmodelEnum.getEnumByString(subModel);
    }

    @When("I upload Data with the {subModel} submodel")
    public void iUploadDataWithTheSubModel(final SubmodelEnum subModel, final DataTable dataTable) {
        final Map<String, String> input = normalize(dataTable.asMap());
        Object rowData;
        switch (subModel) {
            case ASSEMBLY_PART_RELATIONSHIP -> rowData = SubmodelUtils.buildAssemblyPartRelationshipRowData(input);
            case BATCH -> rowData = SubmodelUtils.buildBatchRowData(input);
            case PART_AS_PLANNED -> rowData = SubmodelUtils.buildPartAsPlannedRowData(input);
            case PART_SITE_INFORMATION_AS_PLANNED -> rowData = SubmodelUtils.buildPartSiteInfoAsPlannedRowData(input);
            case PCF -> rowData = SubmodelUtils.buildPcfRowData(input);
            case SERIAL_PART_TYPIZATION -> rowData = SubmodelUtils.buildSerialPartTypizationRowData(input);
            case SINGLE_LEVEL_BOM_AS_PLANNED -> rowData = SubmodelUtils.buildSingleLevelBomAsPlannedRowData(input);
            case SINGLE_LEVEL_USAGE_AS_BUILT -> rowData = SubmodelUtils.buildSingleLevelUsageAsBuildRowData(input);
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

        ResponseEntity response = subModelProcessProvider.uploadDataManual(subModel, body);
    }

    @When("I search for the {subModel} submodel with the uuid: {string}")
    public void iSearchForTheSubmodelWithUuid(final SubmodelEnum submodelEnum, final String uuid) {
        ResponseEntity response = subModelProcessProvider.getData(submodelEnum, uuid);
    }

    @Then("I check, if the {subModel} data is uploaded")
    public void iCheckIfTheDataIsUploaded(final SubmodelEnum subModel, final DataTable dataTable) {
        final Map<String, String> input = normalize(dataTable.asMap());

    }
}
