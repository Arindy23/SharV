package de.arindy.shadowrun.gui.actions;

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
    private JDialog dia;

    public DialogBauer(JFrame sheet, DialogIntrfs dialog) {
        super(dialog.getTitle());
        this.dialog = dialog;
        this.sheet = sheet;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = dialog.getPanel();
        dia = new JDialog(sheet, dialog.getTitle(), Dialog.ModalityType.APPLICATION_MODAL);

        dia.setResizable(false);
        dia.add(panel);
        dia.pack();
        dia.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        dia.setLocationRelativeTo(sheet);
        dia.setVisible(true);
    }
}
