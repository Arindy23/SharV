package de.arindy.shadowrun.controller.helper;

import net.sf.tinylaf.Theme;
import net.sf.tinylaf.TinyLookAndFeel;
import net.sf.tinylaf.util.SBReference;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arindy on 08/10/2016.
 */
public class ColorChanger {

    public static final int HIGHLIGHTCOLOR = 0;

    public static Color nonHighlightColor = new Color(154, 154, 154);

    private static List<Component> compList = new ArrayList<>();
    private static List<Component> highlightComps = new ArrayList<>();

    public static void addComponent(Component comp) {
        for (Component component : compList) {
            if (comp != null && component != null && comp.getName().equalsIgnoreCase(component.getName())) {
                compList.remove(component);
                break;
            }
        }
        compList.add(comp);
    }

    public static void addHighlightComp(Component comp) {
        highlightComps.add(comp);
    }

    public static void addNonHighlightComp(Component comp) {
        if (UIManager.getLookAndFeel().getName().equalsIgnoreCase("TinyLookAndFeel")) {
            comp.setForeground(nonHighlightColor);
        }
    }

    public static void removeComponent(Component comp) {
        System.out.println("removed: " + compList.remove(comp));
    }

    public static void changeColor(Color color, int sort) {
        if (sort == HIGHLIGHTCOLOR) {
            if (UIManager.getLookAndFeel().getName().equalsIgnoreCase("TinyLookAndFeel")) {
                for (Component comp : highlightComps) {
                    comp.setForeground(color);
                }
                SBReference sbr = new SBReference(color, 255, 255, 255);
                Theme.titledBorderColor = sbr;
                Theme.titledBorderFontColor = sbr;
                Theme.textTextColor = sbr;
                Theme.progressColor = sbr;
                Theme.listTextColor = sbr;
                Theme.listSelectedBgColor = sbr;
                Theme.listFocusBorderColor = sbr;
                Theme.tipTextColor = sbr;
                Theme.textCaretColor = sbr;
                Theme.textSelectedBgColor = sbr;
                Theme.buttonFontColor = sbr;
                Theme.buttonRolloverColor = sbr;
                Theme.comboTextColor = sbr;
                Theme.comboSelectedBgColor = sbr;
                Theme.comboArrowColor = sbr;
                Theme.spinnerArrowColor = sbr;
                Theme.tabFontColor = sbr;
                Theme.tabRolloverColor = sbr;
                Color tabSelCol = color;
                for (int i = 0; i < 7; i++) {
                    tabSelCol = tabSelCol.darker();
                }
                Theme.tabSelectedColor = new SBReference(tabSelCol, 255, 255, 255);
                try {
                    UIManager.setLookAndFeel(new TinyLookAndFeel());
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            }
        }
        for (Component comp : compList) {
            if (comp != null) {
                SwingUtilities.updateComponentTreeUI(comp);
            }
        }
    }
}
