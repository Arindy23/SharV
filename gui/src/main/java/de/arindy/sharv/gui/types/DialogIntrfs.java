package de.arindy.sharv.gui.types;

import javax.swing.*;

/**
 * Created by Arindy on 24/09/2016.
 */
public interface DialogIntrfs {
    JPanel panel = null;
    String TITLE = null;

    JPanel getPanel();

    String getTitle();

    void update();

}
