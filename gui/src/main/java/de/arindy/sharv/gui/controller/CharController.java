package de.arindy.sharv.gui.controller;

import de.arindy.sharv.Language;
import de.arindy.sharv.character.Charakter;
import de.arindy.sharv.character.Geschlecht;
import de.arindy.sharv.character.MagRes;
import de.arindy.sharv.character.Metatyp;
import de.arindy.sharv.character.json.VorteilNachteil;
import de.arindy.sharv.gui.CharSheet;
import de.arindy.sharv.gui.MagResPanel;
import de.arindy.sharv.gui.Main;
import de.arindy.sharv.gui.Zustandsmonitor;
import de.arindy.sharv.gui.actions.DialogBauer;
import de.arindy.sharv.gui.actions.ExitAction;
import de.arindy.sharv.gui.actions.LoadCharFile;
import de.arindy.sharv.gui.actions.SaveCharFile;
import de.arindy.sharv.gui.controller.helper.DataHelper;
import de.arindy.sharv.gui.controller.listener.CustomDocumentListener;
import de.arindy.sharv.gui.helper.JCustomTextField;
import de.arindy.sharv.gui.listener.WindowCloseListener;
import de.arindy.sharv.gui.tabs.VorteilNachteilPanel;
import de.arindy.sharv.gui.verwaltung.HiglightColorChooser;
import de.arindy.sharv.gui.verwaltung.VorteilNachteilVerwaltung;
import de.arindy.sharv.persistence.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.ceil;

public class CharController {

    //<editor-fold desc="Variablen">
    public static Charakter character;
    JCheckBoxMenuItem laf;
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
    private List<JCheckBox> edgeCheckBox;
    private List<JCheckBox> koerperlich;
    private List<JCheckBox> geistig;
    private List<JCheckBox> ueberzaehlig;
    //</editor-fold>

    public CharController(CharSheet charSheet) {
        this.charSheet = charSheet;
        magResPanel = new MagResPanel();
        zustandsmonitor = charSheet.getZustandsmonitor();
        vorteilNachteilPanel = new VorteilNachteilPanel();
        documentListener = e -> textFieldChanged((JCustomTextField) e.getDocument().getProperty("owner"));
        comboBoxListener = e -> comboBoxChanged((JComboBox) e.getSource());
        edgeListener = e -> edgeChanged((JCheckBox) e.getSource());
        ueberzaehligListener = e -> ueberzaehligChanged((JCheckBox) e.getSource());
        koerperlichListener = e -> schadenKoerperChanged((JCheckBox) e.getSource());
        geistigListener = e -> schadenGeistChanged((JCheckBox) e.getSource());

        setupForms();
        addListeners();
        updateUeberzaehligPanel();
        updateKoerperPanel();
        updateGeistPanel();
        updateEdgeAusgegeben();
        WindowCloseListener windowCloseListener = new WindowCloseListener(charSheet.getSheet());
        windowCloseListener.addAction(saveCharFile);
        charSheet.getSheet().addWindowListener(windowCloseListener);
        charSheet.setEnabledInputsPersoenlich(true);
        buildMenu();
        addTabbedPanes();
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

    private void setupForms() {
        for (JCheckBox cb : zustandsmonitor.getUeberzaehlig()) {
            cb.addActionListener(ueberzaehligListener);
        }
        for (JCheckBox cb : charSheet.getEdgeCheckBox()) {
            cb.addActionListener(edgeListener);
        }


        for (JCheckBox cb : zustandsmonitor.getKoerperlich()) {
            cb.addActionListener(koerperlichListener);
        }

        for (JCheckBox cb : zustandsmonitor.getGeistig()) {
            cb.addActionListener(geistigListener);
        }
    }

    public void updateTitle() {
        if (!DataHelper.isLoading()) {
            String titleFront = (character != null && character.getName() != null && !character.getName().isEmpty()) ? character.getName() : "";
            if (!titleFront.isEmpty())
                titleFront += (character != null && character.getStrassenname() != null && !character.getStrassenname().isEmpty()) ? " (" + character.getStrassenname() + ")" + " - " : " - ";
            charSheet.getSheet().setTitle(((DataHelper.hasUnsavedData()) ? "*" : "") + titleFront + Main.TITLE);
        }
    }

    private void buildMenu() {
        charSheet.getMenuDatei().add(new ExitAction());
        charSheet.getMenuCharakter().add(loadCharFile);
        charSheet.getMenuCharakter().add(saveCharFile);
        charSheet.getMenuVerwaltung().add(new DialogBauer(charSheet.getSheet(), new VorteilNachteilVerwaltung()));

        final String path = "lang/";
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        if (jarFile.isFile()) {  // Run with JAR file
            try {
                final JarFile jar = new JarFile(jarFile);
                final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
                while (entries.hasMoreElements()) {
                    final String name = entries.nextElement().getName();
                    if (name.startsWith(path)) { //filter according to the path
                        loadLanguageFile(new File(getClass().getClassLoader().getResource(name).getFile()));
                    }
                }
                jar.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        } else { // Run with IDE
            //noinspection ConstantConditions
            for (File lang : new File(getClass().getClassLoader().getResource(path).getFile()).listFiles()) {
                loadLanguageFile(lang);
            }
        }

        Map<Integer, String> fontsizes = new TreeMap<>();
        fontsizes.put(18, Language.getString("menu.options.fontsize.huge"));
        fontsizes.put(15, Language.getString("menu.options.fontsize.big"));
        fontsizes.put(11, Language.getString("menu.options.fontsize.normal"));

        for (Map.Entry<Integer, String> entry : fontsizes.entrySet()) {
            int fontsize = entry.getKey();
            JMenuItem item = new JMenuItem(entry.getValue());
            if (fontsize == DataHelper.getFontsize()) {
                item.setEnabled(false);
            }
            item.addActionListener(e -> changeFontsize(fontsize));
            charSheet.getMenuSchriftgroesse().add(item);
        }
        laf = new JCheckBoxMenuItem(Language.getString("menu.options.customTheme"));
        laf.addActionListener(e -> changeLookAndFeel());
        DialogBauer highlightColor = new DialogBauer(charSheet.getSheet(), new HiglightColorChooser());
        if (UIManager.getLookAndFeel().getName().equalsIgnoreCase("TinyLookAndFeel")) {
            laf.setSelected(true);
            highlightColor.setEnabled(true);
        } else {
            laf.setSelected(false);
            highlightColor.setEnabled(false);

        }
        charSheet.getMenuOptions().add(laf);
        charSheet.getMenuOptions().add(highlightColor);
    }

    private void changeLookAndFeel() {
        if (laf.isSelected()) {
            DataHelper.setLookAndFeel("TinyLookAndFeel");
        } else {
            DataHelper.setLookAndFeel("System");
        }
        reloadUI();
    }

    private void loadLanguageFile(File lang) {
        Locale loc = Locale.forLanguageTag(lang.getName().replace("language_", "").replace(".properties", ""));
        if (!loc.getDisplayName().equalsIgnoreCase("language") && !loc.getDisplayName().equalsIgnoreCase("lang")) {
            JMenuItem item = new JMenuItem(loc.getDisplayName());
            if (loc.toLanguageTag().equalsIgnoreCase(DataHelper.getLocale().toLanguageTag())) {
                item.setEnabled(false);
            }
            item.addActionListener(e -> changeLocale(loc));
            charSheet.getMenuSprache().add(item);
        }
    }

    private void changeLocale(Locale locale) {
        DataHelper.setLocale(locale);
        reloadUI();
    }

    private void changeFontsize(int fontsize) {
        DataHelper.setFontsize(fontsize);
        reloadUI();
    }

    private void reloadUI() {
        DataHelper.setUiChange(true);
        Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();

        while (window.getClass() != JFrame.class) {
            window = window.getOwner();
        }

        DataHelper.setLocation(window.getLocation());
        window.dispose();
        synchronized (this) {
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
        charSheet.gettKonstitution().getDocument().addDocumentListener(documentListener);
        charSheet.gettGeschicklichkeit().getDocument().addDocumentListener(documentListener);
        charSheet.gettReaktion().getDocument().addDocumentListener(documentListener);
        charSheet.gettStaerke().getDocument().addDocumentListener(documentListener);
        charSheet.gettWillenskraft().getDocument().addDocumentListener(documentListener);
        charSheet.gettLogik().getDocument().addDocumentListener(documentListener);
        charSheet.gettIntuition().getDocument().addDocumentListener(documentListener);
        charSheet.gettCharisma().getDocument().addDocumentListener(documentListener);
        charSheet.gettMagieResonanz().getDocument().addDocumentListener(documentListener);
        charSheet.gettEdge().getDocument().addDocumentListener(documentListener);
    }

    private void loadInitCharacter() {
        DataHelper.setLoading(true);
        if (character == null) {
            character = new Charakter();
            character.setMetatyp(Metatyp.MENSCH);
            character.setGeschlecht(Geschlecht.WEIBLICH);
            character.setMagRes(MagRes.NONE);
            mapKarma();
        }
        mapChar();
        DataHelper.setLoading(false);
        DataHelper.setUnsavedData(false);
        updateTitle();
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

        charSheet.gettKonstitutionFin().setText("" + character.getKonstitution());
        charSheet.gettGeschicklichkeitFin().setText("" + character.getGeschicklichkeit());
        charSheet.gettReaktionFin().setText("" + character.getReaktion());
        charSheet.gettStaerkeFin().setText("" + character.getStaerke());

        charSheet.gettWillenskraftFin().setText("" + character.getWillenskraft());
        charSheet.gettLogikFin().setText("" + character.getLogik());
        charSheet.gettIntuitionFin().setText("" + character.getIntuition());
        charSheet.gettCharismaFin().setText("" + character.getCharisma());

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
        charSheet.gettCredit().setText("" + character.getCred());
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

        mapKarma();
//
//        synchronized (this) {
//            try {
//                wait(150L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        charSheet.gettKonstitution().setText("" + character.getKonstitution());
        System.out.print("kon:" + character.getKonstitution());
        charSheet.gettGeschicklichkeit().setText("" + character.getGeschicklichkeit());
        System.out.print("ges" + character.getGeschicklichkeit());
        charSheet.gettReaktion().setText("" + character.getReaktion());
        System.out.print("rea" + character.getReaktion());
        charSheet.gettStaerke().setText("" + character.getStaerke());
        System.out.print("sta" + character.getStaerke());
//        synchronized (this) {
//            try {
//                wait(150L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        charSheet.gettWillenskraft().setText("" + character.getWillenskraft());
        System.out.print("wil" + character.getWillenskraft());
        charSheet.gettLogik().setText("" + character.getLogik());
        System.out.print("log" + character.getLogik());
        charSheet.gettIntuition().setText("" + character.getIntuition());
        System.out.print("int" + character.getIntuition());
        charSheet.gettCharisma().setText("" + character.getCharisma());
        System.out.println("cha" + character.getCharisma());

        charSheet.getlMagieResonanz().setSelectedItem(character.getMagRes());
        magResPanel.setMagRes(character.getMagRes());
        updateMagResTextField();
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

    private void updateMagResTextField() {
        if (character.getMagRes() == MagRes.NONE) {
            charSheet.gettMagieResonanz().setEnabled(false);
            charSheet.gettMagieResonanz().setText("");
        } else {
            charSheet.gettMagieResonanz().setEnabled(true);
            charSheet.gettMagieResonanz().setText("" + character.getMagieResonanz());
        }
    }

    private void mapVorteilNachteil() {
        java.util.List<String> list = character.getVorteileNachteile();
        if (list != null) {
            for (String id : list) {
                File file = new File(getDirectory().getAbsolutePath() + "/srV/vn/" + id.split("\\?")[0] + ".srVvn");
                if (file.exists()) {
                    try {
                        VorteilNachteil item = JsonHandler.readFile(file, VorteilNachteil.class);
                        item.setZusatzInfo((id.split("\\?").length > 1) ? id.split("\\?")[1] : "");
                        vorteilNachteilPanel.getList().add(item);
                    } catch (IOException ignore) {
                    }
                }
            }
        }
    }


    private void updateEdgeAusgegeben() {
        edgeCheckBox = charSheet.getEdgeCheckBox();
        for (int i = 0; i < edgeCheckBox.size(); i++) {
            if (character != null && character.getEdge() < i + 1) {
                edgeCheckBox.get(i).setEnabled(false);
            } else {
                edgeCheckBox.get(i).setEnabled(true);
            }
            if (character != null && character.getMetatyp() != null && character.getMetatyp().getEdg()[1] < i + 1) {
                edgeCheckBox.get(i).setBorderPaintedFlat(true);
                edgeCheckBox.get(i).setVisible(false);
            } else {
                edgeCheckBox.get(i).setBorderPaintedFlat(false);
                edgeCheckBox.get(i).setVisible(true);
            }
            if (character != null && character.getEdgeAusgegeben() >= i + 1) {
                edgeCheckBox.get(i).setSelected(true);
            } else {
                edgeCheckBox.get(i).setSelected(false);
            }
        }
    }

    private void updateUeberzaehligPanel() {
        ueberzaehlig = zustandsmonitor.getUeberzaehlig();
        for (int i = 0; i < ueberzaehlig.size(); i++) {
            if (character != null && character.getKonstitution() < i + 1) {
                ueberzaehlig.get(i).setEnabled(false);
                ueberzaehlig.get(i).setBorderPaintedFlat(true);
                ueberzaehlig.get(i).setVisible(false);
            } else {
                ueberzaehlig.get(i).setEnabled(true);
                ueberzaehlig.get(i).setBorderPaintedFlat(false);
                ueberzaehlig.get(i).setVisible(true);
            }
            if (character != null && character.getSchadenUeberzaehlig() >= i + 1) {
                ueberzaehlig.get(i).setSelected(true);
            } else {
                ueberzaehlig.get(i).setSelected(false);
            }
        }
    }

    private void updateKoerperPanel() {
        koerperlich = zustandsmonitor.getKoerperlich();
        for (int i = 0; i < koerperlich.size(); i++) {
            if (character != null && ((int) ceil((8 + ((double) character.getKonstitution() / 2)))) < i + 1) {
                koerperlich.get(i).setEnabled(false);
                koerperlich.get(i).setBorderPaintedFlat(true);
                koerperlich.get(i).setVisible(false);
            } else {
                koerperlich.get(i).setEnabled(true);
                koerperlich.get(i).setBorderPaintedFlat(false);
                koerperlich.get(i).setVisible(true);
            }
            if (character != null && character.getSchadenKoerper() >= i + 1) {
                koerperlich.get(i).setSelected(true);
            } else {
                koerperlich.get(i).setSelected(false);
            }
        }
    }

    private void updateGeistPanel() {
        geistig = zustandsmonitor.getGeistig();
        for (int i = 0; i < geistig.size(); i++) {
            if (character != null && ((int) ceil((8 + ((double) character.getWillenskraft() / 2)))) < i + 1) {
                geistig.get(i).setEnabled(false);
                geistig.get(i).setBorderPaintedFlat(true);
                geistig.get(i).setVisible(false);
            } else {
                geistig.get(i).setEnabled(true);
                geistig.get(i).setBorderPaintedFlat(false);
                geistig.get(i).setVisible(true);
            }
            if (character != null && character.getSchadenKoerper() >= i + 1) {
                geistig.get(i).setSelected(true);
            } else {
                geistig.get(i).setSelected(false);
            }
        }
    }

    private void mapKarma() {
        charSheet.getlKarma().setText(Language.getString("charSheet.persDaten.karma") + character.getKarma() + "\\" + character.getGesamtkarma());
        charSheet.getPvKarma().setMaximum(character.getGesamtkarma());
        charSheet.getPvKarma().setValue(character.getKarma());
    }

    private void textFieldChanged(JCustomTextField textField) {
        String text = textField.getText();
        if (text.matches(textField.getRegexFilter()) && !DataHelper.isLoading()) {
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
            if (textField == charSheet.gettEdge()) {
                character.setEdge(Integer.valueOf(text));
                updateEdgeAusgegeben();
            }
            mapCalc();
            DataHelper.setUnsavedData(true);
            updateTitle();
        }

    }

    private void comboBoxChanged(JComboBox comboBox) {
        if (comboBox == charSheet.gettMetatyp()) {
            updateMetatyp(comboBox);
        }
        if (comboBox == charSheet.gettGeschlecht()) character.setGeschlecht((Geschlecht) comboBox.getSelectedItem());
        if (comboBox == charSheet.getlMagieResonanz()) {
            character.setMagRes((MagRes) comboBox.getSelectedItem());
            magResPanel.setMagRes((MagRes) comboBox.getSelectedItem());
            updateMagResTextField();
        }
        DataHelper.setUnsavedData(true);
        updateTitle();
    }

    private void updateMetatyp(JComboBox comboBox) {
        character.setMetatyp((Metatyp) comboBox.getSelectedItem());
        updateEdgeAusgegeben();
        charSheet.getMetaMerkmalPopup().removeAll();
        for (String merkmal : character.getMetatyp().getMetamerkmale()) {
            if (!merkmal.isEmpty()) {
                JMenuItem item = new JMenuItem(merkmal);
                charSheet.getMetaMerkmalPopup().add(item);
            }
        }

        charSheet.gettKonstitutionMax().setText("/" + character.getMetatyp().getKon()[1]);
        charSheet.gettGeschicklichkeitMax().setText("/" + character.getMetatyp().getGes()[1]);
        charSheet.gettReaktionMax().setText("/" + character.getMetatyp().getRea()[1]);
        charSheet.gettStaerkeMax().setText("/" + character.getMetatyp().getStr()[1]);

        charSheet.gettWillenskraftMax().setText("/" + character.getMetatyp().getWil()[1]);
        charSheet.gettLogikMax().setText("/" + character.getMetatyp().getLog()[1]);
        charSheet.gettIntuitionMax().setText("/" + character.getMetatyp().getIn()[1]);
        charSheet.gettCharismaMax().setText("/" + character.getMetatyp().getCha()[1]);

        int sprintMod = (character.getMetatyp() == Metatyp.ZWERG || character.getMetatyp() == Metatyp.TROLL) ? 1 : 2;
        charSheet.getlSprinten().setText(Language.getString("charSheet.attribute.neben.spr") + "(+" + sprintMod + "m)");
    }

    private int checkBoxChanged(List<JCheckBox> arrayList, JCheckBox checkBox) {
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
        DataHelper.setUnsavedData(true);
    }

    private void schadenGeistChanged(JCheckBox checkBox) {
        int clickedIndex = checkBoxChanged(geistig, checkBox);
        character.setSchadenGeist((checkBox.isSelected()) ? clickedIndex + 1 : clickedIndex);
        updateVerletzungsMod();
        DataHelper.setUnsavedData(true);
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
        DataHelper.setUnsavedData(true);
    }

    public CharSheet getCharSheet() {
        return charSheet;
    }

}
