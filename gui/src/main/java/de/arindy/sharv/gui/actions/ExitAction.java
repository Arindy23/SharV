package de.arindy.sharv.gui.actions;

import de.arindy.sharv.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * This class will create and dispatch a WINDOW_CLOSING event to the active
 * window.  As a result any WindowListener that handles the windowClosing
 * event will be executed. Since clicking on the "Close" button of the
 * frame/dialog or selecting the "Close" option from the system menu also
 * invoke the WindowListener, this will provide a commen exit point for
 * the application.
 */
public class ExitAction extends AbstractAction {
    public ExitAction() {
        super(Language.getString("menu.datei.close"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //  Find the active window before creating and dispatching the event
        Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();

        if (window != null) {
            WindowEvent windowClosing = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
            window.dispatchEvent(windowClosing);
        }
    }
}
