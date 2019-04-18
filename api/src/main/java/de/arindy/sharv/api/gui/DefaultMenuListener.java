package de.arindy.sharv.api.gui;

import lombok.Getter;

import java.io.File;

@Getter
public class DefaultMenuListener implements MenuListener {

    private MenuView menuView;
    private String color;
    private File file;

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
    public void load(File file) {
        this.file = file;
    }

    @Override
    public void save(File file) {
        this.file = file;
    }

}
