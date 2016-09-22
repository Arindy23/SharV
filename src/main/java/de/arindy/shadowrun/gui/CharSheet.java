package de.arindy.shadowrun.gui;

import de.arindy.shadowrun.controller.CharController;
import de.arindy.shadowrun.entities.types.Geschlecht;
import de.arindy.shadowrun.entities.types.MagRes;
import de.arindy.shadowrun.entities.types.Metatyp;
import de.arindy.shadowrun.gui.helper.JCustomTextField;
import de.arindy.shadowrun.gui.types.GBC;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CharSheet {

    //<editor-fold desc="Variablen">
    private String frameTitle;
    private int initMod;

    private JFrame sheet;

    private JPanel main = new JPanel();
    private JPanel right = new JPanel();
    private JTabbedPane tabbed = new JTabbedPane();

    private JMenuBar menu = new JMenuBar();

    private JPanel persoenliches = new JPanel();
    private JPanel persoenlichesTop = new JPanel();
    private JPanel persoenlichesMiddleTop = new JPanel();
    private JPanel persoenlichesMiddleBottom = new JPanel();
    private JPanel persoenlichesBottom = new JPanel();

    private JPanel attribute = new JPanel();
    private JPanel attributeLeft = new JPanel();
    private JPanel attributeRight = new JPanel();

    private JPanel attributeKoerper = new JPanel();
    private JPanel attributeGeist = new JPanel();
    private JPanel attributeInitiative = new JPanel();
    private JPanel attributeMagieResonanz = new JPanel();
    private JPanel attributeNebenAttribute = new JPanel();
    private JPanel attributeEssenzEdge = new JPanel();

    private JPanel edgeAusgegeben = new JPanel();
    private JPanel bewegung = new JPanel();
    private JPanel attributeLimits = new JPanel();

    private JLabel lName = new JLabel("Name (Straßenname):");
    private JLabel lMetatyp = new JLabel("Metatyp:");
    private JLabel lGeschlecht = new JLabel("Geschlecht:");
    private JLabel lAlter = new JLabel("Alter:");
    private JLabel lGroesse = new JLabel("Größe:");
    private JLabel lGewicht = new JLabel("Gewicht:");
    private JLabel lEthnie = new JLabel("Ethnie:");
    private JLabel lKonzept = new JLabel("Konzept:");
    private JLabel lStrassenruf = new JLabel("Straßenruf:");
    private JLabel lSchlechterRuf = new JLabel("Schlechter Ruf:");
    private JLabel lProminenz = new JLabel("Prominenz:");
    private JLabel lSonstiges = new JLabel("Sonstiges:");
    private JLabel lKarma = new JLabel("Karma: 0\\0");

    private JLabel lKonstitution = new JLabel("Konstitution:");
    private JLabel lGeschicklichkeit = new JLabel("Geschicklichkeit:");
    private JLabel lReaktion = new JLabel("Reaktion:");
    private JLabel lStaerke = new JLabel("Stärke:");

    private JLabel lWillenskraft = new JLabel("Willenskraft:");
    private JLabel lLogik = new JLabel("Logik:");
    private JLabel lIntuition = new JLabel("Intuition:");
    private JLabel lCharisma = new JLabel("Charisma:");

    private JLabel lInitiative = new JLabel("Initiative:");
    private JLabel lAstralInitiative = new JLabel("Astral-Initiative:");
    private JLabel lMatrixInitiativeAR = new JLabel("Matrix-Init.(AR):");
    private JLabel lMatrixInitiativekalt = new JLabel("Matrix-Init.(kalt):");
    private JLabel lMatrixInitiativeheiß = new JLabel("Matrix-Init.(heiß):");
    private JLabel lRiggingInitiative = new JLabel("Rigging-Init.(AR)::");
    private JLabel lRiggingInitiativekalt = new JLabel("Rigging-Init.(kalt):");
    private JLabel lRiggingInitiativeheiß = new JLabel("Rigging-Init.(heiß):");
    private JLabel lRiggingInitiativedirekt = new JLabel("Rigging-Init.(direkt):");

    private JLabel lInitiativeW = new JLabel((1 + initMod) + "W6+");
    private JLabel lAstralInitiativeW = new JLabel((2 + initMod) + "W6+");
    private JLabel lMatrixInitiativeARW = new JLabel((1 + initMod) + "W6+");
    private JLabel lMatrixInitiativekaltW = new JLabel((3 + initMod) + "W6+");
    private JLabel lMatrixInitiativeheißW = new JLabel((4 + initMod) + "W6+");
    private JLabel lRiggingInitiativeW = new JLabel((1 + initMod) + "W6+");
    private JLabel lRiggingInitiativekaltW = new JLabel((1 + initMod) + "W6+");
    private JLabel lRiggingInitiativeheißW = new JLabel((1 + initMod) + "W6+");
    private JLabel lRiggingInitiativedirektW = new JLabel((1 + initMod) + "W6+");

    private JLabel lSelbstbeherrschung = new JLabel("Selbstbeherrschung:");
    private JLabel lMenschenkenntnis = new JLabel("Menschenkenntnis:");
    private JLabel lErinnerungsvermoegen = new JLabel("Erinnerungsvermögen:");
    private JLabel lHebenTragen = new JLabel("Heben/Tragen:");

    private JLabel lGehen = new JLabel("Geh:");
    private JLabel lLaufen = new JLabel("Lauf:");
    private JLabel lSprinten = new JLabel("Sprint(+1m):");

    private JLabel lEssenz = new JLabel("Essenz:");
    private JLabel lEdge = new JLabel("Edge:");

    private JLabel lLimitKoerper = new JLabel("Körper:");
    private JLabel lLimitGeist = new JLabel("Geist:");
    private JLabel lLimitSozial = new JLabel("Sozial:");

    private JCustomTextField tName = new JCustomTextField();
    private JComboBox<Metatyp> tMetatyp = new JComboBox<>(new DefaultComboBoxModel<>(Metatyp.values()));
    private JComboBox<Geschlecht> tGeschlecht = new JComboBox<>(new DefaultComboBoxModel<>(Geschlecht.values()));
    private JComboBox<MagRes> lMagieResonanz = new JComboBox<>(new DefaultComboBoxModel<>(MagRes.values()));
    private JCustomTextField tAlter = new JCustomTextField();
    private JCustomTextField tGroesse = new JCustomTextField();
    private JCustomTextField tGewicht = new JCustomTextField();
    private JCustomTextField tEthnie = new JCustomTextField();
    private JCustomTextField tKonzept = new JCustomTextField();
    private JCustomTextField tStrassenruf = new JCustomTextField();
    private JCustomTextField tSchlechterRuf = new JCustomTextField();
    private JCustomTextField tProminenz = new JCustomTextField();
    private JCustomTextField tSonstiges = new JCustomTextField();

    private JCustomTextField tKonstitution = new JCustomTextField();
    private JCustomTextField tGeschicklichkeit = new JCustomTextField();
    private JCustomTextField tReaktion = new JCustomTextField();
    private JCustomTextField tStaerke = new JCustomTextField();

    private JCustomTextField tWillenskraft = new JCustomTextField();
    private JCustomTextField tLogik = new JCustomTextField();
    private JCustomTextField tIntuition = new JCustomTextField();
    private JCustomTextField tCharisma = new JCustomTextField();

    private JCustomTextField tMagieResonanz = new JCustomTextField();

    private JCustomTextField tInitiative = new JCustomTextField();
    private JCustomTextField tAstralInitiative = new JCustomTextField();
    private JCustomTextField tMatrixInitiativeAR = new JCustomTextField();
    private JCustomTextField tMatrixInitiativekalt = new JCustomTextField();
    private JCustomTextField tMatrixInitiativeheiß = new JCustomTextField();
    private JCustomTextField tRiggingInitiative = new JCustomTextField();
    private JCustomTextField tRiggingInitiativekalt = new JCustomTextField();
    private JCustomTextField tRiggingInitiativeheiß = new JCustomTextField();
    private JCustomTextField tRiggingInitiativedirekt = new JCustomTextField();

    private JCustomTextField tSelbstbeherrschung = new JCustomTextField();
    private JCustomTextField tMenschenkenntnis = new JCustomTextField();
    private JCustomTextField tErinnerungsvermoegen = new JCustomTextField();
    private JCustomTextField tHebenTragen = new JCustomTextField();
    private JCustomTextField tGehen = new JCustomTextField();
    private JCustomTextField tLaufen = new JCustomTextField();
    private JCustomTextField tSprinten = new JCustomTextField();

    private JCustomTextField tEssenz = new JCustomTextField();
    private JCustomTextField tEdge = new JCustomTextField();

    private JCustomTextField tLimitKoerper = new JCustomTextField();
    private JCustomTextField tLimitGeist = new JCustomTextField();
    private JCustomTextField tLimitSozial = new JCustomTextField();

    private JProgressBar pvKarma = new JProgressBar();
    //</editor-fold>

    public CharSheet(String frameTitle) {
        this.frameTitle = frameTitle;
        init();
    }

    private void init() {
        setLimits();
        pvKarma.setMinimum(0);
        sheet = new JFrame(frameTitle);
        sheet.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        sheet.setSize(800, 600);
        sheet.setLocationRelativeTo(null);
        sheet.setResizable(false);
        sheet.setJMenuBar(menu);

        setBorders();

        setLayouts();
        centerText();
        addComponents();

        sheet.repaint();
        sheet.setVisible(true);
    }

    private void setBorders() {
        persoenliches.setBorder(new TitledBorder("Persönliche Daten"));
        attribute.setBorder(new TitledBorder("Attribute"));
        attributeKoerper.setBorder(new TitledBorder("Körper"));
        attributeGeist.setBorder(new TitledBorder("Geist"));
        attributeInitiative.setBorder(new TitledBorder("Initiative"));
        attributeMagieResonanz.setBorder(new TitledBorder("Magie/Resonanz"));
        attributeNebenAttribute.setBorder(new TitledBorder("Nebenattribute"));
        attributeEssenzEdge.setBorder(new TitledBorder("Essenz/Edge"));
        attributeLimits.setBorder(new TitledBorder("Limits"));
    }

    private void setLimits() {
        tName.setMaximumLength(22);
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
        tSonstiges.setMaximumLength(2);
        tSonstiges.setRegexFilter("\\d*");

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

    private void centerText() {
        tAlter.setHorizontalAlignment(JTextField.CENTER);
        tGroesse.setHorizontalAlignment(JTextField.CENTER);
        tGewicht.setHorizontalAlignment(JTextField.CENTER);
        tStrassenruf.setHorizontalAlignment(JTextField.CENTER);
        tSchlechterRuf.setHorizontalAlignment(JTextField.CENTER);
        tProminenz.setHorizontalAlignment(JTextField.CENTER);
        tSonstiges.setHorizontalAlignment(JTextField.CENTER);

        tKonstitution.setHorizontalAlignment(JTextField.CENTER);
        tGeschicklichkeit.setHorizontalAlignment(JTextField.CENTER);
        tReaktion.setHorizontalAlignment(JTextField.CENTER);
        tStaerke.setHorizontalAlignment(JTextField.CENTER);

        tWillenskraft.setHorizontalAlignment(JTextField.CENTER);
        tLogik.setHorizontalAlignment(JTextField.CENTER);
        tIntuition.setHorizontalAlignment(JTextField.CENTER);
        tCharisma.setHorizontalAlignment(JTextField.CENTER);

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

    private void setLayouts() {
        main.setLayout(new GridBagLayout());
        right.setLayout(new GridBagLayout());
        sheet.setLayout(new GridLayout());

        persoenliches.setLayout(new GridBagLayout());
        persoenlichesTop.setLayout(new GridBagLayout());
        persoenlichesMiddleTop.setLayout(new GridBagLayout());
        persoenlichesMiddleBottom.setLayout(new GridBagLayout());
        persoenlichesBottom.setLayout(new GridBagLayout());

        attribute.setLayout(new GridBagLayout());
        attributeLeft.setLayout(new GridBagLayout());
        attributeRight.setLayout(new GridBagLayout());

        attributeKoerper.setLayout(new GridBagLayout());
        attributeGeist.setLayout(new GridBagLayout());
        attributeInitiative.setLayout(new GridBagLayout());
        attributeMagieResonanz.setLayout(new GridBagLayout());
        attributeNebenAttribute.setLayout(new GridBagLayout());
        attributeEssenzEdge.setLayout(new GridBagLayout());
        attributeLimits.setLayout(new GridBagLayout());

        edgeAusgegeben.setLayout(new GridLayout());
        bewegung.setLayout(new GridBagLayout());
    }

    private void addComponents() {

        menu.add(new JMenu(CharController.DATEI));
        menu.add(new JMenu(CharController.CHARAKTER));
        menu.add(new JMenu(CharController.VERWALTUNG));

        persoenlichesTop.add(lName, GBC.clName);
        persoenlichesTop.add(tName, GBC.ctName);
        persoenlichesTop.add(lMetatyp, GBC.clMetatyp);
        persoenlichesTop.add(tMetatyp, GBC.ctMetatyp);

        persoenlichesMiddleTop.add(lGeschlecht, GBC.clGeschlecht);
        persoenlichesMiddleTop.add(tGeschlecht, GBC.ctGeschlecht);
        persoenlichesMiddleTop.add(lAlter, GBC.clAlter);
        persoenlichesMiddleTop.add(tAlter, GBC.ctAlter);
        persoenlichesMiddleTop.add(lGroesse, GBC.clGroesse);
        persoenlichesMiddleTop.add(tGroesse, GBC.ctGroesse);
        persoenlichesMiddleTop.add(lGewicht, GBC.clGewicht);
        persoenlichesMiddleTop.add(tGewicht, GBC.ctGewicht);

        persoenlichesMiddleBottom.add(lEthnie, GBC.clEthnie);
        persoenlichesMiddleBottom.add(tEthnie, GBC.ctEthnie);
        persoenlichesMiddleBottom.add(lKonzept, GBC.clKonzept);
        persoenlichesMiddleBottom.add(tKonzept, GBC.ctKonzept);

        persoenlichesBottom.add(lStrassenruf, GBC.clStrassenruf);
        persoenlichesBottom.add(tStrassenruf, GBC.ctStrassenruf);
        persoenlichesBottom.add(lSchlechterRuf, GBC.clSchlechterRuf);
        persoenlichesBottom.add(tSchlechterRuf, GBC.ctSchlechterRuf);
        persoenlichesBottom.add(lProminenz, GBC.clProminenz);
        persoenlichesBottom.add(tProminenz, GBC.ctProminenz);
        persoenlichesBottom.add(lSonstiges, GBC.clSonstiges);
        persoenlichesBottom.add(tSonstiges, GBC.ctSonstiges);

        persoenliches.add(persoenlichesTop, GBC.cPersoenlichesTop);
        persoenliches.add(persoenlichesMiddleTop, GBC.cPersoenlichesMiddleTop);
        persoenliches.add(persoenlichesMiddleBottom, GBC.cPersoenlichesMiddleBottom);
        persoenliches.add(persoenlichesBottom, GBC.cPersoenlichesBottom);
        persoenliches.add(lKarma, GBC.clKarma);
        persoenliches.add(pvKarma, GBC.cpvKarma);

        attributeKoerper.add(lKonstitution, GBC.clKonstitution);
        attributeKoerper.add(tKonstitution, GBC.ctKonstitution);
        attributeKoerper.add(lGeschicklichkeit, GBC.clGeschicklichkeit);
        attributeKoerper.add(tGeschicklichkeit, GBC.ctGeschicklichkeit);
        attributeKoerper.add(lReaktion, GBC.clReaktion);
        attributeKoerper.add(tReaktion, GBC.ctReaktion);
        attributeKoerper.add(lStaerke, GBC.clStaerke);
        attributeKoerper.add(tStaerke, GBC.ctStaerke);

        attributeGeist.add(lWillenskraft, GBC.clWillenskraft);
        attributeGeist.add(tWillenskraft, GBC.ctWillenskraft);
        attributeGeist.add(lLogik, GBC.clLogik);
        attributeGeist.add(tLogik, GBC.ctLogik);
        attributeGeist.add(lIntuition, GBC.clIntuition);
        attributeGeist.add(tIntuition, GBC.ctIntuition);
        attributeGeist.add(lCharisma, GBC.clCharisma);
        attributeGeist.add(tCharisma, GBC.ctCharisma);

        attributeInitiative.add(lInitiative, GBC.clInitiative);
        attributeInitiative.add(lInitiativeW, GBC.clInitiativeW);
        attributeInitiative.add(tInitiative, GBC.ctInitiative);
        attributeInitiative.add(lAstralInitiative, GBC.clAstralInitiative);
        attributeInitiative.add(lAstralInitiativeW, GBC.clAstralInitiativeW);
        attributeInitiative.add(tAstralInitiative, GBC.ctAstralInitiative);
        attributeInitiative.add(lMatrixInitiativeAR, GBC.clMatrixInitiativeAR);
        attributeInitiative.add(lMatrixInitiativeARW, GBC.clMatrixInitiativeARW);
        attributeInitiative.add(tMatrixInitiativeAR, GBC.ctMatrixInitiativeAR);
        attributeInitiative.add(lMatrixInitiativekalt, GBC.clMatrixInitiativekalt);
        attributeInitiative.add(lMatrixInitiativekaltW, GBC.clMatrixInitiativekaltW);
        attributeInitiative.add(tMatrixInitiativekalt, GBC.ctMatrixInitiativekalt);
        attributeInitiative.add(lMatrixInitiativeheiß, GBC.clMatrixInitiativeheiß);
        attributeInitiative.add(lMatrixInitiativeheißW, GBC.clMatrixInitiativeheißW);
        attributeInitiative.add(tMatrixInitiativeheiß, GBC.ctMatrixInitiativeheiß);
        attributeInitiative.add(lRiggingInitiative, GBC.clRiggingInitiative);
        attributeInitiative.add(lRiggingInitiativeW, GBC.clRiggingInitiativeW);
        attributeInitiative.add(tRiggingInitiative, GBC.ctRiggingInitiative);
        attributeInitiative.add(lRiggingInitiativekalt, GBC.clRiggingInitiativekalt);
        attributeInitiative.add(lRiggingInitiativekaltW, GBC.clRiggingInitiativekaltW);
        attributeInitiative.add(tRiggingInitiativekalt, GBC.ctRiggingInitiativekalt);
        attributeInitiative.add(lRiggingInitiativeheiß, GBC.clRiggingInitiativeheiß);
        attributeInitiative.add(lRiggingInitiativeheißW, GBC.clRiggingInitiativeheißW);
        attributeInitiative.add(tRiggingInitiativeheiß, GBC.ctRiggingInitiativeheiß);
        attributeInitiative.add(lRiggingInitiativedirekt, GBC.clRiggingInitiativedirekt);
        attributeInitiative.add(lRiggingInitiativedirektW, GBC.clRiggingInitiativedirektW);
        attributeInitiative.add(tRiggingInitiativedirekt, GBC.ctRiggingInitiativedirekt);

        attributeNebenAttribute.add(lSelbstbeherrschung, GBC.clSelbstbeherrschung);
        attributeNebenAttribute.add(tSelbstbeherrschung, GBC.ctSelbstbeherrschung);
        attributeNebenAttribute.add(lMenschenkenntnis, GBC.clMenschenkenntnis);
        attributeNebenAttribute.add(tMenschenkenntnis, GBC.ctMenschenkenntnis);
        attributeNebenAttribute.add(lErinnerungsvermoegen, GBC.clErrinerungsvermoegen);
        attributeNebenAttribute.add(tErinnerungsvermoegen, GBC.ctErrinerungsvermoegen);
        attributeNebenAttribute.add(lHebenTragen, GBC.clHebenTragen);
        attributeNebenAttribute.add(tHebenTragen, GBC.ctHebenTragen);

        bewegung.add(lGehen, GBC.clGehen);
        bewegung.add(tGehen, GBC.ctGehen);
        bewegung.add(lLaufen, GBC.clLaufen);
        bewegung.add(tLaufen, GBC.ctLaufen);
        bewegung.add(lSprinten, GBC.clSprinten);
        bewegung.add(tSprinten, GBC.ctSprinten);

        attributeNebenAttribute.add(bewegung, GBC.cBewegung);
        attributeEssenzEdge.add(lEssenz, GBC.clEssenz);
        attributeEssenzEdge.add(tEssenz, GBC.ctEssenz);
        attributeEssenzEdge.add(lEdge, GBC.clEdge);
        attributeEssenzEdge.add(tEdge, GBC.ctEdge);
        attributeEssenzEdge.add(edgeAusgegeben, GBC.cEdgeAusgegeben);

        attributeMagieResonanz.add(lMagieResonanz, GBC.clMagieResonanz);
        attributeMagieResonanz.add(tMagieResonanz, GBC.ctMagieResonanz);

        attributeLimits.add(lLimitKoerper, GBC.clLimitKoerper);
        attributeLimits.add(tLimitKoerper, GBC.ctLimitKoerper);
        attributeLimits.add(lLimitGeist, GBC.clLimitGeist);
        attributeLimits.add(tLimitGeist, GBC.ctLimitGeist);
        attributeLimits.add(lLimitSozial, GBC.clLimitSozial);
        attributeLimits.add(tLimitSozial, GBC.ctLimitSozial);

        attributeLeft.add(attributeKoerper, GBC.cAttributeKoerper);
        attributeLeft.add(attributeGeist, GBC.cAttributeGeist);
        attributeLeft.add(attributeNebenAttribute, GBC.cAttributeNebenAttribute);

        attributeRight.add(attributeMagieResonanz, GBC.cAttributeMagieResonanz);
        attributeRight.add(attributeInitiative, GBC.cAttributeInitiative);
        attributeRight.add(attributeEssenzEdge, GBC.cAttributeEssenzEdge);

        attribute.add(attributeLeft, GBC.cAttributeLeft);
        attribute.add(attributeRight, GBC.cAttributeRight);
        attribute.add(attributeLimits, GBC.cAttributeLimits);

        main.add(persoenliches, GBC.cPersoenliches);
        main.add(attribute, GBC.cAttribute);

        right.add(tabbed, GBC.cTabbed);

        sheet.add(main);
        sheet.add(right);
    }

    public void setInitMod(int initMod) {
        this.initMod = initMod;
        lInitiativeW = new JLabel((1 + initMod) + "W6+");
        lAstralInitiativeW.setText((2 + initMod) + "W6+");
        lMatrixInitiativeARW.setText((1 + initMod) + "W6+");
        lMatrixInitiativekaltW.setText((3 + initMod) + "W6+");
        lMatrixInitiativeheißW.setText((4 + initMod) + "W6+");
        lRiggingInitiativeW.setText((1 + initMod) + "W6+");
        lRiggingInitiativekaltW.setText((1 + initMod) + "W6+");
        lRiggingInitiativeheißW.setText((1 + initMod) + "W6+");
        lRiggingInitiativedirektW.setText((1 + initMod) + "W6+");
    }

    public JFrame getSheet() {
        return sheet;
    }

    public JPanel getRight() {
        return right;
    }

    public JMenuBar getMenu() {
        return menu;
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

    public JCustomTextField gettSonstiges() {
        return tSonstiges;
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
}
