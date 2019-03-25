package de.arindy.sharv.controller;

import de.arindy.sharv.character.SharVCharacter;

public class Calculator {

    public static int calculateDamageMax(double value) {
        return (int) (8 + Math.ceil(value / 2));
    }

    public static int calculateDicePoolModifier(final SharVCharacter character) {
        return - (character.getPhysicalDamage() / 3 + character.getStunDamage() / 3);
    }

}
