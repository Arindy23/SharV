package de.arindy.sharv.gui.actions;

import de.arindy.sharv.Language;
import de.arindy.sharv.character.Charakter;
import de.arindy.sharv.gui.controller.CharController;
import de.arindy.sharv.gui.controller.helper.ColorChanger;
import de.arindy.sharv.gui.controller.helper.DataHelper;
import de.arindy.sharv.persistence.JsonHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by arindy on 15.05.16.
 */
public class LoadCharFile extends AbstractAction {
    private CharController charController;
    private ColorChanger cc;

    public LoadCharFile(CharController charController) {
        super(Language.getString("menu.charakter.load"));
        this.charController = charController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DataHelper.setLoading(true);
        int option = JOptionPane.YES_OPTION;
        if (DataHelper.hasUnsavedData()) {
            option = JOptionPane.showConfirmDialog(charController.getCharSheet().getSheet(), Language.getString("menu.charakter.load.verwerfen"), Language.getString("menu.charakter.load.verwerfen.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        }
        if (option == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(DataHelper.CHARS_PATH);
            fileChooser.setFileFilter(new FileNameExtensionFilter("srVchar-" + Language.getString("menu.datei") + " (*.srVchar)", "srVchar"));

            int returnValue = fileChooser.showOpenDialog(charController.getCharSheet().getSheet());
            if (JFileChooser.APPROVE_OPTION == returnValue) {
                CharController.character = (Charakter) JsonHandler.readFile(fileChooser.getSelectedFile(), Charakter.class);
                charController.mapChar();
                DataHelper.setUnsavedData(false);
                DataHelper.setLoading(false);
                charController.updateTitle();
                String[] colorStr = CharController.character.getColor().split(",");
                Color color = new Color(Integer.valueOf(colorStr[0]), Integer.valueOf(colorStr[1]), Integer.valueOf(colorStr[2]), Integer.valueOf(colorStr[3]));
                ColorChanger.changeColor(color, ColorChanger.HIGHLIGHTCOLOR);
                DataHelper.setColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            }
        }

    }

}
