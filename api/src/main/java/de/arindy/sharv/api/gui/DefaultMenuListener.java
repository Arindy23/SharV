package de.arindy.sharv.api.gui;

import lombok.Getter;

import javax.enterprise.inject.Alternative;

@Alternative
@Getter
public class DefaultMenuListener implements MenuListener {

    private MenuView menuView;
    private String color;

    @Override
    public MenuListener register(final MenuView menuView) {
        this.menuView = menuView;
        return this;
    }

    @Override
    public void changeColor(String color) {
        this.color = color;
    }

    @Override
    public void initializeMenu() {
    }

    @Override
    public void activateModule(String module) {
    }

    @Override
    public void deactivateModule(String module) {
    }

}
