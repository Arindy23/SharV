package de.arindy.sharv.character.json;

import de.arindy.sharv.Language;

public enum VorteilNachteilEnum {

    VORTEIL("v"),
    NACHTEIL("n");

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
        return Language.getString("vnenum." + value);
    }
}
