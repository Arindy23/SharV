package de.arindy.sharv.api.gui;

public interface MenuListener {

    MenuListener register(MenuView menuView);

    void changeColor(String color);

    void initializeMenu();

    void activateModule(String module);

    void deactivateModule(String module);

}
