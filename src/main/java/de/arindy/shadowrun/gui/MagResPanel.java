package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.entities.types.MagRes;
import de.arindy.shadowrun.gui.helper.Language;

import javax.swing.*;

/**
 * Created by arindy on 26.05.16.
 */
public class MagResPanel {
    private JPanel magRes = new JPanel();

    public MagResPanel() {
        magRes.setName("-");
    }

    public JPanel getPanel() {
        return magRes;
    }

    public void setMagRes(MagRes type) {
        if(type!=null){
            JTabbedPane tabbed = (JTabbedPane) magRes.getParent();
            tabbed.remove(magRes);
            if (type == MagRes.MAGIE) magRes = getMagiePanel();
            if (type == MagRes.RESONANZ) magRes = getResonanzPanel();
            if (type == MagRes.NONE) magRes = new JPanel();
            tabbed.add(magRes, 0);
            tabbed.setTitleAt(0, type.toString());
            if (type == MagRes.NONE) {
                tabbed.setEnabledAt(0, false);
            } else {
                tabbed.setEnabledAt(0, true);
                tabbed.setSelectedIndex(0);
            }
        }
    }

    private JPanel getMagiePanel() {
        JPanel mag = new JPanel();
        mag.add(new JLabel(Language.getString("magres.m")));
        return mag;
    }

    private JPanel getResonanzPanel() {
        return new JPanel();
    }

}
