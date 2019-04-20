package de.arindy.sharv.persistence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.arindy.sharv.Logger;
import de.arindy.sharv.character.Character;
import de.arindy.sharv.persistence.mapper.PersistenceMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by arindy on 15.05.16.
 */
public class SharvJSONPersistenceHandler {

    private final Logger LOG = Logger.get(getClass().getName());
    private final File root;

    public SharvJSONPersistenceHandler(final File root) {
        this.root = root;
    }

    public Collection<String> availableModules() {
        LOG.entering();
        final Collection<String> result = new HashSet<>();
        final File[] files = getDataForLanguage().listFiles();
        if (files != null) {
            for (File file : files) {
                result.add(file.getName());
            }
        }
        return LOG.returning(result);
    }

    public <T> T readFile(final String source, final String id, Class<T> target) throws ReflectiveOperationException {
        LOG.entering(source, id, target);
        T result;
        final File language = getDataForLanguage();
        try {
            if (language.exists() && id != null) {
                result = readFile(new File(language, String.format("%s/%s/%s", source, target.getSimpleName(), id)), target);
            } else {
                result = target.newInstance();
            }
        } catch (IOException e) {
            result = target.newInstance();
        }
        return LOG.returning(result);
    }

    public <T> Map<String, Collection<T>> readFiles(Class<T> target) {
        LOG.entering(target);
        final Map<String, Collection<T>> result = new HashMap<>();
        final File language = getDataForLanguage();
        if (language.exists()) {
            try (DirectoryStream<Path> sources = Files.newDirectoryStream(language.toPath())) {
                sources.forEach(source -> {
                    final Collection<T> dataSet = new HashSet<>();
                    try (DirectoryStream<Path> data = Files.newDirectoryStream(new File(source.toFile(), target.getSimpleName()).toPath())) {
                        data.forEach(t -> {
                            try {
                                dataSet.add(readFile(t.toFile(), target));
                            } catch (IOException e) {
                                LOG.error(String.format("In file: %s : %s", t, e.getMessage()));
                            }
                        });
                    } catch (IOException e) {
                        LOG.error(String.format("No Data found: %s/%s", source.getFileName(),target.getSimpleName()));
                    }
                    result.put(source.getFileName().toString(), dataSet);
                });
            } catch (IOException e) {
                LOG.error(String.format("No Data found for: %s", language));
            }
        }
        return LOG.returning(result);
    }

    public Character loadChar(final File file) throws IOException {
        return PersistenceMapper.MAPPER.map(
                readFile(file, de.arindy.sharv.persistence.character.Character.class),
                this
        );
    }

    public void saveChar(final File file, Character character) throws IOException {
        writeFile(file, PersistenceMapper.MAPPER.map(character));
    }

    private File getDataForLanguage() {
        LOG.entering();
        File result = new File(root, String.format("data/%s", Locale.getDefault().getLanguage()));
        if (!result.exists()) {
            result = new File(root, String.format("data/data/%s", Locale.getDefault().getLanguage()));
        }
        return LOG.returning(result);
    }

    private static <T> T readFile(File file, Class<T> T) throws IOException {
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
