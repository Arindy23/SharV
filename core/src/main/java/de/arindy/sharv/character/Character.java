package de.arindy.sharv.character;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;

@Data
public class Character {

    private Collection<String> activeModules;
    private String highlightColor;
    private PersonalData personalData;
    private Attributes attributes;
    private int physicalDamage;
    private int stunDamage;

    public Character() {
        activeModules = new HashSet<>();
        activeModules.add("SR5");
        personalData = new PersonalData();
        attributes = new Attributes();
    }

}
