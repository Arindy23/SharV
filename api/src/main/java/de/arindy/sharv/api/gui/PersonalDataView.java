package de.arindy.sharv.api.gui;

public interface PersonalDataView {

    void addRacialBonuses(String... bonuses);

    void removeAllRacialBonuses();

    PersonalDataView setKarma(int karma);

    PersonalDataView setKarmaMax(int karmaMax);

    PersonalDataView setNuyen(int nuyen);
}
