package de.arindy.sharv.controller;

import javax.enterprise.inject.Alternative;
import java.io.File;

@Alternative
public class DefaultPersistentCharacter implements PersistentCharacter {

    @Override
    public void load(File file) {
    }

    @Override
    public void save(File file) {
    }

}
