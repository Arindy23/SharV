package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.gui.types.GBC;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arindy on 27.05.16.
 */
public class Zustandsmonitor {

    //<editor-fold desc="Variablen">
    private JPanel panel;
    private JPanel pKoerperlich = new JPanel();
    private JPanel pGeistig = new JPanel();
    private JPanel pueberzaehlig = new JPanel();
    private JPanel pcueberzaehlig = new JPanel();
    private JLabel lKoerperlich = new JLabel("Körperlich:");
    private JLabel lGeistig = new JLabel("Geistig:");
    private JLabel lVerletzungsmod = new JLabel("<html>Verletzungs-<br>modifikator:</html>");
    private JLabel lUeberzaehlig = new JLabel("Überzähliger Schaden:");

    private JLabel verletzungsmodifikator = new JLabel("0");
    //</editor-fold>

    public Zustandsmonitor() {
        setupPanel();
    }

    private void setupPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        verletzungsmodifikator.setFont((lVerletzungsmod.getFont().deriveFont(Font.BOLD, 30)));
        pueberzaehlig.add(lUeberzaehlig, GBC.clUeberzaehlig);
        pueberzaehlig.add(pcueberzaehlig, GBC.cUeberzaehlig);
        panel.add(lKoerperlich, GBC.clKoerperlich);
        panel.add(pKoerperlich, GBC.cpKoerperlich);
        panel.add(lGeistig, GBC.clGeistig);
        panel.add(pGeistig, GBC.cpGeistig);
        panel.add(lVerletzungsmod, GBC.clVerletzungsmod);
        panel.add(verletzungsmodifikator, GBC.cVerletzungsmod);
        panel.add(pueberzaehlig, GBC.cpUeberzaehlig);
    }

    public JPanel getpKoerperlich() {
        return pKoerperlich;
    }

    public JPanel getpGeistig() {
        return pGeistig;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getPcueberzaehlig() {
        return pcueberzaehlig;
    }

    public JLabel getVerletzungsmodifikator() {
        return verletzungsmodifikator;
    }
}
