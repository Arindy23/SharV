package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.entities.types.VorteilNachteil;
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
        panelMain = new JPanel();
        panelMain.setName("Vorteile/Nachteile");
        panelList = new JPanel();
        panelDetails = new JPanel();
        setLayouts();
        initList();
        panelMain.add(panelList);
    }

    private void setLayouts() {
        panelMain.setLayout(new GridLayout());
        panelList.setLayout(new GridLayout());
    }

    private void initList() {
        panelList.add(scrollPane);
    }

    public SortedListModel<VorteilNachteil> getList() {
        return listmodel;
    }

    public JPanel getPanel() {
        return panelMain;
    }
}
