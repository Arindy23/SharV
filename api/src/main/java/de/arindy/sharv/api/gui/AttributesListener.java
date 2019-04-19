package de.arindy.sharv.api.gui;

import de.arindy.sharv.character.Special;

public interface AttributesListener {

    void changeBody(int body);

    void changeAgility(int agility);

    void changeReaction(int reaction);

    void changeStrength(int strength);

    void changeWillpower(int willpower);

    void changeLogic(int logic);

    void changeIntuition(int intuition);

    void changeCharisma(int charisma);

    void changeSpecial(Special special);

    void changeSpecialValue(int specialValue);

    void changeEdge(int edge);

    void changeBurnedEdge(int burnedEdge);

    AttributesListener register(AttributesView attributesView);

}
