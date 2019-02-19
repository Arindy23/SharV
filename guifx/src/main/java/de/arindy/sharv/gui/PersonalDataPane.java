package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.PersonalDataView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalDataPane implements PersonalDataView, Initializable {

    private final Logger LOG = Logger.get(getClass().getName());

    //<editor-fold desc="Variables">
    public TextFlow racialBonuses;
    public TextField name;
    public TextField streetname;
    public TextField publicAwareness;
    public ComboBox metatype;
    public Label karma;
    public Label karmaMax;
    public Label nuyen;
    public ComboBox<String> sex;
    public TextField age;
    public TextField height;
    public TextField weight;
    public TextField ethnicity;
    public TextField concept;
    public TextField streetCred;
    public TextField notoriety;
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sex.getItems().add("female");
        sex.getItems().add("male");
    }

    @Override
    public void addRacialBonuses(final String... bonuses) {
        LOG.entering((Object[]) bonuses);
        for (String bonus : bonuses) {
            final ObservableList<Node> racialBonuses = this.racialBonuses.getChildren();
            if (!racialBonuses.isEmpty()) {
                racialBonuses.add(new Text(String.format("%n")));
            }
            racialBonuses.add(new Text(bonus));
        }
    }

    @Override
    public void removeAllRacialBonuses() {
        LOG.entering();
        racialBonuses.getChildren().clear();
    }

    public void onName(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onStreetname(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onMetatype(final ActionEvent actionEvent) {
        LOG.entering(actionEvent);
    }

    public void onSex(final ActionEvent actionEvent) {
        LOG.entering(actionEvent);
        LOG.debug("sex selected = " + JavaFXUtil.getSelectedItem(sex));
    }

    public void onAge(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onHeight(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onWeight(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onEthnicity(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onConcept(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onStreetCred(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onNotoriety(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onPublicAwareness(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    @Override
    public PersonalDataView setKarma(int karma) {
        this.karma.setText(String.valueOf(karma));
        return this;
    }

    @Override
    public PersonalDataView setKarmaMax(int karmaMax) {
        this.karmaMax.setText(String.valueOf(karmaMax));
        return this;
    }

    @Override
    public PersonalDataView setNuyen(int nuyen) {
        this.nuyen.setText(String.valueOf(nuyen));
        return this;
    }

}
