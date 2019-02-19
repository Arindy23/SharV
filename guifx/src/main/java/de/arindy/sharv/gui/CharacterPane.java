package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.AttributesView;
import de.arindy.sharv.api.gui.CharacterView;
import de.arindy.sharv.api.gui.PersonalDataView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CharacterPane extends VBox implements CharacterView {

    @FXML
    private PersonalDataView personalData;
    @FXML
    private AttributesView attributes;

    public CharacterPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/character.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public PersonalDataView getPersonalData() {
        return personalData;
    }

    @Override
    public AttributesView getAttributes() {
        return attributes;
    }

}
