package de.arindy.sharv.api.gui;

import de.arindy.sharv.character.Metatype;
import de.arindy.sharv.character.Sex;

public interface PersonalDataListener {

    PersonalDataListener register(PersonalDataView personalDataView);

    void changeName(String name);

    void changeStreetname(String streetname);

    void changeMetatype(Metatype metatype);

    void changeSex(Sex sex);

    void changeAge(int age);

    void changeHeight(int height);

    void changeWeight(int weight);

    void changeEthnicity(String ethnicity);

    void changeConcept(String concept);

    void changeStreetCred(int streetCred);

    void changeNotoriety(int notoriety);

    void changePublicAwareness(int publicAwareness);

}
