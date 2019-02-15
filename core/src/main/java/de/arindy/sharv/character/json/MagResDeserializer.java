package de.arindy.sharv.character.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.arindy.sharv.character.MagRes;

import java.io.IOException;

/**
 * Created by arindy on 14.05.16.
 */
public class MagResDeserializer extends JsonDeserializer {
    @Override
    public MagRes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MagRes type = MagRes.fromValue(jsonParser.getValueAsString());
        if (type != null) {
            return type;
        }
        return MagRes.NONE;
    }
}
