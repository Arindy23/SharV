package de.arindy.sharv.character;

import lombok.Data;

@Data
public class PersonalData {

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

}
