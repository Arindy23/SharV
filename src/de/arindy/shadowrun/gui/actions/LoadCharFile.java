package de.arindy.shadowrun.gui.actions;

import de.arindy.shadowrun.controller.CharController;
import de.arindy.shadowrun.controller.helper.DataHelper;
import de.arindy.shadowrun.entities.Charakter;
import de.arindy.shadowrun.persistence.helper.JsonHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

/**
 * Created by arindy on 15.05.16.
 */
public class LoadCharFile extends AbstractAction {
    private CharController charController;

    public LoadCharFile(CharController charController) {
        super("Laden");
        this.charController = charController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DataHelper.loading = true;
        int option = JOptionPane.YES_OPTION;
        if (DataHelper.unsavedData) {
            option = JOptionPane.showConfirmDialog(charController.getCharSheet().getSheet(), "Ungespeicherte Daten verwerfen?", "Charakter Laden", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        }
        if (option == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Char-Datei (*.srVchar)", "srVchar"));

            int returnValue = fileChooser.showOpenDialog(charController.getCharSheet().getSheet());
            if (JFileChooser.APPROVE_OPTION == returnValue) {
                //CharController.character = new Charakter();
                CharController.character = (Charakter) JsonHandler.readFile(fileChooser.getSelectedFile(), Charakter.class);
                charController.mapChar();
                DataHelper.unsavedData = false;
                charController.updateTitle();
            }
        }
        DataHelper.loading = false;
    }

}
