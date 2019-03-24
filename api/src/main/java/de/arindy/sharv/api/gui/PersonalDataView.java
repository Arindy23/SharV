package de.arindy.sharv.api.gui;

public interface PersonalDataView {

    PersonalDataView setName(String name);

    PersonalDataView setStreetname(String streetname);

    PersonalDataView setMetatype(String metatype);

    PersonalDataView setSex(String sex);

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

    PersonalDataView registerListener(PersonalDataListener personalDataListener);

    PersonalDataView addSexes(String... sexes);

    PersonalDataView removeAllSexes();

    PersonalDataView addMetatypes(String... metatypes);

    PersonalDataView removeMetatypes();
}
