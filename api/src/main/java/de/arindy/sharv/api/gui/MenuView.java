package de.arindy.sharv.api.gui;

public interface MenuView {

    MenuView setHighlightColor(String highlightColor);

    MenuView addAvailableModules(String... modules);

    MenuView setActivatedModules(String... modules);

    void setReloadable(Reloadable reloadable);

}
