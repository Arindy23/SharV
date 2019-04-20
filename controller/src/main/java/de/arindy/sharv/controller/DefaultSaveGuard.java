package de.arindy.sharv.controller;

import javax.enterprise.inject.Alternative;

@Alternative
public class DefaultSaveGuard implements SaveGuard {

    @Override
    public boolean shouldSave() {
        return false;
    }

}
