package de.arindy.sharv.persistence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.arindy.sharv.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by arindy on 15.05.16.
 */
public class JsonHandler {

    private final Logger LOG = Logger.get(getClass().getSimpleName());
    private final File jarPath;

    public JsonHandler(Class<?> clazz) {
        File file;
        try {
            file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            file = new File("");
        }
        jarPath = file;
    }

    public Collection<String> availableModules() {
        final Collection<String> result = new HashSet<>();
        final File[] files = getLanguage().listFiles();
        if (files != null) {
            for (File file : files) {
                result.add(file.getName());
            }
        }
        return result;
    }

    public <T> Map<String, Collection<T>> readFiles(Class<T> T) {
        final Map<String, Collection<T>> result = new HashMap<>();
        final File language = getLanguage();
        if (language.exists()) {
            try {
                final Collection<T> data = new HashSet<>();
                Files.newDirectoryStream(language.toPath()).forEach(source -> {
                    try {
                        Files.newDirectoryStream(new File(source.toFile(), T.getSimpleName()).toPath()).forEach(t -> {
                            try {
                                data.add(readFile(t.toFile(), T));
                            } catch (IOException e) {
                                LOG.error(String.format("In file: %s : %s", t, e.getMessage()));
                            }
                        });
                    } catch (IOException e) {
                        LOG.error(String.format("No Data found: %s", T.getSimpleName()));
                    }
                    result.put(source.getFileName().toString(), data);
                });
            } catch (IOException e) {
                LOG.error(String.format("No Data found for: %s", language));
            }
        }
        return result;
    }

    private File getLanguage() {
        File result= new File(jarPath, String.format("data/%s", Locale.getDefault().getLanguage()));
        if (!result.exists()) {
            result = new File(jarPath, String.format("data/data/%s", Locale.getDefault().getLanguage()));
        }
        return result;
    }

    public static <T> T readFile(File file, Class<T> T) throws IOException {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(file, T);
    }

    public static void writeFile(File file, Object o) throws IOException {
        new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .writeValue(file, o);
    }
}
