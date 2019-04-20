package de.arindy.sharv.gui;

import de.arindy.sharv.controller.DefaultPersistentCharacter;
import de.arindy.sharv.controller.PersistentCharacter;
import javafx.stage.FileChooser;

import javax.inject.Inject;
import java.io.File;

public class PersistAction {

    private PersistentCharacter persistentCharacter;
    private File characterFile;

    public PersistAction() {
        this.persistentCharacter = new DefaultPersistentCharacter();
    }

    public void loadCharacter() {
        persistentCharacter.load(
                characterFile = fileChooser().showOpenDialog(null)
        );
    }

    public boolean saveCharacter() {
        persistentCharacter.save(
                characterFile = fileChooser().showSaveDialog(null)
        );
        return characterFile != null;
    }

    private FileChooser fileChooser() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Character", "*.sharV"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
        );
        if (characterFile != null && characterFile.exists()) {
            fileChooser.setInitialDirectory(characterFile.getParentFile());
            fileChooser.setInitialFileName(characterFile.getName());
        } else {
            fileChooser.setInitialFileName("sharVCharacter.sharV");
        }
        return fileChooser;
    }

    @Inject
    public PersistAction withPersistentCharacter(final PersistentCharacter persistentCharacter) {
        this.persistentCharacter = persistentCharacter;
        return this;
    }

}
