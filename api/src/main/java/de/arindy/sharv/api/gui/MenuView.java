package de.arindy.sharv.api.gui;

import java.io.File;

public interface MenuView {

    MenuView setHighlightColor(String highlightColor);

    MenuView setCharacterFile(File characterFile);

    MenuView addAvailableModules(String... modules);

    MenuView setActivatedModules(String... modules);

    void setReloadable(Reloadable reloadable);

}
