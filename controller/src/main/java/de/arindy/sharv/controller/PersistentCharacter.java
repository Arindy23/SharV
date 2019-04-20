package de.arindy.sharv.controller;

import java.io.File;

public interface PersistentCharacter {

    void load(File file);

    void save(File file);

}
