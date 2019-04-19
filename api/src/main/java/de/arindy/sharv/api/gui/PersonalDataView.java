package de.arindy.sharv.api.gui;

import de.arindy.sharv.character.Metatype;
import de.arindy.sharv.character.Sex;

public interface PersonalDataView {

    PersonalDataView setName(String name);

    PersonalDataView setStreetname(String streetname);

    PersonalDataView setMetatype(Metatype metatype);

    PersonalDataView setSex(Sex sex);

    PersonalDataView setAge(int age);

    PersonalDataView setHeight(int height);

    PersonalDataView setWeight(int weight);

    PersonalDataView setEthnicity(String ethnicity);

    PersonalDataView setConcept(String concept);

    PersonalDataView setStreetCred(int streetCred);

    PersonalDataView setNotoriety(int notoriety);

    PersonalDataView setPublicAwareness(int publicAwareness);

    PersonalDataView addRacialBonuses(String... bonuses);

    PersonalDataView removeAllRacialBonuses();

    PersonalDataView setKarma(int karma);

    PersonalDataView setKarmaMax(int karmaMax);

    PersonalDataView setNuyen(int nuyen);

    PersonalDataView addSexes(Sex... sexes);

    PersonalDataView removeAllSexes();

    PersonalDataView addMetatypes(Metatype... metatypes);

    PersonalDataView removeMetatypes();

    void updateTitle();
}
