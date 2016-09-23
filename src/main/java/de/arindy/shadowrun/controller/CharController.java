package de.arindy.shadowrun.controller;

import de.arindy.shadowrun.Main;
import de.arindy.shadowrun.controller.helper.DataHelper;
import de.arindy.shadowrun.controller.listener.CustomDocumentListener;
import de.arindy.shadowrun.entities.Charakter;
import de.arindy.shadowrun.entities.types.Geschlecht;
import de.arindy.shadowrun.entities.types.MagRes;
import de.arindy.shadowrun.entities.types.Metatyp;
import de.arindy.shadowrun.entities.types.VorteilNachteil;
import de.arindy.shadowrun.gui.*;
import de.arindy.shadowrun.gui.actions.DialogBauer;
import de.arindy.shadowrun.gui.actions.ExitAction;
import de.arindy.shadowrun.gui.actions.LoadCharFile;
import de.arindy.shadowrun.gui.actions.SaveCharFile;
import de.arindy.shadowrun.gui.helper.JCustomTextField;
import de.arindy.shadowrun.gui.helper.Language;
import de.arindy.shadowrun.gui.listener.WindowCloseListener;
import de.arindy.shadowrun.gui.types.GBC;
import de.arindy.shadowrun.persistence.helper.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.ceil;

public class CharController {

    //<editor-fold desc="Variablen">
    public static Charakter character;
    private CharSheet charSheet;
    private MagResPanel magResPanel;
    private Zustandsmonitor zustandsmonitor;
    private VorteilNachteilPanel vorteilNachteilPanel;
    private CustomDocumentListener documentListener;
    private ActionListener comboBoxListener;
    private ActionListener edgeListener;
    private ActionListener ueberzaehligListener;
    private ActionListener koerperlichListener;
    private ActionListener geistigListener;
    private SaveCharFile saveCharFile = new SaveCharFile(this);
    private LoadCharFile loadCharFile = new LoadCharFile(this);

    private ArrayList<JCheckBox> edgeCheckBox;

    private ArrayList<JCheckBox> koerperlich;
    private ArrayList<JCheckBox> geistig;
    private ArrayList<JCheckBox> ueberzaehlig;
    //</editor-fold>

    public CharController(CharSheet charSheet) {
        this.charSheet = charSheet;
        magResPanel = new MagResPanel();
        zustandsmonitor = new Zustandsmonitor();
        vorteilNachteilPanel = new VorteilNachteilPanel();
        documentListener = e -> textFieldChanged((JCustomTextField) e.getDocument().getProperty("owner"));
        comboBoxListener = e -> comboBoxChanged((JComboBox) e.getSource());
        edgeListener = e -> edgeChanged((JCheckBox) e.getSource());
        ueberzaehligListener = e -> ueberzaehligChanged((JCheckBox) e.getSource());
        koerperlichListener = e -> schadenKoerperChanged((JCheckBox) e.getSource());
        geistigListener = e -> schadenGeistChanged((JCheckBox) e.getSource());

        setupEdgeAusgegeben(8);
        koerperlich = new ArrayList<>();
        geistig = new ArrayList<>();
        fillZustandPanel(zustandsmonitor.getpKoerperlich(), koerperlich, 6);
        fillZustandPanel(zustandsmonitor.getpGeistig(), geistig, 4);
        setupUeberzaehligPanel(20);
        updateUeberzaehligPanel();
        updateKoerperPanel();
        updateGeistPanel();
        updateEdgeAusgegeben();
        WindowCloseListener windowCloseListener = new WindowCloseListener(charSheet.getSheet());
        windowCloseListener.addAction(saveCharFile);
        charSheet.getSheet().addWindowListener(windowCloseListener);
        charSheet.setEnabledInputsPersoenlich(true);
        buildMenu();
        charSheet.getRight().add(zustandsmonitor.getPanel(), GBC.cZustand);
        addTabbedPanes();
        addListeners();
        loadInitCharacter();
    }

    public static File getDirectory() {
        URL url = VorteilNachteilVerwaltung.class.getProtectionDomain().getCodeSource().getLocation();
        File directory = null;
        try {
            directory = new File(url.toURI()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return directory;
    }

    public void updateTitle() {
        String titleBack = (character != null && character.getName() != null && !character.getName().isEmpty()) ? " - " + character.getName() : "";
        titleBack += (character != null && character.getStrassenname() != null && !character.getStrassenname().isEmpty()) ? " (" + character.getStrassenname() + ")" : "";
        charSheet.getSheet().setTitle(((DataHelper.unsavedData) ? "*" : "") + Main.TITLE + titleBack);
    }

    private void buildMenu(){
        charSheet.getMenuDatei().add(new ExitAction());
        charSheet.getMenuCharakter().add(loadCharFile);
        charSheet.getMenuCharakter().add(saveCharFile);
        charSheet.getMenuVerwaltung().add(new DialogBauer(charSheet.getSheet(), VorteilNachteilVerwaltung.getPanel()));;

        //noinspection ConstantConditions
        for (File lang : new File(getClass().getClassLoader().getResource("lang/").getFile()).listFiles()) {
            Locale loc = Locale.forLanguageTag(lang.getName().replace("language_","").replace(".properties",""));
            if (!loc.getDisplayName().equalsIgnoreCase("language")){
                JMenuItem item = new JMenuItem(loc.getDisplayName());
                item.addActionListener(e -> changeLocale(loc));
                charSheet.getMenuSprache().add(item);
            }
        }
    }

    private void changeLocale(Locale locale){
        DataHelper.languageChange = true;
        DataHelper.locale = locale;
        Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        window.dispose();
        synchronized (this){
            notifyAll();
        }
    }

    private void addTabbedPanes() {
        charSheet.getTabbed().add(magResPanel.getPanel());
        charSheet.getTabbed().add(vorteilNachteilPanel.getPanel());
    }

    private void addListeners() {
        charSheet.gettName().getDocument().addDocumentListener(documentListener);
        charSheet.gettMetatyp().addActionListener(comboBoxListener);
        charSheet.gettGeschlecht().addActionListener(comboBoxListener);
        charSheet.getlMagieResonanz().addActionListener(comboBoxListener);
        charSheet.gettAlter().getDocument().addDocumentListener(documentListener);
        charSheet.gettGroesse().getDocument().addDocumentListener(documentListener);
        charSheet.gettGewicht().getDocument().addDocumentListener(documentListener);
        charSheet.gettEthnie().getDocument().addDocumentListener(documentListener);
        charSheet.gettKonzept().getDocument().addDocumentListener(documentListener);
        charSheet.gettStrassenruf().getDocument().addDocumentListener(documentListener);
        charSheet.gettSchlechterRuf().getDocument().addDocumentListener(documentListener);
        charSheet.gettProminenz().getDocument().addDocumentListener(documentListener);
        charSheet.gettSonstiges().getDocument().addDocumentListener(documentListener);
        charSheet.gettKonstitution().getDocument().addDocumentListener(documentListener);
        charSheet.gettGeschicklichkeit().getDocument().addDocumentListener(documentListener);
        charSheet.gettReaktion().getDocument().addDocumentListener(documentListener);
        charSheet.gettStaerke().getDocument().addDocumentListener(documentListener);
        charSheet.gettWillenskraft().getDocument().addDocumentListener(documentListener);
        charSheet.gettLogik().getDocument().addDocumentListener(documentListener);
        charSheet.gettIntuition().getDocument().addDocumentListener(documentListener);
        charSheet.gettCharisma().getDocument().addDocumentListener(documentListener);
        charSheet.gettMagieResonanz().getDocument().addDocumentListener(documentListener);
        charSheet.gettEssenz().getDocument().addDocumentListener(documentListener);
        charSheet.gettEdge().getDocument().addDocumentListener(documentListener);
    }

    private void loadInitCharacter(){
        if(character ==null){
            character = new Charakter();
            character.setMetatyp(Metatyp.MENSCH);
            character.setGeschlecht(Geschlecht.WEIBLICH);
            character.setMagRes(MagRes.NONE);
        }
        else{
            mapChar();
            updateTitle();
        }
    }

    private void calcData() {
        character.setInitiative(character.getReaktion() + character.getIntuition());
        character.setAstralInitiative(character.getIntuition() * 2);
        character.setMatrixInitiative(character.getReaktion() + character.getIntuition());
        character.setMatrixInitiativeKalt(character.getDatenverarbeitung() + character.getIntuition());
        character.setMatrixInitiativeHeiss(character.getDatenverarbeitung() + character.getIntuition());
        character.setRiggingInitiative(character.getReaktion() + character.getIntuition());
        character.setRiggingInitiativeKalt(character.getDatenverarbeitung() + character.getIntuition());
        character.setRiggingInitiativeHeiß(character.getDatenverarbeitung() + character.getIntuition());
        character.setRiggingInitiativeDirekt(character.getReaktion() + character.getIntuition());

        character.setSelbstbeherrschung(character.getWillenskraft() + character.getCharisma());
        character.setMenschenkenntnis(character.getIntuition() + character.getCharisma());
        character.setErinnerungsvermoegen(character.getWillenskraft() + character.getLogik());
        character.setTragen(character.getKonstitution() + character.getStaerke());
        character.setGehen(character.getGeschicklichkeit() * 2);
        character.setLaufen(character.getGeschicklichkeit() * 4);
        character.setSprinten(character.getLaufen() + character.getStaerke());

        character.setLimitKoerper((int) ceil((double) ((character.getStaerke() * 2) + character.getKonstitution() + character.getReaktion()) / 3));
        character.setLimitGeist((int) ceil((double) ((character.getLogik() * 2) + character.getIntuition() + character.getWillenskraft()) / 3));
        character.setLimitSozial((int) ceil((double) ((character.getCharisma() * 2) + character.getWillenskraft() + character.getEssenz()) / 3));
    }

    private void mapCalc() {
        calcData();
        charSheet.gettInitiative().setText("" + character.getInitiative());
        charSheet.gettAstralInitiative().setText("" + character.getAstralInitiative());
        charSheet.gettMatrixInitiativeAR().setText("" + character.getMatrixInitiative());
        charSheet.gettMatrixInitiativekalt().setText("" + character.getMatrixInitiativeKalt());
        charSheet.gettMatrixInitiativeheiß().setText("" + character.getMatrixInitiativeHeiss());
        charSheet.gettRiggingInitiative().setText("" + character.getRiggingInitiative());
        charSheet.gettRiggingInitiativekalt().setText("" + character.getRiggingInitiativeKalt());
        charSheet.gettRiggingInitiativeheiß().setText("" + character.getRiggingInitiativeHeiß());
        charSheet.gettRiggingInitiativedirekt().setText("" + character.getRiggingInitiativeDirekt());

        charSheet.gettSelbstbeherrschung().setText("" + character.getSelbstbeherrschung());
        charSheet.gettMenschenkenntnis().setText("" + character.getMenschenkenntnis());
        charSheet.gettErinnerungsvermoegen().setText("" + character.getErinnerungsvermoegen());
        charSheet.gettHebenTragen().setText("" + character.getTragen());

        charSheet.gettGehen().setText("" + character.getGehen());
        charSheet.gettLaufen().setText("" + character.getLaufen());
        charSheet.gettSprinten().setText("" + character.getSprinten());

        charSheet.gettLimitKoerper().setText("" + character.getLimitKoerper());
        charSheet.gettLimitGeist().setText("" + character.getLimitGeist());
        charSheet.gettLimitSozial().setText("" + character.getLimitSozial());

    }

    public void mapChar() {
        charSheet.gettName().setText((character.getStrassenname() != null && !character.getStrassenname().isEmpty()) ? character.getName() + " (" + character.getStrassenname() + ")" : character.getName());
        charSheet.gettMetatyp().setSelectedItem(character.getMetatyp());
        charSheet.gettGeschlecht().setSelectedItem(character.getGeschlecht());
        charSheet.gettAlter().setText("" + character.getAlter());
        charSheet.gettGroesse().setText("" + character.getGroesse());
        charSheet.gettGewicht().setText("" + character.getGewicht());
        charSheet.gettEthnie().setText(character.getEthnie());
        charSheet.gettKonzept().setText((character.getKonzept() != null) ? character.getKonzept() : "");
        charSheet.gettStrassenruf().setText("" + character.getStrassenruf());
        charSheet.gettSchlechterRuf().setText("" + character.getSchlechterRuf());
        charSheet.gettProminenz().setText("" + character.getProminenz());
        charSheet.gettSonstiges().setText("" + character.getSonstiges());

        mapKarma();

        charSheet.gettKonstitution().setText("" + character.getKonstitution());
        charSheet.gettGeschicklichkeit().setText("" + character.getGeschicklichkeit());
        charSheet.gettReaktion().setText("" + character.getReaktion());
        charSheet.gettStaerke().setText("" + character.getStaerke());

        charSheet.gettWillenskraft().setText("" + character.getWillenskraft());
        charSheet.gettLogik().setText("" + character.getLogik());
        charSheet.gettIntuition().setText("" + character.getIntuition());
        charSheet.gettCharisma().setText("" + character.getCharisma());

        charSheet.getlMagieResonanz().setSelectedItem(character.getMagRes());
        magResPanel.setMagRes(character.getMagRes());
        if (character.getMagRes() == MagRes.NONE) {
            charSheet.gettMagieResonanz().setEnabled(false);
            charSheet.gettMagieResonanz().setText("");
        } else {
            charSheet.gettMagieResonanz().setEnabled(true);
            charSheet.gettMagieResonanz().setText("" + character.getMagieResonanz());
        }
        charSheet.gettEssenz().setText("" + character.getEssenz());
        charSheet.gettEdge().setText("" + character.getEdge());

        updateEdgeAusgegeben();
        updateUeberzaehligPanel();
        updateKoerperPanel();
        updateGeistPanel();
        updateVerletzungsMod();

        mapCalc();
        mapVorteilNachteil();

        charSheet.getlName().grabFocus();
    }

    private void mapVorteilNachteil() {
        java.util.List<String> list = character.getVorteileNachteile();
        if (list != null) {
            for (String id : list) {
                File file = new File(getDirectory().getAbsolutePath() + "/srV/vn/" + id.split("\\?")[0] + ".srVvn");
                if (file.exists()) {
                    VorteilNachteil item = (VorteilNachteil) JsonHandler.readFile(file, VorteilNachteil.class);
                    if (item != null) {
                        item.setZusatzInfo((id.split("\\?").length > 1) ? id.split("\\?")[1] : "");
                        vorteilNachteilPanel.getList().add(item);
                    }
                }
            }
        }
    }

    private void setupEdgeAusgegeben(int size) {
        edgeCheckBox = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            edgeCheckBox.add(new JCheckBox());
            edgeCheckBox.get(i).setHorizontalAlignment(JCheckBox.CENTER);
            edgeCheckBox.get(i).addActionListener(edgeListener);
            charSheet.getEdgeAusgegeben().add(edgeCheckBox.get(i));
        }
    }

    private void updateEdgeAusgegeben() {
        for (int i = 0; i < edgeCheckBox.size(); i++) {
            if (character != null && character.getEdge() < i + 1) {
                edgeCheckBox.get(i).setEnabled(false);
            } else {
                edgeCheckBox.get(i).setEnabled(true);
            }
            if (character != null && character.getMetatyp() != null && character.getMetatyp().getEdg()[1] < i + 1) {
                edgeCheckBox.get(i).setBorderPaintedFlat(true);
            } else {
                edgeCheckBox.get(i).setBorderPaintedFlat(false);
            }
            if (character != null && character.getEdgeAusgegeben() >= i + 1) {
                edgeCheckBox.get(i).setSelected(true);
            } else {
                edgeCheckBox.get(i).setSelected(false);
            }
        }
    }

    private void fillZustandPanel(JPanel panel, ArrayList<JCheckBox> arrayList, int rows) {
        panel.setLayout(new GridLayout(4, 0));
        for (int i = 0; i < rows * 3; i++) {
            arrayList.add(new JCheckBox());
            if (panel == zustandsmonitor.getpKoerperlich()) {
                arrayList.get(i).setHorizontalAlignment(JCheckBox.CENTER);
                arrayList.get(i).addActionListener(koerperlichListener);
            }
            if (panel == zustandsmonitor.getpGeistig()) {
                arrayList.get(i).setHorizontalAlignment(JCheckBox.CENTER);
                arrayList.get(i).addActionListener(geistigListener);
            }

        }
        for (int i = 0; i < rows * 4; i++) {
            if (i / rows == 0) {
                //panel.add(arrayList.get(i*3));
                panel.add(new JLabel("-" + (i + 1)));
            }
            if (i / rows == 1) {
                panel.add(arrayList.get((i - rows) * 3));
                //panel.add(new JLabel("a"+i*3));
            }
            if (i / rows == 2) {
                panel.add(arrayList.get((i - rows * 2) * 3 + 1));
                //panel.add(new JLabel("b"+((i-rows/3)*3+1)));
            }
            if (i / rows == 3) {
                panel.add(arrayList.get((i - rows * 3) * 3 + 2));
                //panel.add(new JLabel("c"+((i-rows*2/3)*3+2)));
            }
        }
    }

    private void setupUeberzaehligPanel(int konst) {
        zustandsmonitor.getPcueberzaehlig().setLayout(new GridLayout(2, 0));
        ueberzaehlig = new ArrayList<>();
        for (int i = 0; i < konst; i++) {
            ueberzaehlig.add(new JCheckBox());
            ueberzaehlig.get(i).setHorizontalAlignment(JCheckBox.CENTER);
            ueberzaehlig.get(i).addActionListener(ueberzaehligListener);
            zustandsmonitor.getPcueberzaehlig().add(ueberzaehlig.get(i));
        }
    }

    private void updateUeberzaehligPanel() {
        for (int i = 0; i < ueberzaehlig.size(); i++) {
            if (character != null && character.getKonstitution() < i + 1) {
                ueberzaehlig.get(i).setEnabled(false);
                ueberzaehlig.get(i).setBorderPaintedFlat(true);
            } else {
                ueberzaehlig.get(i).setEnabled(true);
                ueberzaehlig.get(i).setBorderPaintedFlat(false);
            }
            if (character != null && character.getSchadenUeberzaehlig() >= i + 1) {
                ueberzaehlig.get(i).setSelected(true);
            } else {
                ueberzaehlig.get(i).setSelected(false);
            }
        }
    }

    private void updateKoerperPanel() {
        for (int i = 0; i < koerperlich.size(); i++) {
            if (character != null && ((int) ceil((8 + ((double) character.getKonstitution() / 2)))) < i + 1) {
                koerperlich.get(i).setEnabled(false);
                koerperlich.get(i).setBorderPaintedFlat(true);
            } else {
                koerperlich.get(i).setEnabled(true);
                koerperlich.get(i).setBorderPaintedFlat(false);
            }
            if (character != null && character.getSchadenKoerper() >= i + 1) {
                koerperlich.get(i).setSelected(true);
            } else {
                koerperlich.get(i).setSelected(false);
            }
        }
    }

    private void updateGeistPanel() {
        for (int i = 0; i < geistig.size(); i++) {
            if (character != null && ((int) ceil((8 + ((double) character.getWillenskraft() / 2)))) < i + 1) {
                geistig.get(i).setEnabled(false);
                geistig.get(i).setBorderPaintedFlat(true);
            } else {
                geistig.get(i).setEnabled(true);
                geistig.get(i).setBorderPaintedFlat(false);
            }
            if (character != null && character.getSchadenKoerper() >= i + 1) {
                geistig.get(i).setSelected(true);
            } else {
                geistig.get(i).setSelected(false);
            }
        }
    }

    private void mapKarma() {
        charSheet.getlKarma().setText(Language.getString("charSheet.persDaten.karma") + ": " + character.getKarma() + "\\" + character.getGesamtkarma());
        charSheet.getPvKarma().setMaximum(character.getGesamtkarma());
        charSheet.getPvKarma().setValue(character.getKarma());
    }

    private void textFieldChanged(JCustomTextField textField) {
        String text = textField.getText();
        if (textField == charSheet.gettName()) {
            character.setName(text.replaceAll("\\(.*\\)?", "").trim());
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(text);
            while (m.find()) character.setStrassenname(m.group(1));
        }
        if (textField == charSheet.gettEthnie()) character.setEthnie(text.trim());
        if (textField == charSheet.gettKonzept()) character.setKonzept(text.trim());
        if (text.isEmpty()) text = "" + 0;
        if (textField == charSheet.gettAlter()) character.setAlter(Integer.valueOf(text));
        if (textField == charSheet.gettGroesse()) character.setGroesse(Integer.valueOf(text));
        if (textField == charSheet.gettGewicht()) character.setGewicht(Integer.valueOf(text));
        if (textField == charSheet.gettStrassenruf()) character.setStrassenruf(Integer.valueOf(text));
        if (textField == charSheet.gettSchlechterRuf()) character.setSchlechterRuf(Integer.valueOf(text));
        if (textField == charSheet.gettProminenz()) character.setProminenz(Integer.valueOf(text));
        if (textField == charSheet.gettSonstiges()) character.setSonstiges(Integer.valueOf(text));
        if (textField == charSheet.gettKonstitution()) {
            character.setKonstitution(Integer.valueOf(text));
            updateUeberzaehligPanel();
            updateKoerperPanel();
        }
        if (textField == charSheet.gettGeschicklichkeit()) character.setGeschicklichkeit(Integer.valueOf(text));
        if (textField == charSheet.gettReaktion()) character.setReaktion(Integer.valueOf(text));
        if (textField == charSheet.gettStaerke()) character.setStaerke(Integer.valueOf(text));
        if (textField == charSheet.gettWillenskraft()) {
            character.setWillenskraft(Integer.valueOf(text));
            updateGeistPanel();
        }
        if (textField == charSheet.gettLogik()) character.setLogik(Integer.valueOf(text));
        if (textField == charSheet.gettIntuition()) character.setIntuition(Integer.valueOf(text));
        if (textField == charSheet.gettCharisma()) character.setCharisma(Integer.valueOf(text));
        if (textField == charSheet.gettMagieResonanz()) character.setMagieResonanz(Integer.valueOf(text));
        if (textField == charSheet.gettEssenz()) character.setEssenz(Integer.valueOf(text));
        if (textField == charSheet.gettEdge()) {
            character.setEdge(Integer.valueOf(text));
            updateEdgeAusgegeben();
        }

        mapCalc();
        DataHelper.unsavedData = true;
        updateTitle();
    }

    private void comboBoxChanged(JComboBox comboBox) {
        if (comboBox == charSheet.gettMetatyp()) {
            character.setMetatyp((Metatyp) comboBox.getSelectedItem());
            updateEdgeAusgegeben();
            int sprintMod = (character.getMetatyp() == Metatyp.ZWERG || character.getMetatyp() == Metatyp.TROLL) ? 1 : 2;
            charSheet.getlSprinten().setText("Sprint(+" + sprintMod + "m):");
        }
        if (comboBox == charSheet.gettGeschlecht()) character.setGeschlecht((Geschlecht) comboBox.getSelectedItem());
        if (comboBox == charSheet.getlMagieResonanz()) {
            character.setMagRes((MagRes) comboBox.getSelectedItem());
            magResPanel.setMagRes((MagRes) comboBox.getSelectedItem());
        }
        DataHelper.unsavedData = true;
        updateTitle();
    }

    private int checkBoxChanged(ArrayList<JCheckBox> arrayList, JCheckBox checkBox) {
        int clickedIndex = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (checkBox == arrayList.get(i)) {
                clickedIndex = i;
                break;
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (i < clickedIndex) arrayList.get(i).setSelected(true);
            if (i > clickedIndex) arrayList.get(i).setSelected(false);
        }
        return clickedIndex;
    }

    private void ueberzaehligChanged(JCheckBox checkBox) {
        int clickedIndex = checkBoxChanged(ueberzaehlig, checkBox);
        character.setSchadenUeberzaehlig((checkBox.isSelected()) ? clickedIndex + 1 : clickedIndex);
    }

    private void schadenKoerperChanged(JCheckBox checkBox) {
        int clickedIndex = checkBoxChanged(koerperlich, checkBox);
        character.setSchadenKoerper((checkBox.isSelected()) ? clickedIndex + 1 : clickedIndex);
        updateVerletzungsMod();
    }

    private void schadenGeistChanged(JCheckBox checkBox) {
        int clickedIndex = checkBoxChanged(geistig, checkBox);
        character.setSchadenGeist((checkBox.isSelected()) ? clickedIndex + 1 : clickedIndex);
        updateVerletzungsMod();
    }

    private void updateVerletzungsMod() {
        int mod = -(character.getSchadenKoerper() / 3 + character.getSchadenGeist() / 3);
        zustandsmonitor.getVerletzungsmodifikator().setText("" + mod);
        if (mod < 0) {
            zustandsmonitor.getVerletzungsmodifikator().setForeground(Color.red);
        } else {
            zustandsmonitor.getVerletzungsmodifikator().setForeground(UIManager.getColor("Label.foreground"));
        }
    }

    private void edgeChanged(JCheckBox checkBox) {
        int clickedIndex = checkBoxChanged(edgeCheckBox, checkBox);
        character.setEdgeAusgegeben((checkBox.isSelected()) ? clickedIndex + 1 : clickedIndex);
    }

    public CharSheet getCharSheet() {
        return charSheet;
    }

}
