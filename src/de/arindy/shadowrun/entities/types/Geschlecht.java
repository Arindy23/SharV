package de.arindy.shadowrun.entities.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.arindy.shadowrun.persistence.helper.GeschlechtDeserializer;
import de.arindy.shadowrun.persistence.helper.GeschlechtSerializer;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = GeschlechtDeserializer.class)
@JsonSerialize(using = GeschlechtSerializer.class)
@JsonRootName("geschlecht")
public enum Geschlecht {

    WEIBLICH("weiblich"),
    MAENLICH("männlich");

    private final String value;

    Geschlecht(String s) {
        value = s;
    }

    public static Geschlecht fromValue(final String value) {
        if (value != null) {
            for (Geschlecht type : Geschlecht.values()) {
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
