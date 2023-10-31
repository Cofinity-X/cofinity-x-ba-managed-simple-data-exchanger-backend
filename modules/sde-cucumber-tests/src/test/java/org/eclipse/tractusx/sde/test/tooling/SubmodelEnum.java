package org.eclipse.tractusx.sde.test.tooling;

public enum SubmodelEnum {
    BATCH("batch"),
    PCF("pcf"),
    ASSEMBLY_PART_RELATIONSHIP("assembly-part-relationship"),
    PART_AS_PLANNED("part-as-planned"),
    PART_SITE_INFORMATION_AS_PLANNED("part-site-information-as-planned"),
    SERIAL_PART_TYPIZATION("serial-part-typization"),
    SINGLE_LEVEL_BOM_AS_PLANNED("single-level-bom-as-planned"),
    SINGLE_LEVEL_USAGE_AS_BUILT("single-level-usage-as-built");

    private final String name;

    private SubmodelEnum(String value) {
        this.name = value;
    }

    public static SubmodelEnum getEnumByString(String value){
        for(SubmodelEnum e : SubmodelEnum.values()){
            if(e.name.equals(value)) return e;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
