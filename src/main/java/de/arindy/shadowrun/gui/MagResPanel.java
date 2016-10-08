package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.entities.types.MagRes;
import de.arindy.shadowrun.gui.tabs.MagPanel;
import de.arindy.shadowrun.gui.tabs.ResPanel;

import javax.swing.*;

/**
 * Created by arindy on 26.05.16.
 */
public class MagResPanel {
    private JPanel magRes = new JPanel();
    private MagPanel magPanel = new MagPanel();
    private ResPanel resPanel = new ResPanel();

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
            if (type == MagRes.MAGIE) magRes = magPanel.getPanel();
            if (type == MagRes.RESONANZ) magRes = resPanel.getPanel();
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
}
