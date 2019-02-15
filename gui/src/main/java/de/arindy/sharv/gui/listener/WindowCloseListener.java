package de.arindy.sharv.gui.listener;

import de.arindy.sharv.Language;
import de.arindy.sharv.gui.actions.SaveCharFile;
import de.arindy.sharv.gui.controller.helper.DataHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class WindowCloseListener implements WindowListener {

    private JFrame frame;
    private List<SaveCharFile> actions = new ArrayList<>();

    public WindowCloseListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    public void addAction(SaveCharFile action) {
        actions.add(action);
    }

    public void removeAction(SaveCharFile action) {
        actions.remove(action);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (DataHelper.hasUnsavedData()) {
            int option = JOptionPane.showOptionDialog(frame, Language.getString("menu.datei.close.savePrompt.title"), Language.getString("menu.datei.close.savePrompt.message"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, e);
            if (option == JOptionPane.YES_OPTION) {
                for (SaveCharFile action : actions) {
                    if (action.saveCanceled(new ActionEvent(this, 0, null))) {
                        return;
                    }
                }
            } else if (option == JOptionPane.NO_OPTION) {
                frame.dispose();
                System.exit(0);
            }
        } else {
            frame.dispose();
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
