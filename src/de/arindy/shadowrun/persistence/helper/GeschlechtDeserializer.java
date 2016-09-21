package de.arindy.shadowrun.persistence.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.arindy.shadowrun.entities.types.Geschlecht;

import java.io.IOException;

/**
 * Created by arindy on 14.05.16.
 */
public class GeschlechtDeserializer extends JsonDeserializer {
    @Override
    public Geschlecht deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Geschlecht type = Geschlecht.fromValue(jsonParser.getValueAsString());
        if (type != null) {
            return type;
        }
        return null;
    }
}
