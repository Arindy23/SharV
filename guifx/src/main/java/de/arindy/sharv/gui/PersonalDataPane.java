package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.DefaultPersonalDataListener;
import de.arindy.sharv.api.gui.PersonalDataListener;
import de.arindy.sharv.api.gui.PersonalDataView;
import de.arindy.sharv.character.Metatype;
import de.arindy.sharv.character.Sex;
import de.arindy.sharv.gui.jfx.BorderedTitledPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formattable;
import java.util.HashSet;

import static de.arindy.sharv.gui.JavaFXUtil.*;

@Singleton
public class PersonalDataPane implements PersonalDataView {

    private final Logger LOG;

    @FXML
    private BorderedTitledPane root;

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
    private ComboBox<Metatype> metatype;
    @FXML
    private Label karma;
    @FXML
    private Label karmaMax;
    @FXML
    private Label nuyen;
    @FXML
    private ComboBox<Sex> sex;
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
        LOG = Logger.get(getClass().getName());
        personalDataListener = new DefaultPersonalDataListener();
    }

    @Inject
    public PersonalDataPane withPersonalDataListener(final PersonalDataListener personalDataListener) {
        this.personalDataListener = personalDataListener.register(this);
        return this;
    }

    public void initialize() {
        personalDataListener.initializePersonalData();
    }

    public void onName(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        final String name = extractText(inputMethodEvent);
        personalDataListener.changeName(name);
        updateTitle();
    }

    public void onStreetname(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeStreetname(extractText(inputMethodEvent));
        updateTitle();
    }

    public void onMetatype() {
        LOG.entering();
        final Metatype selectedItem = getSelectedItem(metatype);
        if (selectedItem != null) {
            personalDataListener.changeMetatype(selectedItem);
        }
    }

    public void onSex() {
        LOG.entering();
        Sex selectedItem = getSelectedItem(sex);
        if (selectedItem != null) {
            personalDataListener.changeSex(selectedItem);
        }
    }

    public void onAge(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeAge(extractInteger(inputMethodEvent));
    }

    public void onHeight(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeHeight(extractInteger(inputMethodEvent));
    }

    public void onWeight(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeWeight(extractInteger(inputMethodEvent));
    }

    public void onEthnicity(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeEthnicity(extractText(inputMethodEvent));
    }

    public void onConcept(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeConcept(extractText(inputMethodEvent));
    }

    public void onStreetCred(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeStreetCred(extractInteger(inputMethodEvent));
    }

    public void onNotoriety(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changeNotoriety(extractInteger(inputMethodEvent));
    }

    public void onPublicAwareness(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        personalDataListener.changePublicAwareness(extractInteger(inputMethodEvent));
    }

    @Override
    public PersonalDataView setName(final String name) {
        LOG.entering(name);
        this.name.setText(name);
        updateTitle();
        return this;
    }

    @Override
    public PersonalDataView setStreetname(final String streetname) {
        LOG.entering(streetname);
        this.streetname.setText(streetname);
        updateTitle();
        return this;
    }

    @Override
    public PersonalDataView setMetatype(final Metatype metatype) {
        LOG.entering(metatype);
        if (this.metatype.getItems().contains(metatype)) {
            this.metatype.getSelectionModel().select(metatype);
        } else {
            LOG.warning(warnNotAvailable(metatype));
            this.metatype.getSelectionModel().selectFirst();
        }
        return this;
    }

    @Override
    public PersonalDataView setSex(final Sex sex) {
        LOG.entering(sex);
        if (this.sex.getItems().contains(sex)) {
            this.sex.getSelectionModel().select(sex);
        } else {
            LOG.warning(warnNotAvailable(sex));
            this.sex.getSelectionModel().selectFirst();
        }
        return this;
    }

    private String warnNotAvailable(Formattable formattable) {
        return String.format("%s not available! Selecting first in List!", formattable);
    }

    @Override
    public PersonalDataView setAge(int age) {
        LOG.entering(age);
        this.age.setText(String.valueOf(age));
        return this;
    }

    @Override
    public PersonalDataView setHeight(int height) {
        LOG.entering(height);
        this.height.setText(String.valueOf(height));
        return this;
    }

    @Override
    public PersonalDataView setWeight(int weight) {
        LOG.entering(weight);
        this.weight.setText(String.valueOf(weight));
        return this;
    }

    @Override
    public PersonalDataView setEthnicity(final String ethnicity) {
        LOG.entering(ethnicity);
        this.ethnicity.setText(ethnicity);
        return this;
    }

    @Override
    public PersonalDataView setConcept(final String concept) {
        LOG.entering(concept);
        this.concept.setText(concept);
        return this;
    }

    @Override
    public PersonalDataView setStreetCred(int streetCred) {
        LOG.entering(streetCred);
        this.streetCred.setText(String.valueOf(streetCred));
        return this;
    }

    @Override
    public PersonalDataView setNotoriety(int notoriety) {
        LOG.entering(notoriety);
        this.notoriety.setText(String.valueOf(notoriety));
        return this;
    }

    @Override
    public PersonalDataView setPublicAwareness(int publicAwareness) {
        LOG.entering(publicAwareness);
        this.publicAwareness.setText(String.valueOf(publicAwareness));
        return this;
    }

    @Override
    public PersonalDataView addRacialBonuses(final String... bonuses) {
        LOG.entering((Object[]) bonuses);
        for (String bonus : bonuses) {
            final ObservableList<Node> racialBonuses = this.racialBonuses.getChildren();
            if (!racialBonuses.isEmpty()) {
                racialBonuses.add(new Text(String.format("%n")));
            }
            racialBonuses.add(new Text(bonus));
        }
        return this;
    }

    @Override
    public PersonalDataView removeAllRacialBonuses() {
        LOG.entering();
        racialBonuses.getChildren().clear();
        return this;
    }

    @Override
    public PersonalDataView setKarma(int karma) {
        LOG.entering(karma);
        this.karma.setText(String.valueOf(karma));
        return this;
    }

    @Override
    public PersonalDataView setKarmaMax(int karmaMax) {
        LOG.entering(karmaMax);
        this.karmaMax.setText(String.valueOf(karmaMax));
        return this;
    }

    @Override
    public PersonalDataView setNuyen(int nuyen) {
        LOG.entering(nuyen);
        this.nuyen.setText(String.valueOf(nuyen));
        return this;
    }

    @Override
    public PersonalDataView addSexes(final Sex... sexes) {
        LOG.entering(sexes);
        this.sex.getItems().addAll(sexes);
        return this;
    }

    @Override
    public PersonalDataView removeAllSexes() {
        LOG.entering();
        this.sex.getItems().clear();
        return this;
    }

    @Override
    public PersonalDataView addMetatypes(final Metatype... metatypes) {
        LOG.entering(metatypes);
        final Metatype selectedItem = this.metatype.getSelectionModel().getSelectedItem();
        final Collection<Metatype> hashSet = new HashSet<>(Arrays.asList(metatypes));
        hashSet.addAll(this.metatype.getItems());
        removeMetatypes();
        this.metatype.getItems().addAll(FXCollections.observableArrayList(hashSet).sorted());
        this.metatype.getSelectionModel().select(selectedItem);
        return this;
    }

    @Override
    public PersonalDataView removeMetatypes() {
        LOG.entering();
        this.metatype.getItems().clear();
        return this;
    }

    @Override
    public void updateTitle() {
        final String prefix;
        if (streetname.getText() == null || streetname.getText().isEmpty()) {
            prefix = name.getText();
        } else {
            prefix = streetname.getText();
        }
        final String titlePrefix;
        if (prefix == null || prefix.isEmpty()) {
            titlePrefix = "";
        } else {
            titlePrefix = String.format("%s - ", prefix);
        }
        ((Stage) root.getScene().getWindow()).setTitle(String.format("%s%s", titlePrefix, SharVGUI.TITLE));
    }

}
