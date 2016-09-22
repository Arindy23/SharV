package de.arindy.shadowrun.persistence.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.arindy.shadowrun.entities.types.Metatyp;

import java.io.IOException;

/**
 * Created by arindy on 14.05.16.
 */
public class MetatypSerializer extends JsonSerializer<Metatyp> {

    @Override
    public void serialize(Metatyp type, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(type.toString());
    }
}