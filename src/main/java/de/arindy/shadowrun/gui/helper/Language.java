package de.arindy.shadowrun.gui.helper;

import java.util.ResourceBundle;

/**
 * Created by Arindy on 22/09/2016.
 */
public class Language {
    public static String getString(String key){
        return ResourceBundle.getBundle("lang/language").getString(key);
    }
    public static String[] getStringArray(String key){
        return ResourceBundle.getBundle("lang/language").getStringArray(key);
    }
}
