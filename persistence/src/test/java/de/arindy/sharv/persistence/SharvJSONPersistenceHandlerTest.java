package de.arindy.sharv.persistence;

import de.arindy.sharv.character.Character;
import de.arindy.sharv.character.Metatype;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SharvJSONPersistenceHandlerTest {

    private SharvJSONPersistenceHandler sharvPersistenceHandler;

    @Rule
    public TemporaryFolder temporaryFolder;

    @Before
    public void before() throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        sharvPersistenceHandler = new SharvJSONPersistenceHandler(new File("src/test/resources"));
        temporaryFolder = new TemporaryFolder();
        temporaryFolder.create();
    }

    @Test
    public void shouldLoadCharacter() throws IOException {
        assertThat(
                sharvPersistenceHandler.loadChar(
                        new File("src/test/resources/testChar.sharV")
                ).getPersonalData().getName(),
                is("TestChar")
        );
    }

    @Test
    public void shouldSaveCharacter() throws IOException {
        File file = temporaryFolder.newFile();
        sharvPersistenceHandler.saveChar(file, new Character());
        assertThat(Files.readAllLines(Paths.get(file.toURI()), Charset.defaultCharset()).size(), is(not(0)));
    }

    @Test
    public void shouldLoadAllAvailableModules() {
        assertThat(sharvPersistenceHandler.availableModules(), hasItems("SR5", "RF"));
    }

    @Test
    public void shouldLoadAllFilesForClass() {
        assertThat(
                sharvPersistenceHandler.readFiles(Metatype.class).get("SR5"),
                CoreMatchers.hasItems(
                        new Metatype("testMetatypeId"),
                        new Metatype("otherTestMetatypeId")
                )
        );
    }

    @Test
    public void shouldReadCorrectFile() throws ReflectiveOperationException {
        assertThat(
                sharvPersistenceHandler.readFile("SR5", "testMetatypeId", Metatype.class),
                is(new Metatype("testMetatypeId"))
        );
    }

}
