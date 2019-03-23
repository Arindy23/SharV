package de.arindy.sharv.api.gui;

public interface PersonalDataView {

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
