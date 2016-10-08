package de.arindy.shadowrun.gui.tabs;

import de.arindy.shadowrun.entities.types.VorteilNachteil;
import de.arindy.shadowrun.gui.helper.JCustomTextField;
import de.arindy.shadowrun.gui.helper.Language;
import de.arindy.shadowrun.gui.helper.SortedListModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arindy on 27.05.16.
 */
public class VorteilNachteilPanel {

    private JPanel panelMain;
    private JPanel panelList;
    private JPanel panelDetails;


    private SortedListModel<VorteilNachteil> listmodel = new SortedListModel<>();
    private JList list = new JList(listmodel);
    private JScrollPane scrollPane = new JScrollPane(list);


    public VorteilNachteilPanel() {
    }

    public SortedListModel<VorteilNachteil> getList() {
        return listmodel;
    }

    public JPanel getPanel() {
        return panelMain;
    }
}
