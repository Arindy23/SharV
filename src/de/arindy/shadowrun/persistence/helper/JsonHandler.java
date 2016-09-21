package de.arindy.shadowrun.persistence.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

/**
 * Created by arindy on 15.05.16.
 */
public class JsonHandler {

    public static Object readFile(File file, Class T) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object castedClass = T.newInstance();
            return castedClass.getClass().cast(mapper.readValue(file, T));
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeFile(File file, Object o) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
