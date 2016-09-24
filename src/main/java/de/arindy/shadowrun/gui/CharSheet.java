package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.controller.helper.DataHelper;
import de.arindy.shadowrun.entities.types.Geschlecht;
import de.arindy.shadowrun.entities.types.MagRes;
import de.arindy.shadowrun.entities.types.Metatyp;
import de.arindy.shadowrun.gui.helper.JCustomTextField;
import de.arindy.shadowrun.gui.helper.Language;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CharSheet {

    //<editor-fold desc="Variablen">
    private String frameTitle;
    private int initMod;

    private JFrame sheet;

    private JPanel right;
    private JTabbedPane tabbed = new JTabbedPane();

    private JMenuBar menu = new JMenuBar();

    private JLabel lName;

    private JLabel lKarma;

    private JLabel lInitiativeW;
    private JLabel lAstralInitiativeW;
    private JLabel lMatrixInitiativeARW;
    private JLabel lMatrixInitiativekaltW;
    private JLabel lMatrixInitiativeheißW;
    private JLabel lRiggingInitiativeW;
    private JLabel lRiggingInitiativekaltW;
    private JLabel lRiggingInitiativeheißW;
    private JLabel lRiggingInitiativedirektW;

    private JLabel lSprinten;

    private JCustomTextField tName;
    private JComboBox<Metatyp> tMetatyp;
    private JComboBox<Geschlecht> tGeschlecht;
    private JComboBox<MagRes> lMagieResonanz;
    private JCustomTextField tAlter;
    private JCustomTextField tGroesse;
    private JCustomTextField tGewicht;
    private JCustomTextField tEthnie;
    private JCustomTextField tKonzept;
    private JCustomTextField tStrassenruf;
    private JCustomTextField tSchlechterRuf;
    private JCustomTextField tProminenz;

    private JCustomTextField tKonstitution;
    private JCustomTextField tGeschicklichkeit;
    private JCustomTextField tReaktion;
    private JCustomTextField tStaerke;

    private JCustomTextField tKonstitutionFin;
    private JCustomTextField tGeschicklichkeitFin;
    private JCustomTextField tReaktionFin;
    private JCustomTextField tStaerkeFin;

    private JCustomTextField tWillenskraft;
    private JCustomTextField tLogik;
    private JCustomTextField tIntuition;
    private JCustomTextField tCharisma;

    private JCustomTextField tWillenskraftFin;
    private JCustomTextField tLogikFin;
    private JCustomTextField tIntuitionFin;
    private JCustomTextField tCharismaFin;

    private JCustomTextField tMagieResonanz;

    private JCustomTextField tInitiative;
    private JCustomTextField tAstralInitiative;
    private JCustomTextField tMatrixInitiativeAR;
    private JCustomTextField tMatrixInitiativekalt;
    private JCustomTextField tMatrixInitiativeheiß;
    private JCustomTextField tRiggingInitiative;
    private JCustomTextField tRiggingInitiativekalt;
    private JCustomTextField tRiggingInitiativeheiß;
    private JCustomTextField tRiggingInitiativedirekt;

    private JCustomTextField tSelbstbeherrschung;
    private JCustomTextField tMenschenkenntnis;
    private JCustomTextField tErinnerungsvermoegen;
    private JCustomTextField tHebenTragen;
    private JCustomTextField tGehen;
    private JCustomTextField tLaufen;
    private JCustomTextField tSprinten;

    private JCustomTextField tEssenz;
    private JCustomTextField tEdge;

    private JCustomTextField tLimitKoerper;
    private JCustomTextField tLimitGeist;
    private JCustomTextField tLimitSozial;

    private JProgressBar pvKarma;
    private JPanel mainPanel;
    private JCustomTextField tCredit;

    private JMenu menuDatei;
    private JMenu menuCharakter;
    private JMenu menuVerwaltung;
    private JMenu menuSprache;
    private JMenu menuSchriftgroesse;

    private JPanel edgeAusgegeben;
    private Zustandsmonitor zustandsmonitor;
    private JTabbedPane tabbedPane1;

    private ActionListener edgeListener;
    private List<JCheckBox> edgeCheckBox;
    //</editor-fold>

    public CharSheet(String frameTitle) {
        this.frameTitle = frameTitle;
        init();
    }

    private void init() {
        setLimits();
        pvKarma.setMinimum(0);
        sheet = new JFrame(frameTitle);
        sheet.setContentPane(mainPanel);
        sheet.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        sheet.setJMenuBar(menu);
        setInitMod(0);
        buildMenu();
        setComboBoxModels();

        centerText();
        sheet.pack();
        sheet.setResizable(false);
        if (DataHelper.getLocation() != null) {
            sheet.setLocation(DataHelper.getLocation());
        } else {
            sheet.setLocationRelativeTo(null);
        }
        DataHelper.setLocation(null);
        sheet.setVisible(true);
    }

    private void buildMenu() {
        menuDatei =  new JMenu(Language.getString("menu.datei"));
        menu.add(menuDatei);

        menuCharakter = new JMenu(Language.getString("menu.charakter"));
        menu.add(menuCharakter);

        menuVerwaltung = new JMenu(Language.getString("menu.verwaltung"));
        menu.add(menuVerwaltung);

        JMenu menuOptions = new JMenu(Language.getString("menu.options"));
        menu.add(menuOptions);

        menuSprache = new JMenu(Language.getString("menu.options.sprache"));
        menuOptions.add(menuSprache);

        menuSchriftgroesse = new JMenu(Language.getString("menu.options.fontsize"));
        menuOptions.add(menuSchriftgroesse);
    }

    private void setLimits() {
        tName.setMaximumLength(40);
        String wAndUmlauts = "\\w\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df";
        tName.setRegexFilter("(([" + wAndUmlauts + "]*( )*)*(\\(([" + wAndUmlauts + "]*( )*)*\\))?)");

        tAlter.setMaximumLength(3);
        tAlter.setRegexFilter("\\d*");
        tGroesse.setMaximumLength(3);
        tGroesse.setRegexFilter("\\d*");
        tGewicht.setMaximumLength(3);
        tGewicht.setRegexFilter("\\d*");
        tEthnie.setMaximumLength(12);
        tKonzept.setMaximumLength(30);
        tStrassenruf.setMaximumLength(2);
        tStrassenruf.setRegexFilter("\\d*");
        tSchlechterRuf.setMaximumLength(2);
        tSchlechterRuf.setRegexFilter("\\d*");
        tProminenz.setMaximumLength(2);
        tProminenz.setRegexFilter("\\d*");
        tCredit.setMaximumLength(10);
        tCredit.setRegexFilter("\\d*");
        tCredit.setForeground(java.awt.Color.ORANGE);

        tKonstitution.setMaximumLength(2);
        tKonstitution.setRegexFilter("\\d*");
        tGeschicklichkeit.setMaximumLength(2);
        tGeschicklichkeit.setRegexFilter("\\d*");
        tReaktion.setMaximumLength(2);
        tReaktion.setRegexFilter("\\d*");
        tStaerke.setMaximumLength(2);
        tStaerke.setRegexFilter("\\d*");
        tWillenskraft.setMaximumLength(2);
        tWillenskraft.setRegexFilter("\\d*");
        tLogik.setMaximumLength(2);
        tLogik.setRegexFilter("\\d*");
        tIntuition.setMaximumLength(2);
        tIntuition.setRegexFilter("\\d*");
        tCharisma.setMaximumLength(2);
        tCharisma.setRegexFilter("\\d*");

        tKonstitutionFin.setMaximumLength(2);
        tKonstitutionFin.setRegexFilter("\\d*");
        tGeschicklichkeitFin.setMaximumLength(2);
        tGeschicklichkeitFin.setRegexFilter("\\d*");
        tReaktionFin.setMaximumLength(2);
        tReaktionFin.setRegexFilter("\\d*");
        tStaerkeFin.setMaximumLength(2);
        tStaerkeFin.setRegexFilter("\\d*");
        tWillenskraftFin.setMaximumLength(2);
        tWillenskraftFin.setRegexFilter("\\d*");
        tLogikFin.setMaximumLength(2);
        tLogikFin.setRegexFilter("\\d*");
        tIntuitionFin.setMaximumLength(2);
        tIntuitionFin.setRegexFilter("\\d*");
        tCharismaFin.setMaximumLength(2);
        tCharismaFin.setRegexFilter("\\d*");

        tInitiative.setMaximumLength(2);
        tInitiative.setRegexFilter("\\d*");
        tInitiative.setEditable(false);
        tAstralInitiative.setMaximumLength(2);
        tAstralInitiative.setRegexFilter("\\d*");
        tAstralInitiative.setEditable(false);
        tMatrixInitiativeAR.setMaximumLength(2);
        tMatrixInitiativeAR.setRegexFilter("\\d*");
        tMatrixInitiativeAR.setEditable(false);
        tMatrixInitiativekalt.setMaximumLength(2);
        tMatrixInitiativekalt.setRegexFilter("\\d*");
        tMatrixInitiativekalt.setEditable(false);
        tMatrixInitiativeheiß.setMaximumLength(2);
        tMatrixInitiativeheiß.setRegexFilter("\\d*");
        tMatrixInitiativeheiß.setEditable(false);
        tRiggingInitiative.setMaximumLength(2);
        tRiggingInitiative.setRegexFilter("\\d*");
        tRiggingInitiative.setEditable(false);
        tRiggingInitiativekalt.setMaximumLength(2);
        tRiggingInitiativekalt.setRegexFilter("\\d*");
        tRiggingInitiativekalt.setEditable(false);
        tRiggingInitiativeheiß.setMaximumLength(2);
        tRiggingInitiativeheiß.setRegexFilter("\\d*");
        tRiggingInitiativeheiß.setEditable(false);
        tRiggingInitiativedirekt.setMaximumLength(2);
        tRiggingInitiativedirekt.setRegexFilter("\\d*");
        tRiggingInitiativedirekt.setEditable(false);

        tSelbstbeherrschung.setMaximumLength(2);
        tSelbstbeherrschung.setRegexFilter("\\d*");
        tSelbstbeherrschung.setEditable(false);
        tMenschenkenntnis.setMaximumLength(2);
        tMenschenkenntnis.setRegexFilter("\\d*");
        tMenschenkenntnis.setEditable(false);
        tErinnerungsvermoegen.setMaximumLength(2);
        tErinnerungsvermoegen.setRegexFilter("\\d*");
        tErinnerungsvermoegen.setEditable(false);
        tHebenTragen.setMaximumLength(2);
        tHebenTragen.setEditable(false);

        tGehen.setMaximumLength(2);
        tGehen.setRegexFilter("\\d*");
        tGehen.setEditable(false);
        tLaufen.setMaximumLength(2);
        tLaufen.setRegexFilter("\\d*");
        tLaufen.setEditable(false);
        tSprinten.setMaximumLength(2);
        tSprinten.setRegexFilter("\\d*");
        tSprinten.setEditable(false);

        tEssenz.setMaximumLength(2);
        tEssenz.setRegexFilter("\\d*");
        tEdge.setMaximumLength(2);
        tEdge.setRegexFilter("\\d*");

        tMagieResonanz.setMaximumLength(2);
        tMagieResonanz.setRegexFilter("\\d*");

        tLimitKoerper.setMaximumLength(2);
        tLimitKoerper.setRegexFilter("\\d*");
        tLimitKoerper.setEditable(false);
        tLimitGeist.setMaximumLength(2);
        tLimitGeist.setEditable(false);
        tLimitSozial.setMaximumLength(2);
        tLimitSozial.setEditable(false);
    }

    private void setComboBoxModels(){
        tMetatyp.setModel(new DefaultComboBoxModel<>(Metatyp.values()));
        tGeschlecht.setModel(new DefaultComboBoxModel<>(Geschlecht.values()));
        lMagieResonanz.setModel(new DefaultComboBoxModel<>(MagRes.values()));
        removeComboBoxBorder(tMetatyp);
        removeComboBoxBorder(tGeschlecht);
        removeComboBoxBorder(lMagieResonanz);
    }

    private void removeComboBoxBorder(JComboBox combo) {
        for (int i = 0; i < combo.getComponentCount(); i++) {
            if (combo.getComponent(i) instanceof JComponent) {
                ((JComponent) combo.getComponent(i)).setBorder(new EmptyBorder(0, 0, 0, 0));
            }
        }
    }
    private void centerText() {
        tName.setHorizontalAlignment(JTextField.CENTER);
        tCredit.setHorizontalAlignment(JTextField.RIGHT);
        tAlter.setHorizontalAlignment(JTextField.CENTER);
        tGroesse.setHorizontalAlignment(JTextField.CENTER);
        tGewicht.setHorizontalAlignment(JTextField.CENTER);
        tStrassenruf.setHorizontalAlignment(JTextField.CENTER);
        tSchlechterRuf.setHorizontalAlignment(JTextField.CENTER);
        tProminenz.setHorizontalAlignment(JTextField.CENTER);

        tKonstitution.setHorizontalAlignment(JTextField.CENTER);
        tGeschicklichkeit.setHorizontalAlignment(JTextField.CENTER);
        tReaktion.setHorizontalAlignment(JTextField.CENTER);
        tStaerke.setHorizontalAlignment(JTextField.CENTER);

        tWillenskraft.setHorizontalAlignment(JTextField.CENTER);
        tLogik.setHorizontalAlignment(JTextField.CENTER);
        tIntuition.setHorizontalAlignment(JTextField.CENTER);
        tCharisma.setHorizontalAlignment(JTextField.CENTER);

        tKonstitutionFin.setHorizontalAlignment(JTextField.CENTER);
        tGeschicklichkeitFin.setHorizontalAlignment(JTextField.CENTER);
        tReaktionFin.setHorizontalAlignment(JTextField.CENTER);
        tStaerkeFin.setHorizontalAlignment(JTextField.CENTER);

        tWillenskraftFin.setHorizontalAlignment(JTextField.CENTER);
        tLogikFin.setHorizontalAlignment(JTextField.CENTER);
        tIntuitionFin.setHorizontalAlignment(JTextField.CENTER);
        tCharismaFin.setHorizontalAlignment(JTextField.CENTER);

        tSelbstbeherrschung.setHorizontalAlignment(JTextField.CENTER);
        tMenschenkenntnis.setHorizontalAlignment(JTextField.CENTER);
        tErinnerungsvermoegen.setHorizontalAlignment(JTextField.CENTER);
        tHebenTragen.setHorizontalAlignment(JTextField.CENTER);

        tGehen.setHorizontalAlignment(JTextField.CENTER);
        tLaufen.setHorizontalAlignment(JTextField.CENTER);
        tSprinten.setHorizontalAlignment(JTextField.CENTER);

        tMagieResonanz.setHorizontalAlignment(JTextField.CENTER);

        tInitiative.setHorizontalAlignment(JTextField.CENTER);
        tAstralInitiative.setHorizontalAlignment(JTextField.CENTER);
        tMatrixInitiativeAR.setHorizontalAlignment(JTextField.CENTER);
        tMatrixInitiativeheiß.setHorizontalAlignment(JTextField.CENTER);
        tMatrixInitiativekalt.setHorizontalAlignment(JTextField.CENTER);
        tRiggingInitiative.setHorizontalAlignment(JTextField.CENTER);
        tRiggingInitiativeheiß.setHorizontalAlignment(JTextField.CENTER);
        tRiggingInitiativekalt.setHorizontalAlignment(JTextField.CENTER);
        tRiggingInitiativedirekt.setHorizontalAlignment(JTextField.CENTER);

        tEssenz.setHorizontalAlignment(JTextField.CENTER);
        tEdge.setHorizontalAlignment(JTextField.CENTER);

        tLimitKoerper.setHorizontalAlignment(JTextField.CENTER);
        tLimitGeist.setHorizontalAlignment(JTextField.CENTER);
        tLimitSozial.setHorizontalAlignment(JTextField.CENTER);
    }

    public void setEnabledInputsPersoenlich(boolean bool) {
        tName.setEditable(bool);
        tAlter.setEditable(bool);
        tGroesse.setEditable(bool);
        tGewicht.setEditable(bool);
        tEthnie.setEditable(bool);
        tKonzept.setEditable(bool);
        setEnabledComboBox(tMetatyp, bool);
        setEnabledComboBox(tGeschlecht, bool);
    }

    @SuppressWarnings("rawtypes")
    private void setEnabledComboBox(JComboBox cb, boolean bool) {
        cb.setEditable(!bool);
        ((JTextField) cb.getEditor().getEditorComponent()).setDisabledTextColor(UIManager.getColor("ComboBox.foreground"));
        cb.setEnabled(bool);
    }

    private void setInitMod(int initMod) {
        this.initMod = initMod;
        lInitiativeW.setText((1 + initMod) + Language.getString("dice") + " +");
        lAstralInitiativeW.setText((2 + initMod) + Language.getString("dice") + " +");
        lMatrixInitiativeARW.setText((1 + initMod) + Language.getString("dice") + " +");
        lMatrixInitiativekaltW.setText((3 + initMod) + Language.getString("dice") + " +");
        lMatrixInitiativeheißW.setText((4 + initMod) + Language.getString("dice") + " +");
        lRiggingInitiativeW.setText((1 + initMod) + Language.getString("dice") + " +");
        lRiggingInitiativekaltW.setText((1 + initMod) + Language.getString("dice") + " +");
        lRiggingInitiativeheißW.setText((1 + initMod) + Language.getString("dice") + " +");
        lRiggingInitiativedirektW.setText((1 + initMod) + Language.getString("dice") + " +");
    }

    public JFrame getSheet() {
        return sheet;
    }

    public JTabbedPane getTabbed() {
        return tabbed;
    }

    public JPanel getEdgeAusgegeben() {
        return edgeAusgegeben;
    }

    public JLabel getlName() {
        return lName;
    }

    public JLabel getlKarma() {
        return lKarma;
    }

    public JComboBox<MagRes> getlMagieResonanz() {
        return lMagieResonanz;
    }

    public JCustomTextField gettName() {
        return tName;
    }

    public JCustomTextField gettCredit() {
        return tCredit;
    }

    public JComboBox<Metatyp> gettMetatyp() {
        return tMetatyp;
    }

    public JComboBox<Geschlecht> gettGeschlecht() {
        return tGeschlecht;
    }

    public JCustomTextField gettAlter() {
        return tAlter;
    }

    public JCustomTextField gettGroesse() {
        return tGroesse;
    }

    public JCustomTextField gettGewicht() {
        return tGewicht;
    }

    public JCustomTextField gettEthnie() {
        return tEthnie;
    }

    public JCustomTextField gettKonzept() {
        return tKonzept;
    }

    public JCustomTextField gettStrassenruf() {
        return tStrassenruf;
    }

    public JCustomTextField gettSchlechterRuf() {
        return tSchlechterRuf;
    }

    public JCustomTextField gettProminenz() {
        return tProminenz;
    }

    public JCustomTextField gettKonstitution() {
        return tKonstitution;
    }

    public JCustomTextField gettGeschicklichkeit() {
        return tGeschicklichkeit;
    }

    public JCustomTextField gettReaktion() {
        return tReaktion;
    }

    public JCustomTextField gettStaerke() {
        return tStaerke;
    }

    public JCustomTextField gettWillenskraft() {
        return tWillenskraft;
    }

    public JCustomTextField gettLogik() {
        return tLogik;
    }

    public JCustomTextField gettIntuition() {
        return tIntuition;
    }

    public JCustomTextField gettCharisma() {
        return tCharisma;
    }

    public JCustomTextField gettKonstitutionFin() {
        return tKonstitutionFin;
    }

    public JCustomTextField gettGeschicklichkeitFin() {
        return tGeschicklichkeitFin;
    }

    public JCustomTextField gettReaktionFin() {
        return tReaktionFin;
    }

    public JCustomTextField gettStaerkeFin() {
        return tStaerkeFin;
    }

    public JCustomTextField gettWillenskraftFin() {
        return tWillenskraftFin;
    }

    public JCustomTextField gettLogikFin() {
        return tLogikFin;
    }

    public JCustomTextField gettIntuitionFin() {
        return tIntuitionFin;
    }

    public JCustomTextField gettCharismaFin() {
        return tCharismaFin;
    }

    public JCustomTextField gettMagieResonanz() {
        return tMagieResonanz;
    }

    public JCustomTextField gettInitiative() {
        return tInitiative;
    }

    public JCustomTextField gettAstralInitiative() {
        return tAstralInitiative;
    }

    public JCustomTextField gettMatrixInitiativeAR() {
        return tMatrixInitiativeAR;
    }

    public JCustomTextField gettMatrixInitiativekalt() {
        return tMatrixInitiativekalt;
    }

    public JCustomTextField gettMatrixInitiativeheiß() {
        return tMatrixInitiativeheiß;
    }

    public JCustomTextField gettRiggingInitiative() {
        return tRiggingInitiative;
    }

    public JCustomTextField gettRiggingInitiativekalt() {
        return tRiggingInitiativekalt;
    }

    public JCustomTextField gettRiggingInitiativeheiß() {
        return tRiggingInitiativeheiß;
    }

    public JCustomTextField gettRiggingInitiativedirekt() {
        return tRiggingInitiativedirekt;
    }

    public JCustomTextField gettSelbstbeherrschung() {
        return tSelbstbeherrschung;
    }

    public JCustomTextField gettMenschenkenntnis() {
        return tMenschenkenntnis;
    }

    public JCustomTextField gettErinnerungsvermoegen() {
        return tErinnerungsvermoegen;
    }

    public JCustomTextField gettHebenTragen() {
        return tHebenTragen;
    }

    public JCustomTextField gettGehen() {
        return tGehen;
    }

    public JCustomTextField gettLaufen() {
        return tLaufen;
    }

    public JCustomTextField gettSprinten() {
        return tSprinten;
    }

    public JLabel getlSprinten() {
        return lSprinten;
    }

    public JCustomTextField gettEssenz() {
        return tEssenz;
    }

    public JCustomTextField gettEdge() {
        return tEdge;
    }

    public JCustomTextField gettLimitKoerper() {
        return tLimitKoerper;
    }

    public JCustomTextField gettLimitGeist() {
        return tLimitGeist;
    }

    public JCustomTextField gettLimitSozial() {
        return tLimitSozial;
    }

    public JProgressBar getPvKarma() {
        return pvKarma;
    }

    public JMenu getMenuVerwaltung() {
        return menuVerwaltung;
    }

    public JMenu getMenuDatei() {
        return menuDatei;
    }

    public JMenu getMenuCharakter() {
        return menuCharakter;
    }

    public JMenu getMenuSprache() {
        return menuSprache;
    }

    public JMenu getMenuSchriftgroesse() {
        return menuSchriftgroesse;
    }

    public List<JCheckBox> getEdgeCheckBox() {
        return edgeCheckBox;
    }

    public Zustandsmonitor getZustandsmonitor() {
        return zustandsmonitor;
    }

    private void createUIComponents() {
        edgeCheckBox = new ArrayList<>();
        edgeAusgegeben = new JPanel();
        for (int i = 0; i < 8; i++) {
            edgeCheckBox.add(new JCheckBox());
            edgeAusgegeben.add(edgeCheckBox.get(i));
        }
    }
}
