package de.arindy.sharv.character.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.arindy.sharv.character.MagRes;

import java.io.IOException;

/**
 * Created by arindy on 14.05.16.
 */
public class MagResSerializer extends JsonSerializer<MagRes> {

    @Override
    public void serialize(MagRes type, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(type.getValue());
    }
}