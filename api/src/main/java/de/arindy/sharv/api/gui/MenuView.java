package de.arindy.sharv.api.gui;

import java.io.File;

public interface MenuView {

    MenuView registerListener(MenuListener menuListener);

    MenuView setHighlightColor(String highlightColor);

    MenuView setCharacterFile(File characterFile);

}
