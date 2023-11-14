package org.eclipse.tractusx.sde.test.utils;

import org.eclipse.tractusx.sde.submodels.apr.model.AspectRelationship;
import org.eclipse.tractusx.sde.submodels.batch.model.Batch;
import org.eclipse.tractusx.sde.submodels.pap.model.PartAsPlanned;
import org.eclipse.tractusx.sde.submodels.pcf.model.PcfAspect;
import org.eclipse.tractusx.sde.submodels.psiap.model.PartSiteInformationAsPlanned;
import org.eclipse.tractusx.sde.submodels.slbap.model.SingleLevelBoMAsPlanned;
import org.eclipse.tractusx.sde.submodels.sluab.model.SingleLevelUsageAsBuilt;
import org.eclipse.tractusx.sde.submodels.spt.model.Aspect;

import java.util.ArrayList;
import java.util.Map;

public class SubmodelProcessUtils {

    public static Batch buildBatchRowData(Map<String, String> input) {
        final String uuid = input.getOrDefault("uuid", "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c9");
        final String batchId = input.getOrDefault("batch_id", "NO-159040131155901488695379");
        final String part_instance_id = input.getOrDefault("part_instance_id", "PINO-34634534536");
        final String manufacturing_date = input.getOrDefault("manufacturing_date", "2022-02-04T14:48:54.709Z");
        final String manufacturing_country = input.getOrDefault("manufacturing_country", "DEU");
        final String manufacturer_part_id = input.getOrDefault("manufacturer_part_id", "37754B7-77");
        final String classification = input.getOrDefault("classification", "component");
        final String name_at_manufacturer = input.getOrDefault("name_at_manufacturer", "Motor");

        return Batch.builder()
                .uuid(uuid)
                .batchId(batchId)
                .partInstanceId(part_instance_id)
                .manufacturingDate(manufacturing_date)
                .manufacturingCountry(manufacturing_country)
                .manufacturerPartId(manufacturer_part_id)
                .classification(classification)
                .nameAtManufacturer(name_at_manufacturer)
                .build();
    }

    public static SingleLevelUsageAsBuilt buildSingleLevelUsageAsBuildRowData(Map<String, String> input) {
        final String parent_uuid = input.getOrDefault("parent_uuid", "urn:uuid:bC7ADFb4-596a-Fe7F-6F28-a5EB7738D2Ca");
        final String parent_part_instance_id = input.getOrDefault("parent_part_instance_id", "NO-159040131155901488695376");
        final String parent_manufacturer_part_id = input.getOrDefault("parent_manufacturer_part_id", "MAN-159040131155901488695376");
        final String parent_optional_identifier_key = input.getOrDefault("parent_optional_identifier_key", "van");
        final String parent_optional_identifier_value = input.getOrDefault("parent_optional_identifier_value", "Value");
        final String uuid = input.getOrDefault("uuid", "urn:uuid:055c1128-0375-47c8-98de-7cf802c3241d");
        final String part_instance_id = input.getOrDefault("part_instance_id", "NO-159040131155901488");
        final String manufacturer_part_id = input.getOrDefault("manufacturer_part_id", "37754B7-76");
        final String optional_identifier_key = input.getOrDefault("optional_identifier_key", "van");
        final String optional_identifier_value = input.getOrDefault("optional_identifier_value", "Value");
        final String quantity_number = input.getOrDefault("quantity_number", "02. Mai");
        final String measurement_unit = input.getOrDefault("measurement_unit", "litre");
        final String created_on = input.getOrDefault("created_on", "2022-02-03T14:48:54.709Z");
        final String last_modified_on = input.getOrDefault("last_modified_on", "2022-02-03T14:48:54.709Z");

        return SingleLevelUsageAsBuilt.builder()
                .parentUuid(parent_uuid)
                .parentPartInstanceId(parent_part_instance_id)
                .parentManufacturerPartId(parent_manufacturer_part_id)
                .parentOptionalIdentifierKey(parent_optional_identifier_key)
                .parentOptionalIdentifierValue(parent_optional_identifier_value)
                .childUuid(uuid)
                .childPartInstanceId(part_instance_id)
                .childManufacturerPartId(manufacturer_part_id)
                .childOptionalIdentifierKey(optional_identifier_key)
                .childOptionalIdentifierValue(optional_identifier_value)
                .quantityNumber(quantity_number)
                .measurementUnit(measurement_unit)
                .createdOn(created_on)
                .lastModifiedOn(last_modified_on)
                .build();
    }

    public static PartAsPlanned buildPartAsPlannedRowData(Map<String, String> input) {
        final String uuid = input.getOrDefault("uuid", "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c2");
        final String manufacturer_part_id = input.getOrDefault("manufacturer_part_id", "37754B7-76");
        final String valid_from = input.getOrDefault("valid_from", "2021-06-14T06:55:29.935Z");
        final String valid_to = input.getOrDefault("valid_to", "2022-06-14T06:55:29.935Z");
        final String customer_part_id = input.getOrDefault("customer_part_id", "Currently missing the syntax");
        final String classification = input.getOrDefault("classification", "component");
        final String name_at_manufacturer = input.getOrDefault("name_at_manufacturer", "Sensor");

        return PartAsPlanned.builder()
                .uuid(uuid)
                .manufacturerPartId(manufacturer_part_id)
                .validFrom(valid_from)
                .validTo(valid_to)
                .customerPartId(customer_part_id)
                .classification(classification)
                .nameAtManufacturer(name_at_manufacturer)
                .build();
    }

    // Single Level Bom AsBuilt
    public static AspectRelationship buildAssemblyPartRelationshipRowData(Map<String, String> input) {
        final String parent_part_instance_id = input.getOrDefault("parent_part_instance_id", "NO-159040131155901488695376");
        final String parent_manufacturer_part_id = input.getOrDefault("parent_manufacturer_part_id", "MAN-159040131155901488695376");
        final String part_instance_id = input.getOrDefault("part_instance_id", "NO-159040131155901488695376");
        final String manufacturer_part_id = input.getOrDefault("manufacturer_part_id", "PART-123412416");
        final String manufacturer_id = input.getOrDefault("manufacturer_id", "BPNL00000000001");
        final String quantity_number = input.getOrDefault("quantity_number", "02. Mai");
        final String measurement_unit = input.getOrDefault("measurement_unit", "litre");
        final String created_on = input.getOrDefault("created_on", "2022-02-03T14:48:54.709Z");
        final String classification = input.getOrDefault("classification", "component");

        return AspectRelationship.builder()
                .parentPartInstanceId(parent_part_instance_id)
                .parentManufacturerPartId(parent_manufacturer_part_id)
                .childPartInstanceId(part_instance_id)
                .childManufacturerPartId(manufacturer_part_id)
                .childManufacturerId(manufacturer_id)
                .quantityNumber(quantity_number)
                .measurementUnit(measurement_unit)
                .createdOn(created_on)
                .childManufacturerId(classification)
                .build();
    }

    public static PartSiteInformationAsPlanned buildPartSiteInfoAsPlannedRowData(Map<String, String> input) {
        final String uuid = input.getOrDefault("uuid", "urn:uuid:580d3adf-1981-44a0-a214-13d6ceed9379");
        final String manufacturer_part_id = input.getOrDefault("manufacturer_part_id", "37754B7-76");
        final String catenax_site_id = input.getOrDefault("catenax_site_id", "BPNS1234567890ZZ");
        final String name_at_manufacturer = input.getOrDefault("name_at_manufacturer", "Sensor");
        final String function = input.getOrDefault("function", "production");
        final String function_valid_from = input.getOrDefault("function_valid_from", "2022-11-28T09:39:44.673Z");
        final String function_valid_until = input.getOrDefault("function_valid_until", "2022-11-28T09:39:44.673Z");

        return PartSiteInformationAsPlanned.builder()
                .uuid(uuid)
                .manufacturerPartId(manufacturer_part_id)
                .catenaXSiteId(catenax_site_id)
                .nameAtManufacturer(name_at_manufacturer)
                .function(function)
                .functionValidFrom(function_valid_from)
                .functionValidUntil(function_valid_until)
                .build();
    }

    public static PcfAspect buildPcfRowData(Map<String, String> input) {
        final String id = input.getOrDefault("id", "3893bb5d-da16-4dc1-9185-11d97476c254");
        final String companyName = input.getOrDefault("companyName", "My Corp");
        final String companyId = input.getOrDefault("companyId", "urn:uuid:51131FB5-42A2-4267-A402-0ECFEFAD1619");
        final String specVersion = input.getOrDefault("specVersion", "2.0.1-20230314");
        final String partialFullPcf = input.getOrDefault("partialFullPcf", "Cradle-to-gate");
        final String precedingPfId = input.getOrDefault("precedingPfId", "3893bb5d-da16-4dc1-9185-11d97476c254");
        final String version = input.getOrDefault("version", "0");
        final String created = input.getOrDefault("created", "2022-05-22T21:47:32Z");
        final String extWBCSD_pfStatus = input.getOrDefault("extWBCSD_pfStatus", "Active");
        final String productId = input.getOrDefault("productId", "urn:gtin:4712345060507");
        final String productName = input.getOrDefault("productName", "My Product Name");
        final String declaredUnit = input.getOrDefault("declaredUnit", "liter");
        final String unitaryProductAmount = input.getOrDefault("unitaryProductAmount", "1000.0");
        final String productMassPerDeclaredUnit = input.getOrDefault("productMassPerDeclaredUnit", "0.456");
        final String exemptedEmissionsPercent = input.getOrDefault("exemptedEmissionsPercent", "0.0");
        final String referencePeriodStart = input.getOrDefault("referencePeriodStart", "2022-01-01T00:00:01Z");
        final String referencePeriodEnd = input.getOrDefault("referencePeriodEnd", "2022-12-31T23:59:59Z");
        final String crossSectoralStandard = input.getOrDefault("crossSectoralStandard", "GHG Protocol Product standard");
        final String extWBCSD_operator = input.getOrDefault("extWBCSD_operator", "PEF");
        final String ruleName = input.getOrDefault("ruleName", "urn:tfs-initiative.com:PCR:The Product Carbon Footprint Guideline for the Chemical Industry:version:v2.0");
        final String secondaryEmissionFactorSource = input.getOrDefault("secondaryEmissionFactorSource", "ecoinvent 3.8");
        final String pcfExcludingBiogenic = input.getOrDefault("pcfExcludingBiogenic", "2.0");
        final String extWBCSD_packagingGhgEmissions = input.getOrDefault("extWBCSD_packagingGhgEmissions", "01. Mai");
        final String assetLifeCyclePhase = input.getOrDefault("assetLifeCyclePhase", "AsPlanned");
        final String extWBCSD_productCodeCpc = input.getOrDefault("extWBCSD_productCodeCpc", "011-99000");
        final String extWBCSD_characterizationFactors = input.getOrDefault("extWBCSD_characterizationFactors", "AR5");
        final String extWBCSD_allocationRulesDescription = input.getOrDefault("extWBCSD_allocationRulesDescription", "In accordance with Catena-X PCF Rulebook");
        final String extTFS_allocationWasteIncineration = input.getOrDefault("extTFS_allocationWasteIncineration", "cut-off");
        final String extWBCSD_packagingEmissionsIncluded = input.getOrDefault("extWBCSD_packagingEmissionsIncluded", "true");

        return PcfAspect.builder()
                .id(id)
                .specVersion(specVersion)
                .companyId(companyId)
                .companyName(companyName)
                .created(created)
                .partialFullPcf(partialFullPcf)
                .extWBCSDProductCodeCpc(extWBCSD_productCodeCpc)
                .version(version)
                .extWBCSDPfStatus(extWBCSD_pfStatus)
                .productId(productId)
                .productName(productName)
                .declaredUnit(declaredUnit)
                .unitaryProductAmount(unitaryProductAmount)
                .ruleName(ruleName)
                .extWBCSDOperator(extWBCSD_operator)
                .productMassPerDeclaredUnit(productMassPerDeclaredUnit)
                .exemptedEmissionsPercent(exemptedEmissionsPercent)
                .referencePeriodEnd(referencePeriodEnd)
                .referencePeriodStart(referencePeriodStart)
                .crossSectoralStandard(crossSectoralStandard)
                .extWBCSDCharacterizationFactors(extWBCSD_characterizationFactors)
                .extTFSAllocationWasteIncineration(extTFS_allocationWasteIncineration)
                .extWBCSDAllocationRulesDescription(extWBCSD_allocationRulesDescription)
                .extWBCSDPackagingEmissionsIncluded(Boolean.parseBoolean(extWBCSD_packagingEmissionsIncluded))
                .pcfExcludingBiogenic(pcfExcludingBiogenic)
                .extWBCSDPackagingGhgEmissions(extWBCSD_packagingGhgEmissions)
                .precedingPfId(precedingPfId)
                .secondaryEmissionFactorSource(secondaryEmissionFactorSource)
                .assetLifeCyclePhase(assetLifeCyclePhase)
                .build();
    }

    // Serial Part
    public static Aspect buildSerialPartTypizationRowData(Map<String, String> input) {
        final String uuid = input.getOrDefault("uuid", "urn:uuid:8eea5f45-0823-48ce-a4fc-c3bf1cdfa4c2");
        final String partInstanceId = input.getOrDefault("part_instance_id", "NO-159040131155901488695376");
        final String manufacturingDate = input.getOrDefault("manufacturing_date", "2022-02-04T14:48:54.709Z");
        final String manufacturingCountry = input.getOrDefault("manufacturing_country", "DEU");
        final String manufacturerPartId = input.getOrDefault("manufacturer_part_id", "37754B7-76");
        final String customerPartId = input.getOrDefault("customer_part_id", "37754B7-76");
        final String classification = input.getOrDefault("classification", "component");
        final String nameAtManufacturer = input.getOrDefault("name_at_manufacturer", "Sensor");
        final String nameAtCustomer = input.getOrDefault("name_at_customer", "Sensor");

        return Aspect.builder()
                .uuid(uuid)
                .partInstanceId(partInstanceId)
                .manufacturingDate(manufacturingDate)
                .manufacturingCountry(manufacturingCountry)
                .manufacturerPartId(manufacturerPartId)
                .customerPartId(customerPartId)
                .classification(classification)
                .nameAtManufacturer(nameAtManufacturer)
                .nameAtCustomer(nameAtCustomer)
                .build();
    }

    public static SingleLevelBoMAsPlanned buildSingleLevelBomAsPlannedRowData(Map<String, String> input) {
        final String parentUuid = input.getOrDefault("parent_uuid", "urn:uuid:055c1128-0375-47c8-98de-7cf802c3241d");
        final String parentManufacturerPartId = input.getOrDefault("parent_manufacturer_part_id", "37754B7-76");
        final String uuid = input.getOrDefault("uuid", "urn:uuid:5daB938E-Cafa-92B3-7ca1-9aD7885e9dC8");
        final String manufacturerPartId = input.getOrDefault("manufacturer_part_id", "37754B7-76");
        final String customerPartId = input.getOrDefault("customer_part_id", "AsPlanned");
        final String quantityNumber = input.getOrDefault("quantity_number", "02. Mai");
        final String measurementUnitLexicalValue = input.getOrDefault("measurement_unit_lexical_value", "litre");
        final String datatypeUri = input.getOrDefault("datatype_uri", "urn:bamm:io.openmanufacturing:meta-model:1.0.0#curie");
        final String createdOn = input.getOrDefault("created_on", "2022-02-03T14:48:54.709Z");
        final String lastModifiedOn = input.getOrDefault("last_modified_on", "2022-02-03T14:48:54.709Z");

        return SingleLevelBoMAsPlanned.builder()
                .parentUuid(parentUuid)
                .parentManufacturerPartId(parentManufacturerPartId)
                .childUuid(uuid)
                .childManufacturerPartId(manufacturerPartId)
                .customerPartId(customerPartId)
                .quantityNumber(quantityNumber)
                .measurementUnitLexicalValue(measurementUnitLexicalValue)
                .datatypeURI(datatypeUri)
                .createdOn(createdOn)
                .lastModifiedOn(lastModifiedOn)
                .build();
    }

    public static ArrayList<String> buildBpnNumbers(Map<String, String> input) {
        final ArrayList<String> numbers = new ArrayList<>();
        final String bpn_number = input.getOrDefault("bpn_number", "BPNL000000000001");
        numbers.add(bpn_number);
        return numbers;
    }
}