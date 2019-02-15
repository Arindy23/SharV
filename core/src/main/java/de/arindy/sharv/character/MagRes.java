package de.arindy.sharv.character;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.arindy.sharv.Language;
import de.arindy.sharv.character.json.MagResDeserializer;
import de.arindy.sharv.character.json.MagResSerializer;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = MagResDeserializer.class)
@JsonSerialize(using = MagResSerializer.class)
@JsonRootName("magRes")
public enum MagRes {

    NONE("-"),
    MAGIE("m"),
    RESONANZ("r");

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

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value.equals(NONE.value)) {
            return value;
        } else {
            return Language.getString("magres." + value);
        }
    }
}
