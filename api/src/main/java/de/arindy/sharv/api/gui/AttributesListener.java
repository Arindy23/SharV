package de.arindy.sharv.api.gui;

public interface AttributesListener {

    void changeBody(int body);

    void changeAgility(int agility);

    void changeReaction(int reaction);

    void changeStrength(int strength);

    void changeWillpower(int willpower);

    void changeLogic(int logic);

    void changeIntuition(int intuition);

    void changeCharisma(int charisma);

    void changeSpecial(String special);

    void changeSpecialValue(int specialValue);

    void changeEdge(int edge);

    void changeBurnedEdge(int burnedEdge);

}
