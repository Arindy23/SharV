package de.arindy.sharv.api.gui;

import java.io.File;

public interface MenuListener {

    MenuListener register(MenuView menuView);

    void changeColor(String color);

    void load(File file);

    void save(File file);

    void initializeMenu();

    void activateModule(String module);

    void deactivateModule(String module);

}
