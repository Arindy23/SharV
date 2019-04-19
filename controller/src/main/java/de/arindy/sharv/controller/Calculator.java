package de.arindy.sharv.controller;

import de.arindy.sharv.character.Character;

public class Calculator {

    static int calculatePhysicalDamageMax(final Character character) {
        return (int) (8 + Math.ceil(character.getAttributes().getPhysical().getBody() / 2));
    }

    static int calculateStunDamageMax(final Character character) {
        return (int) (8 + Math.ceil(character.getAttributes().getMental().getWillpower() / 2));
    }

    static int calculateDicePoolModifier(final Character character) {
        return -(character.getPhysicalDamage() / 3 + character.getStunDamage() / 3);
    }

}
