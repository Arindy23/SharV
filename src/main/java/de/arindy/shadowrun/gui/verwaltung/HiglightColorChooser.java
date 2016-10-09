package de.arindy.shadowrun.gui.verwaltung;

import de.arindy.shadowrun.controller.helper.ColorChanger;
import de.arindy.shadowrun.controller.helper.DataHelper;
import de.arindy.shadowrun.gui.helper.Language;
import de.arindy.shadowrun.gui.types.DialogIntrfs;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Arindy on 08/10/2016.
 */
public class HiglightColorChooser implements DialogIntrfs, ChangeListener {
    private String TITLE = Language.getString("menu.options.highlightColorChooser.title");

    private JPanel panel;
    private JColorChooser hcc;
    private JButton apply;
    private JButton reset;
    private JButton cancel;
    private Color newColor;

    public HiglightColorChooser() {
        panel.setName(TITLE);
        apply.addActionListener(e -> apply());
        reset.addActionListener(e -> reset());
        cancel.addActionListener(e -> panel.getParent().getParent().getParent().getParent().setVisible(false));
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void update() {
        newColor = new Color(DataHelper.getHighlightColorR(), DataHelper.getHighlightColorG(), DataHelper.getHighlightColorB(), DataHelper.getHighlightColorAlpha());
        hcc.setColor(newColor);
        hcc.updateUI();
        removeChooserPanels();
        apply.updateUI();
        reset.updateUI();
        cancel.updateUI();
    }

    private void apply() {
        if (newColor != null) {
            DataHelper.setColor(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), newColor.getAlpha());
            panel.getParent().getParent().getParent().getParent().setVisible(false);
            DataHelper.setUnsavedData(true);
            hcc.updateUI();
            removeChooserPanels();
        }
    }

    private void reset() {
        hcc.setColor(new Color(DataHelper.getHighlightColorR(), DataHelper.getHighlightColorG(), DataHelper.getHighlightColorB(), DataHelper.getHighlightColorAlpha()));
        newColor = hcc.getColor();
        ColorChanger.changeColor(newColor, ColorChanger.HIGHLIGHTCOLOR);
    }

    private void removeChooserPanels() {
        for (AbstractColorChooserPanel ccp : hcc.getChooserPanels()) {
            if (!ccp.getDisplayName().equalsIgnoreCase("HSV")) {
                hcc.removeChooserPanel(ccp);
            }
        }

    }

    private void createUIComponents() {
        hcc = new JColorChooser();
        removeChooserPanels();
        hcc.setPreviewPanel(new JPanel());
        hcc.getSelectionModel().addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        newColor = hcc.getColor();
        ColorChanger.changeColor(newColor, ColorChanger.HIGHLIGHTCOLOR);
        apply.updateUI();
        reset.updateUI();
        cancel.updateUI();
    }
}
