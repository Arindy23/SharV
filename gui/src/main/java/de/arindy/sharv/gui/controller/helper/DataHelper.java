package de.arindy.sharv.gui.controller.helper;

import org.ini4j.Ini;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class DataHelper {

    public static final File CHARS_PATH = new File(System.getProperty("user.home") + "/.sharV/chars");
    private static final File CONFIG_PATH = new File(System.getProperty("user.home") + "/.sharV");
    private static boolean unsavedData = false;
    private static boolean loading = false;
    private static boolean uiChange = true;
    private static Point location;
    private static Locale locale;
    private static String lookAndFeel;
    private static int fontsize;
    private static File iniFile = new File(CONFIG_PATH.getAbsolutePath() + "/srV.ini");
    private static Ini ini;
    private static int highlightColorR;
    private static int highlightColorG;
    private static int highlightColorB;
    private static int highlightColorAlpha;

    public static void init() {
        System.out.println(iniFile);
        CONFIG_PATH.mkdir();
        CHARS_PATH.mkdir();
        setupIni();
        String tempCharPath = ini.fetch("lastFile", "filePath");
        lookAndFeel = ini.fetch("options", "lookAndFeel");
        loadColor();
        storeIniFile();

        locale = new Locale(ini.fetch("options", "locale"));
        try {
            fontsize = Integer.valueOf(ini.fetch("options", "fontsize"));
        } catch (NumberFormatException nfe) {
            fontsize = 12;
        }

    }

    private static void loadColor() {
        String color = ini.fetch("options", "color");
        String[] colorStr;
        if (color == null) {
            colorStr = new String[]{"255", "255", "255", "255"};
        } else {
            colorStr = color.split(",");
        }
        if (colorStr.length >= 3) {
            highlightColorR = Integer.valueOf(colorStr[0]);
            highlightColorG = Integer.valueOf(colorStr[1]);
            highlightColorB = Integer.valueOf(colorStr[2]);
            if (colorStr.length == 4) {
                highlightColorAlpha = Integer.valueOf(colorStr[3]);
            }
        }
    }

    private static void setupIni() {
        if (iniFile.exists()) {
            try {
                ini = new Ini(iniFile);
            } catch (IOException e) {
                e.printStackTrace();
                setupIniFromTemplate();
            }
        } else {
            setupIniFromTemplate();
        }

    }

    private static void setupIniFromTemplate() {
        ini = new Ini();
        ini.put("options", "locale", "de");
        ini.put("options", "fontsize", "12");
        storeIniFile();
    }

    private static void storeIniFile() {
        try {
            ini.store(iniFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasUnsavedData() {
        return unsavedData;
    }

    public static void setUnsavedData(boolean unsavedData) {
        DataHelper.unsavedData = unsavedData;
    }

    public static boolean isLoading() {
        return loading;
    }

    public static void setLoading(boolean loading) {
        DataHelper.loading = loading;
    }

    public static boolean isUiChange() {
        return uiChange;
    }

    public static void setUiChange(boolean uiChange) {
        DataHelper.uiChange = uiChange;
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        DataHelper.locale = locale;
        ini.put("options", "locale", locale.toLanguageTag());
        storeIniFile();
    }

    public static int getFontsize() {
        return fontsize;
    }

    public static void setFontsize(int fontsize) {
        DataHelper.fontsize = fontsize;
        ini.put("options", "fontsize", fontsize);
        storeIniFile();
    }

    public static int getHighlightColorR() {
        return highlightColorR;
    }

    public static int getHighlightColorG() {
        return highlightColorG;
    }

    public static int getHighlightColorB() {
        return highlightColorB;
    }

    public static int getHighlightColorAlpha() {
        return highlightColorAlpha;
    }

    public static void setColor(int R, int G, int B, int ALPHA) {
        DataHelper.highlightColorR = R;
        DataHelper.highlightColorG = G;
        DataHelper.highlightColorB = B;
        DataHelper.highlightColorAlpha = ALPHA;
        ini.put("options", "color", R + "," + G + "," + B + "," + ALPHA);
        storeIniFile();
    }

    public static String getLookAndFeel() {
        return lookAndFeel;
    }

    public static void setLookAndFeel(String lookAndFeel) {
        DataHelper.lookAndFeel = lookAndFeel;
        ini.put("options", "lookAndFeel", lookAndFeel);
        storeIniFile();
    }

    public static Point getLocation() {
        return location;
    }

    public static void setLocation(Point location) {
        DataHelper.location = location;
    }
}
