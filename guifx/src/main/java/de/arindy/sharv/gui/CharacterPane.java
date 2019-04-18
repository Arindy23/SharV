package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.AttributesView;
import de.arindy.sharv.api.gui.CharacterView;
import de.arindy.sharv.api.gui.PersonalDataView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CharacterPane implements CharacterView {

    @Inject
    private PersonalDataView personalData;
    @Inject
    private AttributesView attributes;

    @Override
    public PersonalDataView getPersonalData() {
        return personalData;
    }

    @Override
    public AttributesView getAttributes() {
        return attributes;
    }

}
