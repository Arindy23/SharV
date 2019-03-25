package de.arindy.sharv.api.gui;

import java.io.File;

public interface MenuListener {

    void changeColor(String color);

    void loadCharacter(File file);

    void saveCharacter(File file);
}
