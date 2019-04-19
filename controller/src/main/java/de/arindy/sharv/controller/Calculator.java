package de.arindy.sharv.controller;

import de.arindy.sharv.character.Character;

public class Calculator {

    static int calculatePhysicalDamageMax(final Character character) {
        return (int) (8 + Math.ceil(character.getAttributes().getPhysical().getBody() / 2D));
    }

    static int calculateStunDamageMax(final Character character) {
        return (int) (8 + Math.ceil(character.getAttributes().getMental().getWillpower() / 2D));
    }

    static int calculateDicePoolModifier(final Character character) {
        return -(character.getPhysicalDamage() / 3 + character.getStunDamage() / 3);
    }

    static int calculateBodyMax(final Character character) {
        return character.getPersonalData().getMetatype().getBodyMax();
    }

}
