package de.arindy.sharv.api.gui;

import java.io.File;

public interface MenuView {

    MenuView setHighlightColor(String highlightColor);

    MenuView setCharacterFile(File characterFile);

    void setReloadable(Reloadable reloadable);

}
