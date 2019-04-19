package de.arindy.sharv.controller;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.*;
import de.arindy.sharv.character.Character;
import de.arindy.sharv.character.*;
import de.arindy.sharv.persistence.SharvJSONPersistenceHandler;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static de.arindy.sharv.controller.Calculator.*;

@ApplicationScoped
public class SharVCharacter implements PersonalDataListener, AttributesListener, ConditionMonitorListener, MenuListener {

    private final Logger LOG;

    private final Map<Class<?>, Collection<Runnable>> actions = new HashMap<>();

    private Character character;

    private PersonalDataView personalDataView;
    private AttributesView attributesView;
    private ConditionMonitorView conditionMonitorView;
    private MenuView menuView;
    private SharvJSONPersistenceHandler sharvPersistenceHandler;

    private SharVCharacter() {
        character = new Character();
        LOG = Logger.get(getClass().getName());
        try {
            sharvPersistenceHandler = new SharvJSONPersistenceHandler(getRootFolder());
        } catch (URISyntaxException e) {
            sharvPersistenceHandler = new SharvJSONPersistenceHandler(new File(""));
        }
        initActions();
    }

    private void initActions() {
        actions.put(Metatype.class, metatypeActions());
    }

    private Collection<Runnable> metatypeActions() {
        final Collection<Runnable> result = new HashSet<>();

        result.add(() -> attributesView.setBodyMax(Calculator.calculateBodyMax(character)));

        return result;
    }

    private File getRootFolder() throws URISyntaxException {
        final File file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        if (!new File(file, "data").exists()) {
            return file.getParentFile();
        }
        return file;
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
        LOG.entering(name);
        character.getPersonalData().setName(name);
    }

    @Override
    public void changeStreetname(String streetname) {
        LOG.entering(streetname);
        character.getPersonalData().setStreetname(streetname);
    }

    @Override
    public void changeMetatype(Metatype metatype) {
        LOG.entering(metatype);
        character.getPersonalData().setMetatype(metatype);
        for (Runnable metatypeAction : actions.get(Metatype.class)) {
            metatypeAction.run();
        }
    }

    @Override
    public void changeSex(Sex sex) {
        LOG.entering(sex);
        character.getPersonalData().setSex(sex);
    }

    @Override
    public void changeAge(int age) {
        LOG.entering(age);
        character.getPersonalData().setAge(age);
    }

    @Override
    public void changeHeight(int height) {
        LOG.entering(height);
        character.getPersonalData().setHeight(height);
    }

    @Override
    public void changeWeight(int weight) {
        LOG.entering(weight);
        character.getPersonalData().setWeight(weight);
    }

    @Override
    public void changeEthnicity(String ethnicity) {
        LOG.entering(ethnicity);
        character.getPersonalData().setEthnicity(ethnicity);
    }

    @Override
    public void changeConcept(String concept) {
        LOG.entering(concept);
        character.getPersonalData().setConcept(concept);
    }

    @Override
    public void changeStreetCred(int streetCred) {
        LOG.entering(streetCred);
        character.getPersonalData().setStreetCred(streetCred);
    }

    @Override
    public void changeNotoriety(int notoriety) {
        LOG.entering(notoriety);
        character.getPersonalData().setNotoriety(notoriety);
    }

    @Override
    public void changePublicAwareness(int publicAwareness) {
        LOG.entering(publicAwareness);
        character.getPersonalData().setPublicAwareness(publicAwareness);
    }

    @Override
    public void initializePersonalData() {
        initMetatypes();
        initSexes();
    }

    @Override
    public void changeBody(int body) {
        LOG.entering(body);
        character.getAttributes().getPhysical().setBody(body);
        conditionMonitorView.setPhysicalDamageMax(calculatePhysicalDamageMax(character));
    }

    @Override
    public void changeAgility(int agility) {
        LOG.entering(agility);
        character.getAttributes().getPhysical().setAgility(agility);
    }

    @Override
    public void changeReaction(int reaction) {
        LOG.entering(reaction);
        character.getAttributes().getPhysical().setReaction(reaction);
    }

    @Override
    public void changeStrength(int strength) {
        LOG.entering(strength);
        character.getAttributes().getPhysical().setStrength(strength);
    }

    @Override
    public void changeWillpower(int willpower) {
        LOG.entering(willpower);
        character.getAttributes().getMental().setWillpower(willpower);
        conditionMonitorView.setStunDamageMax(calculateStunDamageMax(character));
    }

    @Override
    public void changeLogic(int logic) {
        LOG.entering(logic);
        character.getAttributes().getMental().setLogic(logic);
    }

    @Override
    public void changeIntuition(int intuition) {
        LOG.entering(intuition);
        character.getAttributes().getMental().setIntuition(intuition);
    }

    @Override
    public void changeCharisma(int charisma) {
        LOG.entering(charisma);
        character.getAttributes().getMental().setCharisma(charisma);
    }

    @Override
    public void changeSpecial(Special special) {
        LOG.entering(special);
        character.getAttributes().getSpecialAttributes().setSpecial(special);
    }

    @Override
    public void changeSpecialValue(int specialValue) {
        LOG.entering(specialValue);
        character.getAttributes().getSpecialAttributes().setSpecialValue(specialValue);
    }

    @Override
    public void changeEdge(int edge) {
        LOG.entering(edge);
        character.getAttributes().getSpecialAttributes().setEdge(edge);
    }

    @Override
    public void changeBurnedEdge(int burnedEdge) {
        LOG.entering(burnedEdge);
        character.getAttributes().getSpecialAttributes().setBurnedEdge(burnedEdge);
    }

    @Override
    public void changePhysicalDamage(int physicalDamage) {
        LOG.entering(physicalDamage);
        character.setPhysicalDamage(physicalDamage);
        conditionMonitorView.setDicePoolModifier(calculateDicePoolModifier(character));
    }

    @Override
    public void changeStunDamage(int stunDamage) {
        LOG.entering(stunDamage);
        character.setStunDamage(stunDamage);
        conditionMonitorView.setDicePoolModifier(calculateDicePoolModifier(character));
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
                character = sharvPersistenceHandler.loadChar(file);
                load(character);
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
                sharvPersistenceHandler.saveChar(file, character);
                menuView.setCharacterFile(file);
                LOG.debug(String.format("Character written: %s", file.getAbsolutePath()));
            }
        } catch (IOException e) {
            LOG.exception(e.getMessage(), e);
        }
    }

    @Override
    public void initializeMenu() {
        menuView.addAvailableModules(sharvPersistenceHandler.availableModules().toArray(new String[0]));
        menuView.setActivatedModules("SR5");
    }

    @Override
    public void activateModule(String module) {
        LOG.entering(module);
        character.getActiveModules().add(module);
    }

    @Override
    public void deactivateModule(String module) {
        LOG.entering(module);
        character.getActiveModules().remove(module);
    }

    private void load(final Character character) {
        initializePersonalData();
        menuView.setActivatedModules(character.getActiveModules().toArray(new String[0]));
        load(character.getPersonalData());
        load(character.getAttributes());
        conditionMonitorView
                .setPhysicalDamageMax(calculatePhysicalDamageMax(character))
                .setStunDamageMax(calculateStunDamageMax(character))
                .setPhysicalDamage(character.getPhysicalDamage())
                .setStunDamage(character.getStunDamage())
                .setDicePoolModifier(calculateDicePoolModifier(character));
    }

    private void initMetatypes() {
        personalDataView.removeMetatypes();
        personalDataView.addMetatypes(
                loadData(Metatype.class).toArray(new Metatype[0])
        );
    }

    private void initSexes() {
        personalDataView.removeAllSexes();
        personalDataView.addSexes(
                loadData(Sex.class).toArray(new Sex[0])
        );
    }

    private <T> Collection<T> loadData(Class<T> t) {
        Collection<T> result = new HashSet<>();
        for (String activeModule : character.getActiveModules()) {
            Collection<T> data = sharvPersistenceHandler.readFiles(t).get(activeModule);
            if (data != null) {
                result.addAll(data);
            }
        }
        return result;
    }

    private void load(PersonalData personalData) {
        personalDataView
                .setName(personalData.getName())
                .setStreetname(personalData.getStreetname())
                .setMetatype(personalData.getMetatype())
                .setKarma(0)
                .setKarmaMax(0)
                .setNuyen(0)
                .setSex(personalData.getSex())
                .setAge(personalData.getAge())
                .setHeight(personalData.getHeight())
                .setWeight(personalData.getWeight())
                .setEthnicity(personalData.getEthnicity())
                .setConcept(personalData.getConcept())
                .setStreetCred(personalData.getStreetCred())
                .setNotoriety(personalData.getNotoriety())
                .setPublicAwareness(personalData.getPublicAwareness());
    }

    private void load(Attributes attributes) {
        attributesView
                .setBody(attributes.getPhysical().getBody())
                .setBodyMax(Calculator.calculateBodyMax(character))
                .setAgility(attributes.getPhysical().getAgility())
                .setReaction(attributes.getPhysical().getReaction())
                .setStrength(attributes.getPhysical().getStrength())
                .setWillpower(attributes.getMental().getWillpower())
                .setLogic(attributes.getMental().getLogic())
                .setIntuition(attributes.getMental().getIntuition())
                .setCharisma(attributes.getMental().getCharisma())
                .setComposure(0)
                .setJudgeIntentions(0)
                .setMemory(0)
                .setLiftCarry(0)
                .setWalk(0)
                .setRun(0)
                .setSprint(0)
                .setSpecial(attributes.getSpecialAttributes().getSpecial())
                .setSpecialValue(attributes.getSpecialAttributes().getSpecialValue())
                .setEdge(attributes.getSpecialAttributes().getEdge())
                .setInitiativePhysical(0)
                .setInitiativeAstral(0)
                .setInitiativeMatrixAR(0)
                .setInitiativeMatrixVRcold(0)
                .setInitiativeMatrixVRhot(0)
                .setInitiativeRiggingAR(0)
                .setInitiativeRiggingVRcold(0)
                .setInitiativeRiggingVRhot(0)
                .setInitiativeRiggingDirect(0)
                .setEssence(0)
                .setPhysicalLimit(0)
                .setMentalLimit(0)
                .setSocialLimit(0);
    }

}
