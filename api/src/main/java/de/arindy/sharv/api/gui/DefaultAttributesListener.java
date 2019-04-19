package de.arindy.sharv.api.gui;

import de.arindy.sharv.character.Special;
import lombok.Getter;

@Getter
public class DefaultAttributesListener implements AttributesListener {

    private int body;
    private int agility;
    private int reaction;
    private int strength;
    private int willpower;
    private int logic;
    private int intuition;
    private int charisma;
    private Special special;
    private int specialValue;
    private int edge;
    private int burnedEdge;
    private AttributesView attributesView;

    @Override
    public AttributesListener register(AttributesView attributesView) {
        this.attributesView = attributesView;
        return this;
    }

    @Override
    public void changeBody(int body) {
        this.body = body;
    }

    @Override
    public void changeAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public void changeReaction(int reaction) {
        this.reaction = reaction;
    }

    @Override
    public void changeStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void changeWillpower(int willpower) {
        this.willpower = willpower;
    }

    @Override
    public void changeLogic(int logic) {
        this.logic = logic;
    }

    @Override
    public void changeIntuition(int intuition) {
        this.intuition = intuition;
    }

    @Override
    public void changeCharisma(int charisma) {
        this.charisma = charisma;
    }

    @Override
    public void changeSpecial(Special special) {
        this.special = special;
    }

    @Override
    public void changeSpecialValue(int specialValue) {
        this.specialValue = specialValue;
    }

    @Override
    public void changeEdge(int edge) {
        this.edge = edge;
    }

    @Override
    public void changeBurnedEdge(int burnedEdge) {
        this.burnedEdge = burnedEdge;
    }

}
