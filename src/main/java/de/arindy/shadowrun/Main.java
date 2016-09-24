package de.arindy.shadowrun;

import de.arindy.shadowrun.controller.CharController;
import de.arindy.shadowrun.controller.helper.DataHelper;
import de.arindy.shadowrun.gui.CharSheet;
import net.sf.tinylaf.Theme;
import net.sf.tinylaf.TinyLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static String TITLE = "Shadowrun V Helper";
    private static CharSheet charSheet;

    public static void main(String[] args) {
        DataHelper.init();
        do{
            init();
            CharController charController = new CharController(charSheet);
            try {
                //noinspection SynchronizationOnLocalVariableOrMethodParameter
                synchronized (charController){
                    charController.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (DataHelper.isUiChange());
    }

    private static void init() {
        setLocales();
        DataHelper.setUiChange(false);
        initLookAndFeel();
        int fontSize = DataHelper.getFontsize();
        Font font = loadFont(fontSize);
        setUIFont(new javax.swing.plaf.FontUIResource(((font != null) ? font : new Font("Consolas", Font.PLAIN, fontSize))));

        charSheet = new CharSheet(TITLE);
        setIcons();
    }

    private static void setLocales(){
        Locale.setDefault(DataHelper.getLocale());
        JFileChooser.setDefaultLocale(DataHelper.getLocale());
        JOptionPane.setDefaultLocale(DataHelper.getLocale());
    }

    private static void initLookAndFeel() {
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        //JFrame.setDefaultLookAndFeelDecorated(true);
        //JDialog.setDefaultLookAndFeelDecorated(true);
        Theme.loadTheme(CharSheet.class.getClassLoader().getResource("theme/Nightly.theme"));
        try {
            UIManager.setLookAndFeel(new TinyLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private static Font loadFont(float size) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, CharSheet.class.getClassLoader().getResourceAsStream("font/Inconsolata-Regular.ttf"));
            ge.registerFont(font);
            return font.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            return null;
        }
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        @SuppressWarnings("rawtypes")
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    private static void setIcons() {
        ArrayList<Image> icons = new ArrayList<>();
        try {
            icons.add(ImageIO.read(CharSheet.class.getClassLoader().getResourceAsStream("icon/icon20x20.png")));
            icons.add(ImageIO.read(CharSheet.class.getClassLoader().getResourceAsStream("icon/icon40x40.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        charSheet.getSheet().setIconImages(icons);
    }

}
