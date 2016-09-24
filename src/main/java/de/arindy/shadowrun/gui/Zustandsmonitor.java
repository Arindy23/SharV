package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.controller.helper.DataHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindy on 27.05.16.
 */
public class Zustandsmonitor {

    //<editor-fold desc="Variablen">
    private JPanel pKoerperlich;
    private JPanel pGeistig;
    private JPanel pueberzaehlig;

    private JLabel verletzungsmodifikator;
    private JPanel panel;

    private List<JCheckBox> koerperlich;
    private List<JCheckBox> geistig;
    private List<JCheckBox> ueberzaehlig;
    //</editor-fold>

    public JLabel getVerletzungsmodifikator() {
        return verletzungsmodifikator;
    }

    public List<JCheckBox> getKoerperlich() {
        return koerperlich;
    }

    public List<JCheckBox> getGeistig() {
        return geistig;
    }

    public List<JCheckBox> getUeberzaehlig() {
        return ueberzaehlig;
    }

    public JPanel getPanel() {
        return panel;
    }

    private void fillZustandPanel(JPanel panel, List<JCheckBox> arrayList, int rows) {
        panel.setLayout(new GridLayout(4, 0));
        for (int i = 0; i < rows * 3; i++) {
            arrayList.add(new JCheckBox());
        }
        for (int i = 0; i < rows * 4; i++) {
            if (i / rows == 0) panel.add(new JLabel("-" + (i + 1)));
            if (i / rows == 1) panel.add(arrayList.get((i - rows) * 3));
            if (i / rows == 2) panel.add(arrayList.get((i - rows * 2) * 3 + 1));
            if (i / rows == 3) panel.add(arrayList.get((i - rows * 3) * 3 + 2));
        }
    }

    private void setupUeberzaehligPanel(int konst) {
        pueberzaehlig.setLayout(new GridLayout(2, 0));
        for (int i = 0; i < konst; i++) {
            ueberzaehlig.add(new JCheckBox());
            ueberzaehlig.get(i).setHorizontalAlignment(JCheckBox.CENTER);
            pueberzaehlig.add(ueberzaehlig.get(i));
        }
    }

    private void createUIComponents() {
        koerperlich = new ArrayList<>();
        geistig = new ArrayList<>();
        ueberzaehlig = new ArrayList<>();
        verletzungsmodifikator = new JLabel("" + 0);
        verletzungsmodifikator.setFont(verletzungsmodifikator.getFont().deriveFont(Font.BOLD, (int) (DataHelper.getFontsize() * 2.5)));
        pKoerperlich = new JPanel();
        pGeistig = new JPanel();
        pueberzaehlig = new JPanel();

        fillZustandPanel(pKoerperlich, koerperlich, 6);
        fillZustandPanel(pGeistig, geistig, 4);
        setupUeberzaehligPanel(20);
    }
}