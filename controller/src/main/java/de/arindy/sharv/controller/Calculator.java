package de.arindy.sharv.controller;

import de.arindy.sharv.character.Character;

public class Calculator {

    public static int calculateDamageMax(double value) {
        return (int) (8 + Math.ceil(value / 2));
    }

    public static int calculateDicePoolModifier(final Character character) {
        return -(character.getPhysicalDamage() / 3 + character.getStunDamage() / 3);
    }

}
