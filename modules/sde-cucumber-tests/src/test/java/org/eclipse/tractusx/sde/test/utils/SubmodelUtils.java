package org.eclipse.tractusx.sde.test.utils;

import io.cucumber.datatable.DataTable;
import org.eclipse.tractusx.sde.common.entities.UsagePolicies;
import org.eclipse.tractusx.sde.common.enums.DurationEnum;
import org.eclipse.tractusx.sde.common.enums.PolicyAccessEnum;
import org.eclipse.tractusx.sde.common.enums.UsagePolicyEnum;
import org.eclipse.tractusx.sde.submodels.batch.model.Batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.eclipse.tractusx.sde.test.utils.TestUtils.normalize;

public class SubmodelUtils {

    public static Batch buildBatchRowData(final Map<String, String> input) {
        final String uuid = input.get("uuid") == null ? "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c9" : input.get("uuid");
        final String batchId = input.get("batchId") == null ? "NO-159040131155901488695379" : input.get("batchId");
        final String part_instance_id = input.get("part_instance_id") == null ? "PINO-34634534536" : input.get("part_instance_id");
        final String manufacturing_date = input.get("manufacturing_date") == null ? "2022-02-04T14:48:54.709Z" : input.get("manufacturing_date");

        return Batch.builder()
                .uuid(uuid)
                .batchId(batchId)
                .partInstanceId(part_instance_id)
                .manufacturingDate(manufacturing_date)
                .build();
    }

    public static ArrayList<String> buildBpnNumbers(Map<String, String> input) {
        final ArrayList<String> numbers = new ArrayList<>();
        final String bpn_number = input.get("bpn_number") == null ? "BPNL000000000001" : input.get("bpn_number");
        numbers.add(bpn_number);
        return numbers;
    }
}

