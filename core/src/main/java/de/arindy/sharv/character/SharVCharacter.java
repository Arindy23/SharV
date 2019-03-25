package de.arindy.sharv.character;

import lombok.Data;

@Data
public class SharVCharacter {

    private String highlightColor;
    private PersonalData personalData;
    private Attributes attributes;
    private int physicalDamage;
    private int stunDamage;

    public SharVCharacter() {
        personalData = new PersonalData();
        attributes = new Attributes();
    }

}
