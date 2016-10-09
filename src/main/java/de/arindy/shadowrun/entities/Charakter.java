package de.arindy.shadowrun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.arindy.shadowrun.entities.types.Geschlecht;
import de.arindy.shadowrun.entities.types.MagRes;
import de.arindy.shadowrun.entities.types.Metatyp;

import java.util.List;

public class Charakter {
    private String color;
    //Allgemein:
    private String name;
    private String strassenname;
    private Metatyp metatyp;
    private int cred;
    private Geschlecht geschlecht;
    private MagRes magRes;
    private int alter;
    private int groesse;
    private int gewicht;
    private String ethnie;
    private String konzept;
    private int strassenruf;
    private int schlechterRuf;
    private int prominenz;
    private int karma;
    private int gesamtkarma;

    //Attribute:
    private int konstitution;
    private int geschicklichkeit;
    private int reaktion;
    private int staerke;

    private int willenskraft;
    private int logik;
    private int intuition;
    private int charisma;

    private int edge;
    private int edgeAusgegeben;
    @JsonIgnore
    private int essenz = 6;
    private int magieResonanz;

    private int schadenKoerper;
    private int schadenGeist;
    private int schadenUeberzaehlig;

    private List<String> vorteileNachteile;

    //Matrixattribute:
    @JsonIgnore
    private int angriff;
    @JsonIgnore
    private int schleicher;
    @JsonIgnore
    private int datenverarbeitung;
    @JsonIgnore
    private int firewall;

    //Limits:
    @JsonIgnore
    private int limitKoerper;
    @JsonIgnore
    private int limitGeist;
    @JsonIgnore
    private int limitSozial;

    @JsonIgnore
    private int initiative;
    @JsonIgnore
    private int matrixInitiative;
    @JsonIgnore
    private int matrixInitiativeKalt;
    @JsonIgnore
    private int matrixInitiativeHeiss;
    @JsonIgnore
    private int riggingInitiative;
    @JsonIgnore
    private int riggingInitiativeKalt;
    @JsonIgnore
    private int riggingInitiativeHeiß;
    @JsonIgnore
    private int riggingInitiativeDirekt;
    @JsonIgnore
    private int astralInitiative;

    @JsonIgnore
    private int selbstbeherrschung;
    @JsonIgnore
    private int menschenkenntnis;
    @JsonIgnore
    private int erinnerungsvermoegen;
    @JsonIgnore
    private int tragen;
    @JsonIgnore
    private int gehen;
    @JsonIgnore
    private int laufen;
    @JsonIgnore
    private int sprinten;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrassenname() {
        return strassenname;
    }

    public void setStrassenname(String strassenname) {
        this.strassenname = strassenname;
    }

    public int getCred() {
        return cred;
    }

    public void setCred(int cred) {
        this.cred = cred;
    }

    public Metatyp getMetatyp() {
        return metatyp;
    }

    public void setMetatyp(Metatyp metatyp) {
        this.metatyp = metatyp;
    }

    public Geschlecht getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    public MagRes getMagRes() {
        return magRes;
    }

    public void setMagRes(MagRes magRes) {
        this.magRes = magRes;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public int getGroesse() {
        return groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public String getEthnie() {
        return ethnie;
    }

    public void setEthnie(String ethnie) {
        this.ethnie = ethnie;
    }

    public String getKonzept() {
        return konzept;
    }

    public void setKonzept(String konzept) {
        this.konzept = konzept;
    }

    public int getStrassenruf() {
        return strassenruf;
    }

    public void setStrassenruf(int strassenruf) {
        this.strassenruf = strassenruf;
    }

    public int getSchlechterRuf() {
        return schlechterRuf;
    }

    public void setSchlechterRuf(int schlechterRuf) {
        this.schlechterRuf = schlechterRuf;
    }

    public int getProminenz() {
        return prominenz;
    }

    public void setProminenz(int prominenz) {
        this.prominenz = prominenz;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getGesamtkarma() {
        return gesamtkarma;
    }

    public void setGesamtkarma(int gesamtkarma) {
        this.gesamtkarma = gesamtkarma;
    }

    public int getKonstitution() {
        return konstitution;
    }

    public void setKonstitution(int konstitution) {
        this.konstitution = konstitution;
    }

    public int getGeschicklichkeit() {
        return geschicklichkeit;
    }

    public void setGeschicklichkeit(int geschicklichkeit) {
        this.geschicklichkeit = geschicklichkeit;
    }

    public int getReaktion() {
        return reaktion;
    }

    public void setReaktion(int reaktion) {
        this.reaktion = reaktion;
    }

    public int getStaerke() {
        return staerke;
    }

    public void setStaerke(int staerke) {
        this.staerke = staerke;
    }

    public int getWillenskraft() {
        return willenskraft;
    }

    public void setWillenskraft(int willenskraft) {
        this.willenskraft = willenskraft;
    }

    public int getLogik() {
        return logik;
    }

    public void setLogik(int logik) {
        this.logik = logik;
    }

    public int getIntuition() {
        return intuition;
    }

    public void setIntuition(int intuition) {
        this.intuition = intuition;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }

    public int getEdgeAusgegeben() {
        return edgeAusgegeben;
    }

    public void setEdgeAusgegeben(int edgeAusgegeben) {
        this.edgeAusgegeben = edgeAusgegeben;
    }

    public int getEssenz() {
        return essenz;
    }

    public void setEssenz(int essenz) {
        this.essenz = essenz;
    }

    public int getMagieResonanz() {
        return magieResonanz;
    }

    public void setMagieResonanz(int magieResonanz) {
        this.magieResonanz = magieResonanz;
    }

    public int getSchadenKoerper() {
        return schadenKoerper;
    }

    public void setSchadenKoerper(int schadenKoerper) {
        this.schadenKoerper = schadenKoerper;
    }

    public int getSchadenGeist() {
        return schadenGeist;
    }

    public void setSchadenGeist(int schadenGeist) {
        this.schadenGeist = schadenGeist;
    }

    public int getSchadenUeberzaehlig() {
        return schadenUeberzaehlig;
    }

    public void setSchadenUeberzaehlig(int schadenUeberzaehlig) {
        this.schadenUeberzaehlig = schadenUeberzaehlig;
    }

    public int getAngriff() {
        return angriff;
    }

    public void setAngriff(int angriff) {
        this.angriff = angriff;
    }

    public int getSchleicher() {
        return schleicher;
    }

    public void setSchleicher(int schleicher) {
        this.schleicher = schleicher;
    }

    public int getDatenverarbeitung() {
        return datenverarbeitung;
    }

    public void setDatenverarbeitung(int datenverarbeitung) {
        this.datenverarbeitung = datenverarbeitung;
    }

    public int getFirewall() {
        return firewall;
    }

    public void setFirewall(int firewall) {
        this.firewall = firewall;
    }

    public int getLimitKoerper() {
        return limitKoerper;
    }

    public void setLimitKoerper(int limitKoerper) {
        this.limitKoerper = limitKoerper;
    }

    public int getLimitGeist() {
        return limitGeist;
    }

    public void setLimitGeist(int limitGeist) {
        this.limitGeist = limitGeist;
    }

    public int getLimitSozial() {
        return limitSozial;
    }

    public void setLimitSozial(int limitSozial) {
        this.limitSozial = limitSozial;
    }

    public List<String> getVorteileNachteile() {
        return vorteileNachteile;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getMatrixInitiative() {
        return matrixInitiative;
    }

    public void setMatrixInitiative(int matrixInitiative) {
        this.matrixInitiative = matrixInitiative;
    }

    public int getMatrixInitiativeKalt() {
        return matrixInitiativeKalt;
    }

    public void setMatrixInitiativeKalt(int matrixInitiativeKalt) {
        this.matrixInitiativeKalt = matrixInitiativeKalt;
    }

    public int getMatrixInitiativeHeiss() {
        return matrixInitiativeHeiss;
    }

    public void setMatrixInitiativeHeiss(int matrixInitiativeHeiss) {
        this.matrixInitiativeHeiss = matrixInitiativeHeiss;
    }

    public int getRiggingInitiative() {
        return riggingInitiative;
    }

    public void setRiggingInitiative(int riggingInitiative) {
        this.riggingInitiative = riggingInitiative;
    }

    public int getRiggingInitiativeKalt() {
        return riggingInitiativeKalt;
    }

    public void setRiggingInitiativeKalt(int riggingInitiativeKalt) {
        this.riggingInitiativeKalt = riggingInitiativeKalt;
    }

    public int getRiggingInitiativeHeiß() {
        return riggingInitiativeHeiß;
    }

    public void setRiggingInitiativeHeiß(int riggingInitiativeHeiß) {
        this.riggingInitiativeHeiß = riggingInitiativeHeiß;
    }

    public int getRiggingInitiativeDirekt() {
        return riggingInitiativeDirekt;
    }

    public void setRiggingInitiativeDirekt(int riggingInitiativeDirekt) {
        this.riggingInitiativeDirekt = riggingInitiativeDirekt;
    }

    public int getAstralInitiative() {
        return astralInitiative;
    }

    public void setAstralInitiative(int astralInitiative) {
        this.astralInitiative = astralInitiative;
    }

    public int getSelbstbeherrschung() {
        return selbstbeherrschung;
    }

    public void setSelbstbeherrschung(int selbstbeherrschung) {
        this.selbstbeherrschung = selbstbeherrschung;
    }

    public int getMenschenkenntnis() {
        return menschenkenntnis;
    }

    public void setMenschenkenntnis(int menschenkenntnis) {
        this.menschenkenntnis = menschenkenntnis;
    }

    public int getErinnerungsvermoegen() {
        return erinnerungsvermoegen;
    }

    public void setErinnerungsvermoegen(int erinnerungsvermoegen) {
        this.erinnerungsvermoegen = erinnerungsvermoegen;
    }

    public int getTragen() {
        return tragen;
    }

    public void setTragen(int tragen) {
        this.tragen = tragen;
    }

    public int getGehen() {
        return gehen;
    }

    public void setGehen(int gehen) {
        this.gehen = gehen;
    }

    public int getLaufen() {
        return laufen;
    }

    public void setLaufen(int laufen) {
        this.laufen = laufen;
    }

    public int getSprinten() {
        return sprinten;
    }

    public void setSprinten(int sprinten) {
        this.sprinten = sprinten;
    }
}
