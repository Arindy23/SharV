package de.arindy.sharv.api.gui;

import de.arindy.sharv.character.Metatype;
import de.arindy.sharv.character.Sex;
import lombok.Getter;

@Getter
public class DefaultPersonalDataListener implements PersonalDataListener {

    private String name;
    private String streetname;
    private Metatype metatype;
    private Sex sex;
    private int age;
    private int height;
    private int weight;
    private String ethnicity;
    private String concept;
    private int streetCred;
    private int notoriety;
    private int publicAwareness;
    private PersonalDataView personalDataView;

    @Override
    public PersonalDataListener register(PersonalDataView personalDataView) {
        this.personalDataView = personalDataView;
        return this;
    }

    @Override
    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public void changeStreetname(String streetname) {
        this.streetname = streetname;
    }

    @Override
    public void changeMetatype(Metatype metatype) {
        this.metatype = metatype;
    }

    @Override
    public void changeSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public void changeAge(int age) {
        this.age = age;
    }

    @Override
    public void changeHeight(int height) {
        this.height = height;
    }

    @Override
    public void changeWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void changeEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    @Override
    public void changeConcept(String concept) {
        this.concept = concept;
    }

    @Override
    public void changeStreetCred(int streetCred) {
        this.streetCred = streetCred;
    }

    @Override
    public void changeNotoriety(int notoriety) {
        this.notoriety = notoriety;
    }

    @Override
    public void changePublicAwareness(int publicAwareness) {
        this.publicAwareness = publicAwareness;
    }

    @Override
    public void initializePersonalData() {
    }

}
