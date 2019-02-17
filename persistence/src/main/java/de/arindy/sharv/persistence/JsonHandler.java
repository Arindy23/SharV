package de.arindy.sharv.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

/**
 * Created by arindy on 15.05.16.
 */
public class JsonHandler {

    public static <T> T readFile(File file, Class<T> T) throws IOException {
        return new ObjectMapper().readValue(file, T);
    }

    public static void writeFile(File file, Object o) throws IOException {
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValue(file, o);
    }
}
