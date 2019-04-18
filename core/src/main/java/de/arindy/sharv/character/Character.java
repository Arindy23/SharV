package de.arindy.sharv.character;

import lombok.Data;

@Data
public class Character {

    private String highlightColor;
    private PersonalData personalData;
    private Attributes attributes;
    private int physicalDamage;
    private int stunDamage;

    public Character() {
        personalData = new PersonalData();
        attributes = new Attributes();
    }

}
