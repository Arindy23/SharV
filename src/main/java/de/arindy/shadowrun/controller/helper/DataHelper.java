package de.arindy.shadowrun.controller.helper;

import org.ini4j.Ini;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class DataHelper {

    private static final File CONFIG_PATH = new File(System.getProperty("user.home") + "/.srVHelper");
    private static boolean unsavedData = false;
    private static boolean loading = false;
    private static boolean uiChange = true;
    private static Point location;
    private static Locale locale;
    private static String initCharPath;
    private static int fontsize;
    private static File iniFile = new File(CONFIG_PATH.getAbsolutePath() + "/srV.ini");
    private static Ini ini;

    public static void init() {
        System.out.println(iniFile);
        CONFIG_PATH.mkdir();
        setupIni();
        String tempCharPath = ini.fetch("lastFile", "filePath");
        initCharPath = (tempCharPath != null && !tempCharPath.isEmpty()) ? tempCharPath : null;

        locale = new Locale(ini.fetch("options", "locale"));
        try {
            fontsize = Integer.valueOf(ini.fetch("options", "fontsize"));
        } catch (NumberFormatException nfe) {
            fontsize = 12;
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
        System.out.println("Boop");
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

    public static String getInitCharPath() {
        return initCharPath;
    }

    public static void setInitCharPath(String initCharPath) {
        DataHelper.initCharPath = initCharPath;
        ini.put("lastFile", "filePath", initCharPath);
        storeIniFile();
    }

    public static Point getLocation() {
        return location;
    }

    public static void setLocation(Point location) {
        DataHelper.location = location;
    }
}
