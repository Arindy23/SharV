package de.arindy.shadowrun.entities.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.arindy.shadowrun.persistence.helper.MagResDeserializer;
import de.arindy.shadowrun.persistence.helper.MagResSerializer;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = MagResDeserializer.class)
@JsonSerialize(using = MagResSerializer.class)
@JsonRootName("magRes")
public enum MagRes {

    MAGIE("Magie"),
    RESONANZ("Resonanz"),
    NONE("-");

    private final String value;

    MagRes(String s) {
        value = s;
    }

    public static MagRes fromValue(final String value) {
        if (value != null) {
            for (MagRes type : MagRes.values()) {
                if (value.equalsIgnoreCase(type.value)) {
                    return type;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
