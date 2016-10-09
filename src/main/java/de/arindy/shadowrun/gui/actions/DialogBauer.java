package de.arindy.shadowrun.gui.actions;

import de.arindy.shadowrun.controller.helper.ColorChanger;
import de.arindy.shadowrun.gui.helper.Language;
import de.arindy.shadowrun.gui.types.DialogIntrfs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by arindy on 28.07.16.
 */
public class DialogBauer extends AbstractAction {

    private JFrame sheet;
    private DialogIntrfs dialog;
    private JPanel panel;

    public DialogBauer(JFrame sheet, DialogIntrfs dialog) {
        super(dialog.getTitle());
        this.dialog = dialog;
        this.sheet = sheet;
        this.panel = dialog.getPanel();
        if (!dialog.getTitle().equalsIgnoreCase(Language.getString("menu.options.highlightColorChooser.title")))
            ColorChanger.addComponent(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dia = new JDialog(sheet, dialog.getTitle(), Dialog.ModalityType.APPLICATION_MODAL);
        dialog.update();
        dia.setResizable(false);
        dia.add(panel);
        dia.pack();
        dia.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        dia.setLocationRelativeTo(sheet);
        dia.setVisible(true);
    }
}
