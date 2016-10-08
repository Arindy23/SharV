package de.arindy.shadowrun.entities.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.arindy.shadowrun.gui.helper.Language;
import de.arindy.shadowrun.persistence.helper.MetatypDeserializer;
import de.arindy.shadowrun.persistence.helper.MetatypSerializer;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = MetatypDeserializer.class)
@JsonSerialize(using = MetatypSerializer.class)
@JsonRootName("metatyp")
public enum Metatyp {

    MENSCH("m",
            /*Kon*/new int[]{1, 6},
            /*Ges*/new int[]{1, 6},
            /*Rea*/new int[]{1, 6},
            /*Str*/new int[]{1, 6},
            /*Wil*/new int[]{1, 6},
			/*Log*/new int[]{1, 6},
			/*In*/new int[]{1, 6},
			/*Cha*/new int[]{1, 6},
			/*Edg*/new int[]{2, 7},
			/*Ess*/6),

    ELF("e",
			/*Kon*/new int[]{1, 6},
			/*Ges*/new int[]{2, 7},
			/*Rea*/new int[]{1, 6},
			/*Str*/new int[]{1, 6},
			/*Wil*/new int[]{1, 6},
			/*Log*/new int[]{1, 6},
			/*In*/new int[]{1, 6},
			/*Cha*/new int[]{3, 8},
			/*Edg*/new int[]{1, 6},
			/*Ess*/6),

    ZWERG("z",
			/*Kon*/new int[]{3, 8},
			/*Ges*/new int[]{1, 6},
			/*Rea*/new int[]{1, 5},
			/*Str*/new int[]{3, 8},
			/*Wil*/new int[]{2, 7},
			/*Log*/new int[]{1, 6},
			/*In*/new int[]{1, 6},
			/*Cha*/new int[]{1, 6},
			/*Edg*/new int[]{1, 6},
			/*Ess*/6),

    ORK("o",
			/*Kon*/new int[]{4, 9},
			/*Ges*/new int[]{1, 6},
			/*Rea*/new int[]{1, 6},
			/*Str*/new int[]{3, 8},
			/*Wil*/new int[]{1, 6},
			/*Log*/new int[]{1, 5},
			/*In*/new int[]{1, 6},
			/*Cha*/new int[]{1, 5},
			/*Edg*/new int[]{1, 6},
			/*Ess*/6),

    TROLL("t",
			/*Kon*/new int[]{5, 10},
			/*Ges*/new int[]{1, 5},
			/*Rea*/new int[]{1, 6},
			/*Str*/new int[]{5, 10},
			/*Wil*/new int[]{1, 6},
			/*Log*/new int[]{1, 5},
			/*In*/new int[]{1, 5},
			/*Cha*/new int[]{1, 4},
			/*Edg*/new int[]{1, 6},
			/*Ess*/6);

    private final String name;
    private final int[] kon;
    private final int[] ges;
    private final int[] rea;
    private final int[] str;
    private final int[] wil;
    private final int[] log;
    private final int[] in;
    private final int[] cha;
    private final int[] edg;
    private final int ess;

    Metatyp(String name, int[] kon, int[] ges, int[] rea, int[] str, int[] wil, int[] log, int[] in, int[] cha, int[] edg, int ess) {
        this.name = name;
        this.kon = kon;
        this.ges = ges;
        this.rea = rea;
        this.str = str;
        this.wil = wil;
        this.log = log;
        this.in = in;
        this.cha = cha;
        this.edg = edg;
        this.ess = ess;
    }

    public static Metatyp fromValue(final String value) {
        if (value != null) {
            for (Metatyp type : Metatyp.values()) {
                if (value.equalsIgnoreCase(type.name)) {
                    return type;
                }
            }
        }
        return null;
    }

    public int[] getKon() {
        return kon;
    }

    public int[] getGes() {
        return ges;
    }

    public int[] getRea() {
        return rea;
    }

    public int[] getStr() {
        return str;
    }

    public int[] getWil() {
        return wil;
    }

    public int[] getLog() {
        return log;
    }

    public int[] getIn() {
        return in;
    }

    public int[] getCha() {
        return cha;
    }

    public int[] getEdg() {
        return edg;
    }

    public int getEss() {
        return ess;
    }

    public String[] getMetamerkmale() {
        return Language.getString("metatyp." + name + ".merkmale").replaceFirst("%null%", "").split(",");
    }

    public String getValue() {
        return name;
    }

    @Override
    public String toString() {
        return Language.getString("metatyp." + name);
    }
}
