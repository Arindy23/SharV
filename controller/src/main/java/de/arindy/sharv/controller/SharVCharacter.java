package de.arindy.sharv.controller;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.*;
import de.arindy.sharv.character.Character;
import de.arindy.sharv.persistence.JsonHandler;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;

@ApplicationScoped
public class SharVCharacter implements PersonalDataListener, AttributesListener, ConditionMonitorListener, MenuListener {

    private final Logger LOG;

    private Character character;

    private PersonalDataView personalDataView;
    private AttributesView attributesView;
    private ConditionMonitorView conditionMonitorView;
    private MenuView menuView;

    private SharVCharacter() {
        character = new Character();
        LOG = Logger.get(getClass().getName());
    }

    @Override
    public PersonalDataListener register(final PersonalDataView personalDataView) {
        this.personalDataView = personalDataView;
        return this;
    }

    public SharVCharacter register(final AttributesView attributesView) {
        this.attributesView = attributesView;
        return this;
    }

    public SharVCharacter register(final ConditionMonitorView conditionMonitorView) {
        this.conditionMonitorView = conditionMonitorView;
        return this;
    }

    public SharVCharacter register(final MenuView menuView) {
        this.menuView = menuView;
        return this;
    }

    @Override
    public void changeName(String name) {

    }

    @Override
    public void changeStreetname(String streetname) {

    }

    @Override
    public void changeMetatype(String metatype) {

    }

    @Override
    public void changeSex(String sex) {

    }

    @Override
    public void changeAge(int age) {

    }

    @Override
    public void changeHeight(int height) {

    }

    @Override
    public void changeWeight(int weight) {

    }

    @Override
    public void changeEthnicity(String ethnicity) {

    }

    @Override
    public void changeConcept(String concept) {

    }

    @Override
    public void changeStreetCred(int streetCred) {

    }

    @Override
    public void changeNotoriety(int notoriety) {

    }

    @Override
    public void changePublicAwareness(int publicAwareness) {

    }

    @Override
    public void changeBody(int body) {
        LOG.entering(body);
        character.getAttributes().getPhysical().setBody(body);
        conditionMonitorView.setPhysicalDamageMax(Calculator.calculateDamageMax(body));
    }

    @Override
    public void changeAgility(int agility) {
        LOG.entering(agility);
        character.getAttributes().getPhysical().setAgility(agility);
    }

    @Override
    public void changeReaction(int reaction) {

    }

    @Override
    public void changeStrength(int strength) {

    }

    @Override
    public void changeWillpower(int willpower) {
        LOG.entering(willpower);
        character.getAttributes().getMental().setWillpower(willpower);
        conditionMonitorView.setStunDamageMax(Calculator.calculateDamageMax(willpower));
    }

    @Override
    public void changeLogic(int logic) {

    }

    @Override
    public void changeIntuition(int intuition) {

    }

    @Override
    public void changeCharisma(int charisma) {

    }

    @Override
    public void changeSpecial(String special) {

    }

    @Override
    public void changeSpecialValue(int specialValue) {

    }

    @Override
    public void changeEdge(int edge) {

    }

    @Override
    public void changeBurnedEdge(int burnedEdge) {

    }

    @Override
    public void changePhysicalDamage(int physicalDamage) {
        LOG.entering(physicalDamage);
        character.setPhysicalDamage(physicalDamage);
        conditionMonitorView.setDicePoolModifier(Calculator.calculateDicePoolModifier(character));
    }

    @Override
    public void changeStunDamage(int stunDamage) {
        LOG.entering(stunDamage);
        character.setStunDamage(stunDamage);
        conditionMonitorView.setDicePoolModifier(Calculator.calculateDicePoolModifier(character));
    }

    @Override
    public void changeColor(String color) {
        LOG.entering(color);
        character.setHighlightColor(color);
    }

    @Override
    public void load(final File file) {
        LOG.entering(file);
        try {
            if (file != null) {
                character = JsonHandler.readFile(file, Character.class);
                menuView.setHighlightColor(character.getHighlightColor());
                menuView.setCharacterFile(file);
                LOG.debug(String.format("Character loaded: %s", character));
            }
        } catch (IOException e) {
            LOG.exception(e.getMessage(), e);
        }
    }

    @Override
    public void save(final File file) {
        LOG.entering(file);
        try {
            if (file != null) {
                JsonHandler.writeFile(file, character);
                menuView.setCharacterFile(file);
                LOG.debug(String.format("Character written: %s", file.getAbsolutePath()));
            }
        } catch (IOException e) {
            LOG.exception(e.getMessage(), e);
        }
    }
}
