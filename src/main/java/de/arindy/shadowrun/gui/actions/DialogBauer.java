package de.arindy.shadowrun.gui.actions;

import de.arindy.shadowrun.controller.CharController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by arindy on 28.07.16.
 */
public class DialogBauer extends AbstractAction {

    private JFrame sheet;
    private JPanel panel;
    private JDialog dialog;

    public DialogBauer(JFrame sheet, JPanel panel) {
        super(panel.getName());
        this.sheet = sheet;
        this.panel = panel;
        init();
    }

    private void init() {
        dialog = new JDialog(sheet, this.panel.getName(), Dialog.ModalityType.APPLICATION_MODAL);

        dialog.setSize(this.panel.getSize());
        dialog.setResizable(false);
        dialog.add(this.panel);
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
