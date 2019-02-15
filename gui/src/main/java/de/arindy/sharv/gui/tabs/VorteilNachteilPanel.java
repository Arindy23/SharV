package de.arindy.sharv.gui.tabs;

import de.arindy.sharv.character.json.VorteilNachteil;
import de.arindy.sharv.gui.helper.SortedListModel;

import javax.swing.*;

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
