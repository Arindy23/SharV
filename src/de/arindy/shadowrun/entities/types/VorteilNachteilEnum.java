package de.arindy.shadowrun.entities.types;

public enum VorteilNachteilEnum {

    VORTEIL("Vorteil"),
    NACHTEIL("Nachteil");

    private final String value;

    VorteilNachteilEnum(String s) {
        value = s;
    }

    public static VorteilNachteilEnum fromValue(final String value) {
        if (value != null) {
            for (VorteilNachteilEnum type : VorteilNachteilEnum.values()) {
                if (value.equalsIgnoreCase(type.value)) {
                    return type;
                }
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
