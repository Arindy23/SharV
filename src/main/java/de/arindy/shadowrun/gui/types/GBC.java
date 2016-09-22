package de.arindy.shadowrun.gui.types;

import java.awt.*;

public class GBC {

    private static Insets insets = new Insets(0, 0, 0, 0);

    //<editor-fold desc="CharSheet">
    public static GridBagConstraints cTabbed = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cZustand = new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cPersoenliches = new GridBagConstraints(0, 0, 1, 1, 1, 0.08, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cAttribute = new GridBagConstraints(0, 1, 1, 1, 1, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints cPersoenlichesTop = new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cPersoenlichesMiddleTop = new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cPersoenlichesMiddleBottom = new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cPersoenlichesBottom = new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints cAttributeLeft = new GridBagConstraints(0, 0, 1, 1, 2.82, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cAttributeRight = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints cAttributeKoerper = new GridBagConstraints(0, 0, 1, 1, 1, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cAttributeGeist = new GridBagConstraints(0, 1, 1, 1, 1, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cAttributeNebenAttribute = new GridBagConstraints(0, 2, 1, 1, 1, 0.4, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints cAttributeMagieResonanz = new GridBagConstraints(0, 0, 1, 1, 1, 0.08, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cAttributeInitiative = new GridBagConstraints(0, 1, 1, 1, 1, 0.4, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cAttributeEssenzEdge = new GridBagConstraints(0, 2, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints cEdgeAusgegeben = new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints cBewegung = new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints cAttributeLimits = new GridBagConstraints(0, 1, 2, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

    public static GridBagConstraints clName = new GridBagConstraints(0, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMetatyp = new GridBagConstraints(2, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clGeschlecht = new GridBagConstraints(2, 0, 1, 1, 0.3, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clAlter = new GridBagConstraints(4, 0, 1, 1, 0.5, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clGroesse = new GridBagConstraints(6, 0, 1, 1, 0.5, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clGewicht = new GridBagConstraints(8, 0, 1, 1, 0.5, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clEthnie = new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clKonzept = new GridBagConstraints(2, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clStrassenruf = new GridBagConstraints(0, 0, 1, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clSchlechterRuf = new GridBagConstraints(2, 0, 1, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clProminenz = new GridBagConstraints(4, 0, 1, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clSonstiges = new GridBagConstraints(6, 0, 1, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clKarma = new GridBagConstraints(0, 4, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clKonstitution = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clGeschicklichkeit = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clReaktion = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clStaerke = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clWillenskraft = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clLogik = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clIntuition = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clCharisma = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clMagieResonanz = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clInitiative = new GridBagConstraints(0, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clAstralInitiative = new GridBagConstraints(0, 1, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMatrixInitiativeAR = new GridBagConstraints(0, 2, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMatrixInitiativekalt = new GridBagConstraints(0, 3, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMatrixInitiativeheiß = new GridBagConstraints(0, 4, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiative = new GridBagConstraints(0, 5, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativekalt = new GridBagConstraints(0, 6, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativeheiß = new GridBagConstraints(0, 7, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativedirekt = new GridBagConstraints(0, 8, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clInitiativeW = new GridBagConstraints(1, 0, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clAstralInitiativeW = new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMatrixInitiativeARW = new GridBagConstraints(1, 2, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMatrixInitiativekaltW = new GridBagConstraints(1, 3, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMatrixInitiativeheißW = new GridBagConstraints(1, 4, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativeW = new GridBagConstraints(1, 5, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativekaltW = new GridBagConstraints(1, 6, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativeheißW = new GridBagConstraints(1, 7, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clRiggingInitiativedirektW = new GridBagConstraints(1, 8, 1, 1, 0, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clSelbstbeherrschung = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clMenschenkenntnis = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clErrinerungsvermoegen = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clHebenTragen = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clGehen = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clLaufen = new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clSprinten = new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clEssenz = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clEdge = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints clLimitKoerper = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clLimitGeist = new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clLimitSozial = new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints ctName = new GridBagConstraints(1, 0, 1, 1, 0.01, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctMetatyp = new GridBagConstraints(3, 0, 1, 1, 0.01, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctGeschlecht = new GridBagConstraints(3, 0, 1, 1, 0.01, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctAlter = new GridBagConstraints(5, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctGroesse = new GridBagConstraints(7, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctGewicht = new GridBagConstraints(9, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctEthnie = new GridBagConstraints(1, 0, 1, 1, 0.1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctKonzept = new GridBagConstraints(3, 0, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctStrassenruf = new GridBagConstraints(1, 0, 1, 1, 0.6, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctSchlechterRuf = new GridBagConstraints(3, 0, 1, 1, 0.6, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctProminenz = new GridBagConstraints(5, 0, 1, 1, 0.6, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctSonstiges = new GridBagConstraints(7, 0, 1, 1, 0.6, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints cpvKarma = new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctKonstitution = new GridBagConstraints(1, 0, 1, 1, 0.06, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctGeschicklichkeit = new GridBagConstraints(1, 1, 1, 1, 0.06, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctReaktion = new GridBagConstraints(1, 2, 1, 1, 0.06, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctStaerke = new GridBagConstraints(1, 3, 1, 1, 0.06, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctWillenskraft = new GridBagConstraints(1, 0, 1, 1, 0.043, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctLogik = new GridBagConstraints(1, 1, 1, 1, 0.043, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctIntuition = new GridBagConstraints(1, 2, 1, 1, 0.043, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctCharisma = new GridBagConstraints(1, 3, 1, 1, 0.043, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctMagieResonanz = new GridBagConstraints(1, 0, 1, 1, 0.03, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctInitiative = new GridBagConstraints(2, 0, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctAstralInitiative = new GridBagConstraints(2, 1, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctMatrixInitiativeAR = new GridBagConstraints(2, 2, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctMatrixInitiativekalt = new GridBagConstraints(2, 3, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctMatrixInitiativeheiß = new GridBagConstraints(2, 4, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctRiggingInitiative = new GridBagConstraints(2, 5, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctRiggingInitiativekalt = new GridBagConstraints(2, 6, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctRiggingInitiativeheiß = new GridBagConstraints(2, 7, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctRiggingInitiativedirekt = new GridBagConstraints(2, 8, 1, 1, 0.04, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctSelbstbeherrschung = new GridBagConstraints(1, 0, 1, 1, 0.08, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctMenschenkenntnis = new GridBagConstraints(1, 1, 1, 1, 0.08, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctErrinerungsvermoegen = new GridBagConstraints(1, 2, 1, 1, 0.08, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctHebenTragen = new GridBagConstraints(1, 3, 1, 1, 0.08, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctGehen = new GridBagConstraints(1, 0, 1, 1, 0.3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctLaufen = new GridBagConstraints(3, 0, 1, 1, 0.3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctSprinten = new GridBagConstraints(5, 0, 1, 1, 0.3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctEssenz = new GridBagConstraints(1, 0, 1, 1, 0.03, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctEdge = new GridBagConstraints(1, 1, 1, 1, 0.03, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    public static GridBagConstraints ctLimitKoerper = new GridBagConstraints(1, 0, 1, 1, 0.01, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctLimitGeist = new GridBagConstraints(3, 0, 1, 1, 0.01, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints ctLimitSozial = new GridBagConstraints(5, 0, 1, 1, 0.01, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    //</editor-fold>
    //<editor-fold desc="Zustandsmonitor">
    public static GridBagConstraints cpKoerperlich = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clKoerperlich = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cpGeistig = new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clGeistig = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cVerletzungsmod = new GridBagConstraints(2, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints clVerletzungsmod = new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cpUeberzaehlig = new GridBagConstraints(0, 3, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints clUeberzaehlig = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cUeberzaehlig = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    //</editor-fold>
    //<editor-fold desc="VorteilNachteilVerwaltung">
    public static GridBagConstraints cvnwpTop = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cvnwListe = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cvnwbAktualisieren = new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints cvnwpLoeschenSpeichern = new GridBagConstraints(0, 1, 2, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints cvnwbLoeschen = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwbSpeichern = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

    public static GridBagConstraints cvnwlVorteilNachteil = new GridBagConstraints(0, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwpName = new GridBagConstraints(0, 1, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwlName = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwtName = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
    public static GridBagConstraints cvnwlBeschreibung = new GridBagConstraints(0, 2, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwtBeschreibung = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cvnwlDetails = new GridBagConstraints(0, 4, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwtDetails = new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
    public static GridBagConstraints cvnwpKarmaBonusKosten = new GridBagConstraints(0, 6, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwlKarmaBonusKosten = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
    public static GridBagConstraints cvnwtKarmaBonusKosten = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);

    //</editor-fold>

}
