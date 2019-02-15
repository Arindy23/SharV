package de.arindy.sharv.gui.controller.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by arindy on 27.05.16.
 */
public interface CustomDocumentListener extends DocumentListener {

    void onEvent(DocumentEvent e);

    @Override
    default void insertUpdate(DocumentEvent e) {
        onEvent(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        onEvent(e);
    }

    @Override
    default void changedUpdate(DocumentEvent e) {

    }
}
