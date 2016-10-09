package de.arindy.shadowrun.gui.verwaltung;

import de.arindy.shadowrun.controller.CharController;
import de.arindy.shadowrun.entities.types.VorteilNachteil;
import de.arindy.shadowrun.entities.types.VorteilNachteilEnum;
import de.arindy.shadowrun.gui.helper.JCustomTextField;
import de.arindy.shadowrun.gui.helper.Language;
import de.arindy.shadowrun.gui.helper.SortedListModel;
import de.arindy.shadowrun.gui.types.DialogIntrfs;
import de.arindy.shadowrun.gui.types.GBC;
import de.arindy.shadowrun.persistence.helper.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by arindy on 28.07.16.
 */
public class VorteilNachteilVerwaltung implements DialogIntrfs {
    private final String srVvnFolder = "/srV/vn/";
    private String TITLE = Language.getString("verwaltung.vortnacht.title");
    private JPanel panel;
    private JPanel panelTop = new JPanel();
    private JPanel panelLeft = new JPanel();
    private JPanel panelRight = new JPanel();
    private JPanel panelName = new JPanel();
    private JPanel panelKarmaBonusKosten = new JPanel();
    private JPanel panelSpeichernLoeschen = new JPanel();

    private JComboBox<VorteilNachteilEnum> lVorteilNachteil = new JComboBox<>(new DefaultComboBoxModel<>(VorteilNachteilEnum.values()));

    private JLabel lName = new JLabel("Name:");
    private JLabel lBeschreibung = new JLabel("Beschreibung:");
    private JLabel lDetails = new JLabel("Details:");
    private JLabel lKarmaBonusKosten = new JLabel("");
    private SortedListModel<VorteilNachteil> modelVorteilNachteil = new SortedListModel<>();
    private JList listVorteilNachteil = new JList(modelVorteilNachteil);
    private JScrollPane spListVorteilNachteil = new JScrollPane(listVorteilNachteil);

    private JCustomTextField tName = new JCustomTextField();
    private JCustomTextField tKarmaBonusKosten = new JCustomTextField();
    private JTextArea tBeschreibung = new JTextArea();
    private JScrollPane spBeschreibung = new JScrollPane(tBeschreibung);
    private JTextArea tDetails = new JTextArea();
    private JScrollPane spDetails = new JScrollPane(tDetails);

    private JButton bAktualisieren = new JButton("Liste aktualisieren");
    private JButton bLoeschen = new JButton("Löschen");
    private JButton bSpeichern = new JButton("Speichern");
    private JList list1;
    private JComboBox comboBox1;
    private JCustomTextField JCustomTextField1;
    private JCustomTextField JCustomTextField2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton bCancel;


    public JPanel getPanel() {
//        if(panel==null){
//            addListeners();
//        }
        initPanel();
        return panel;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void update() {

    }

    private void initPanel() {
        panel = new JPanel();
        panel.setName(TITLE);
        panel.setSize(500, 350);
        setLayouts();
        addComponents();
        updateVorteilNachteilAuswahl();
        setLimits();
    }

    private void addListeners() {
        bAktualisieren.addActionListener(e -> loadList());
        bSpeichern.addActionListener(e -> actionSpeichern());
        bLoeschen.addActionListener(e -> actionLoeschen());
        bLoeschen.setEnabled(false);
        lVorteilNachteil.addActionListener(e -> updateVorteilNachteilAuswahl());
        listVorteilNachteil.addListSelectionListener(e -> actionListSelect());
    }

    private void actionListSelect() {
        int selIndex = listVorteilNachteil.getSelectedIndex();
        if (selIndex > -1) {
            bLoeschen.setEnabled(true);
            VorteilNachteil selItem = (VorteilNachteil) modelVorteilNachteil.getElementAt(selIndex);
            lVorteilNachteil.setSelectedItem((selItem.isVorteil()) ? VorteilNachteilEnum.VORTEIL : VorteilNachteilEnum.NACHTEIL);
            tName.setText(selItem.getName());
            tBeschreibung.setText(selItem.getBeschreibung());
            tDetails.setText(selItem.getDetails());
            updateVorteilNachteilAuswahl();
            tKarmaBonusKosten.setText(selItem.getKarmaBonusKosten() + "");
        } else {
            bLoeschen.setEnabled(false);
        }
    }

    private void actionSpeichern() {
        VorteilNachteil item = new VorteilNachteil();
        item.setVorteil((lVorteilNachteil.getSelectedItem() == VorteilNachteilEnum.VORTEIL));
        item.setName(tName.getText());
        item.setBeschreibung(tBeschreibung.getText());
        item.setDetails(tDetails.getText());
        if (tKarmaBonusKosten.getText().isEmpty()) tKarmaBonusKosten.setText("0");
        item.setKarmaBonusKosten(Integer.valueOf(tKarmaBonusKosten.getText()));
        boolean done = false;
        while (!done) {
            String s = (String) JOptionPane.showInputDialog(
                    panel,
                    "Geben Sie eine eindeutige ID ein.",
                    "ID",
                    JOptionPane.PLAIN_MESSAGE, null, null, null
            );
            if (s != null) {
                File file = new File(CharController.getDirectory().getAbsolutePath() + srVvnFolder + s + ".srVvn");
                int option = JOptionPane.YES_OPTION;
                if (file.exists()) {
                    option = JOptionPane.showConfirmDialog(panel, "ID schon vergeben. Wollen Sie sie ersetzen?", "ID vergeben", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                }
                if (option == JOptionPane.YES_OPTION) {
                    JsonHandler.writeFile(file, item);
                    done = true;
                }
            } else {
                done = true;
            }
            loadList();
            if (listVorteilNachteil.getSelectedIndex() > -1) {
                bLoeschen.setEnabled(true);
            }
        }
    }

    private void actionLoeschen() {
        int option = JOptionPane.showConfirmDialog(panel, "Wollen sie wirklich löschen?", "Löschen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        int index = listVorteilNachteil.getSelectedIndex();
        if (option == JOptionPane.YES_OPTION && index > -1) {
            VorteilNachteil item = (VorteilNachteil) modelVorteilNachteil.getElementAt(index);
            modelVorteilNachteil.removeElement(item);
            new File(CharController.getDirectory() + srVvnFolder + item.getId() + ".srVvn").delete();
            loadList();
            bLoeschen.setEnabled(false);
        }
    }

    private void updateVorteilNachteilAuswahl() {
        if (lVorteilNachteil.getSelectedItem() == VorteilNachteilEnum.VORTEIL) {
            lKarmaBonusKosten.setText("Karmakosten:");
        }
        if (lVorteilNachteil.getSelectedItem() == VorteilNachteilEnum.NACHTEIL) {
            lKarmaBonusKosten.setText("Karmabonus:");
        }
    }

    private void setLayouts() {
        panel.setLayout(new GridBagLayout());
        panelTop.setLayout(new GridLayout());
        panelLeft.setLayout(new GridBagLayout());
        panelRight.setLayout(new GridBagLayout());
        panelSpeichernLoeschen.setLayout(new GridBagLayout());

        panelName.setLayout(new GridBagLayout());
        panelKarmaBonusKosten.setLayout(new GridBagLayout());
    }

    private void setLimits() {
        tName.setMaximumLength(30);
        tKarmaBonusKosten.setMaximumLength(3);
        tKarmaBonusKosten.setRegexFilter("\\d*");
        tBeschreibung.setLineWrap(true);
        tBeschreibung.setWrapStyleWord(true);
        tBeschreibung.setBorder(BorderFactory.createLineBorder(new Color(116, 116, 116)));
        tBeschreibung.setRows(13);
        tDetails.setLineWrap(true);
        tDetails.setWrapStyleWord(true);
        tDetails.setBorder(BorderFactory.createLineBorder(new Color(116, 116, 116)));
        tDetails.setRows(5);

        spListVorteilNachteil.setBorder(BorderFactory.createEmptyBorder());
    }

    private void loadList() {
        modelVorteilNachteil.clear();
        File vorteilNachteilOrdner = new File(CharController.getDirectory().getAbsolutePath() + "/srV/vn");
        vorteilNachteilOrdner.mkdirs();
        for (File file : vorteilNachteilOrdner.listFiles((dir, name) -> name.toLowerCase().endsWith(".srvvn"))) {
            VorteilNachteil item = (VorteilNachteil) JsonHandler.readFile(file, VorteilNachteil.class);
            if (item != null) {
                item.setId(file.getName().split("\\.")[0]);
            }
            modelVorteilNachteil.add(item);
        }
    }

    private void addComponents() {
        loadList();
        panelName.add(lName, GBC.cvnwlName);
        panelName.add(tName, GBC.cvnwtName);

        panelKarmaBonusKosten.add(lKarmaBonusKosten, GBC.cvnwlKarmaBonusKosten);
        panelKarmaBonusKosten.add(tKarmaBonusKosten, GBC.cvnwtKarmaBonusKosten);

        panelSpeichernLoeschen.add(bSpeichern, GBC.cvnwbSpeichern);
        panelSpeichernLoeschen.add(bLoeschen, GBC.cvnwbLoeschen);

        panelLeft.add(spListVorteilNachteil, GBC.cvnwListe);
        panelLeft.add(bAktualisieren, GBC.cvnwbAktualisieren);

        panelRight.add(lVorteilNachteil, GBC.cvnwlVorteilNachteil);
        panelRight.add(panelName, GBC.cvnwpName);
        panelRight.add(lBeschreibung, GBC.cvnwlBeschreibung);
        panelRight.add(spBeschreibung, GBC.cvnwtBeschreibung);
        panelRight.add(lDetails, GBC.cvnwlDetails);
        panelRight.add(spDetails, GBC.cvnwtDetails);
        panelRight.add(panelKarmaBonusKosten, GBC.cvnwpKarmaBonusKosten);
        panelTop.add(panelLeft);
        panelTop.add(panelRight);
        panel.add(panelTop, GBC.cvnwpTop);
        panel.add(panelSpeichernLoeschen, GBC.cvnwpLoeschenSpeichern);

    }
}
