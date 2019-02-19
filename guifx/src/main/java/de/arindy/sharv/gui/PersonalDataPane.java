package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.PersonalDataListener;
import de.arindy.sharv.api.gui.PersonalDataView;
import de.arindy.sharv.gui.jfx.BorderedTitledPane;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

import static de.arindy.sharv.gui.JavaFXUtil.*;

public class PersonalDataPane extends BorderedTitledPane implements PersonalDataView, Initializable {

    private final Logger LOG = Logger.get(getClass().getName());

    private PersonalDataListener personalDataListener;

    //<editor-fold desc="Variables">
    @FXML
    private TextFlow racialBonuses;
    @FXML
    private TextField name;
    @FXML
    private TextField streetname;
    @FXML
    private TextField publicAwareness;
    @FXML
    private ComboBox<String> metatype;
    @FXML
    private Label karma;
    @FXML
    private Label karmaMax;
    @FXML
    private Label nuyen;
    @FXML
    private ComboBox<String> sex;
    @FXML
    private TextField age;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField ethnicity;
    @FXML
    private TextField concept;
    @FXML
    private TextField streetCred;
    @FXML
    private TextField notoriety;
    //</editor-fold>

    public PersonalDataPane() {
        super("personalData");
    }

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
        if (listenerRegistered()) {
            personalDataListener.changeName(extractText(inputMethodEvent));
        }
    }

    public void onStreetname(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeStreetname(extractText(inputMethodEvent));
        }
    }

    public void onMetatype() {
        LOG.entering();
        if (listenerRegistered()) {
            personalDataListener.changeMetatype(getSelectedItem(metatype));
        }
    }

    public void onSex() {
        LOG.entering();
        if (listenerRegistered()) {
            personalDataListener.changeSex(getSelectedItem(sex));
        }
    }

    public void onAge(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeAge(extractText(inputMethodEvent));
        }
    }

    public void onHeight(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeHeight(extractInteger(inputMethodEvent));
        }
    }

    public void onWeight(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeWeight(extractInteger(inputMethodEvent));
        }
    }

    public void onEthnicity(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeEthnicity(extractText(inputMethodEvent));
        }
    }

    public void onConcept(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeConcept(extractText(inputMethodEvent));
        }
    }

    public void onStreetCred(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeStreetCred(extractInteger(inputMethodEvent));
        }
    }

    public void onNotoriety(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changeNotoriety(extractInteger(inputMethodEvent));
        }
    }

    public void onPublicAwareness(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            personalDataListener.changePublicAwareness(extractInteger(inputMethodEvent));
        }
    }

    @Override
    public PersonalDataView setKarma(int karma) {
        LOG.entering(karma);
        this.karma.setText(String.valueOf(karma));
        return LOG.returning(this);
    }

    @Override
    public PersonalDataView setKarmaMax(int karmaMax) {
        LOG.entering(karmaMax);
        this.karmaMax.setText(String.valueOf(karmaMax));
        return LOG.returning(this);
    }

    @Override
    public PersonalDataView setNuyen(int nuyen) {
        LOG.entering(nuyen);
        this.nuyen.setText(String.valueOf(nuyen));
        return LOG.returning(this);
    }

    @Override
    public PersonalDataView registerListener(final PersonalDataListener personalDataListener) {
        this.personalDataListener = personalDataListener;
        return LOG.returning(this);
    }

    private boolean listenerRegistered() {
        return personalDataListener != null;
    }

}
