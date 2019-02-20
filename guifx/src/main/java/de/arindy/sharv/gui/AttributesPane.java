package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.AttributesListener;
import de.arindy.sharv.api.gui.AttributesView;
import de.arindy.sharv.gui.jfx.BorderedTitledPane;
import de.arindy.sharv.gui.jfx.ContentAwareTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;

import static de.arindy.sharv.gui.JavaFXUtil.extractInteger;
import static de.arindy.sharv.gui.JavaFXUtil.getSelectedItem;

public class AttributesPane extends BorderedTitledPane implements AttributesView {

    private Logger LOG = Logger.get(getClass().getName());

    private AttributesListener attributesListener;

    //<editor-fold desc="Variables">
    @FXML
    private Label bodyMax;
    @FXML
    private Label bodyEffective;
    @FXML
    private Label agilityMax;
    @FXML
    private Label agilityEffective;
    @FXML
    private Label reactionMax;
    @FXML
    private Label reactionEffective;
    @FXML
    private Label strengthMax;
    @FXML
    private Label strengthEffective;
    @FXML
    private Label willpowerMax;
    @FXML
    private Label willpowerEffective;
    @FXML
    private Label logicMax;
    @FXML
    private Label logicEffective;
    @FXML
    private Label intuitionMax;
    @FXML
    private Label intuitionEffective;
    @FXML
    private Label charismaMax;
    @FXML
    private Label charismaEffective;
    @FXML
    private Label composure;
    @FXML
    private Label judgeIntentions;
    @FXML
    private Label memory;
    @FXML
    private Label liftCarry;
    @FXML
    private Label walk;
    @FXML
    private Label run;
    @FXML
    private Label sprint;
    @FXML
    private Label initiativePhysical;
    @FXML
    private Label initiativeAstral;
    @FXML
    private Label initiativeMatrixAR;
    @FXML
    private Label initiativeMatrixVRcold;
    @FXML
    private Label initiativeMatrixVRhot;
    @FXML
    private Label initiativeRiggingAR;
    @FXML
    private Label initiativeRiggingVRcold;
    @FXML
    private Label initiativeRiggingVRhot;
    @FXML
    private Label initiativeRiggingDirect;
    @FXML
    private Label initiativePhysicalDice;
    @FXML
    private Label initiativeAstralDice;
    @FXML
    private Label initiativeMatrixARDice;
    @FXML
    private Label initiativeMatrixVRcoldDice;
    @FXML
    private Label initiativeMatrixVRhotDice;
    @FXML
    private Label initiativeRiggingARDice;
    @FXML
    private Label initiativeRiggingVRcoldDice;
    @FXML
    private Label initiativeRiggingVRhotDice;
    @FXML
    private Label initiativeRiggingDirectDice;
    @FXML
    private Label essence;
    @FXML
    private Label physicalLimit;
    @FXML
    private Label mentalLimit;
    @FXML
    private Label socialLimit;

    public ComboBox<String> special;
    public ContentAwareTextField specialValue;
    public ContentAwareTextField body;
    public ContentAwareTextField agility;
    public ContentAwareTextField reaction;
    public ContentAwareTextField strength;
    public ContentAwareTextField willpower;
    public ContentAwareTextField logic;
    public ContentAwareTextField intuition;
    public ContentAwareTextField charisma;
    public ContentAwareTextField edge;
    //</editor-fold>

    public AttributesPane() {
        super("attributes");
    }

    public void onBody(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeBody(extractInteger(inputMethodEvent));
        }
    }

    public void onAgility(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeAgility(extractInteger(inputMethodEvent));
        }
    }

    public void onReaction(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeReaction(extractInteger(inputMethodEvent));
        }
    }

    public void onStrength(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeStrength(extractInteger(inputMethodEvent));
        }
    }

    public void onWillpower(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeWillpower(extractInteger(inputMethodEvent));
        }
    }

    public void onLogic(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeLogic(extractInteger(inputMethodEvent));
        }
    }

    public void onIntuition(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeIntuition(extractInteger(inputMethodEvent));
        }
    }

    public void onCharisma(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeCharisma(extractInteger(inputMethodEvent));
        }
    }

    public void onSpecial(final ActionEvent actionEvent) {
        LOG.entering(actionEvent);
        if (listenerRegistered()) {
            attributesListener.changeSpecial(getSelectedItem(special));
        }
    }

    public void onSpecialValue(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeSpecialValue(extractInteger(inputMethodEvent));
        }
    }

    public void onEdge(final InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
        if (listenerRegistered()) {
            attributesListener.changeEdge(extractInteger(inputMethodEvent));
        }
    }

    @Override
    public AttributesView setBodyMax(final int bodyMax) {
        LOG.entering(bodyMax);
        this.bodyMax.setText(String.valueOf(bodyMax));
        this.body.setMaxValue(bodyMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setBodyEffective(final int bodyEffective) {
        LOG.entering(bodyEffective);
        this.bodyEffective.setText(String.valueOf(bodyEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setAgilityMax(final int agilityMax) {
        LOG.entering(agilityMax);
        this.agilityMax.setText(String.valueOf(agilityMax));
        this.agility.setMaxValue(agilityMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setAgilityEffective(final int agilityEffective) {
        LOG.entering(agilityEffective);
        this.agilityEffective.setText(String.valueOf(agilityEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setReactionMax(final int reactionMax) {
        LOG.entering(reactionMax);
        this.reactionMax.setText(String.valueOf(reactionMax));
        this.reaction.setMaxValue(reactionMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setReactionEffective(final int reactionEffective) {
        LOG.entering(reactionEffective);
        this.reactionEffective.setText(String.valueOf(reactionEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setStrengthMax(final int strengthMax) {
        LOG.entering(strengthMax);
        this.strengthMax.setText(String.valueOf(strengthMax));
        this.strength.setMaxValue(strengthMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setStrengthEffective(final int strengthEffective) {
        LOG.entering(strengthEffective);
        this.strengthEffective.setText(String.valueOf(strengthEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setWillpowerMax(final int willpowerMax) {
        LOG.entering(willpowerMax);
        this.willpowerMax.setText(String.valueOf(willpowerMax));
        this.willpower.setMaxValue(willpowerMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setWillpowerEffective(final int willpowerEffective) {
        LOG.entering(willpowerEffective);
        this.willpowerEffective.setText(String.valueOf(willpowerEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setLogicMax(final int logicMax) {
        LOG.entering(logicMax);
        this.logicMax.setText(String.valueOf(logicMax));
        this.logic.setMaxValue(logicMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setLogicEffective(final int logicEffective) {
        LOG.entering(logicEffective);
        this.logicEffective.setText(String.valueOf(logicEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setIntuitionMax(final int intuitionMax) {
        LOG.entering(intuitionMax);
        this.intuitionMax.setText(String.valueOf(intuitionMax));
        this.intuition.setMaxValue(intuitionMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setIntuitionEffective(final int intuitionEffective) {
        LOG.entering(intuitionEffective);
        this.intuitionEffective.setText(String.valueOf(intuitionEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setCharismaMax(final int charismaMax) {
        LOG.entering(charismaMax);
        this.charismaMax.setText(String.valueOf(charismaMax));
        this.charisma.setMaxValue(charismaMax);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setCharismaEffective(final int charismaEffective) {
        LOG.entering(charismaEffective);
        this.charismaEffective.setText(String.valueOf(charismaEffective));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setComposure(final int composure) {
        LOG.entering(composure);
        this.composure.setText(String.valueOf(composure));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setJudgeIntentions(final int judgeIntentions) {
        LOG.entering(judgeIntentions);
        this.judgeIntentions.setText(String.valueOf(judgeIntentions));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setMemory(final int memory) {
        LOG.entering(memory);
        this.memory.setText(String.valueOf(memory));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setLiftCarry(final int liftCarry) {
        LOG.entering(liftCarry);
        this.liftCarry.setText(String.valueOf(liftCarry));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setWalk(final int walk) {
        LOG.entering(walk);
        this.walk.setText(String.valueOf(walk));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setRun(final int run) {
        LOG.entering(run);
        this.run.setText(String.valueOf(run));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setSprint(final int sprint) {
        LOG.entering(sprint);
        this.sprint.setText(String.valueOf(sprint));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativePhysical(final int initiativePhysical) {
        LOG.entering(initiativePhysical);
        this.initiativePhysical.setText(String.valueOf(initiativePhysical));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeAstral(final int initiativeAstral) {
        LOG.entering(initiativeAstral);
        this.initiativeAstral.setText(String.valueOf(initiativeAstral));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeMatrixAR(final int initiativeMatrixAR) {
        LOG.entering(initiativeMatrixAR);
        this.initiativeMatrixAR.setText(String.valueOf(initiativeMatrixAR));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeMatrixVRcold(final int initiativeMatrixVRcold) {
        LOG.entering(initiativeMatrixVRcold);
        this.initiativeMatrixVRcold.setText(String.valueOf(initiativeMatrixVRcold));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeMatrixVRhot(final int initiativeMatrixVRhot) {
        LOG.entering(initiativeMatrixVRhot);
        this.initiativeMatrixVRhot.setText(String.valueOf(initiativeMatrixVRhot));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingAR(final int initiativeRiggingAR) {
        LOG.entering(initiativeRiggingAR);
        this.initiativeRiggingAR.setText(String.valueOf(initiativeRiggingAR));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingVRcold(final int initiativeRiggingVRcold) {
        LOG.entering(initiativeRiggingVRcold);
        this.initiativeRiggingVRcold.setText(String.valueOf(initiativeRiggingVRcold));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingVRhot(final int initiativeRiggingVRhot) {
        LOG.entering(initiativeRiggingVRhot);
        this.initiativeRiggingVRhot.setText(String.valueOf(initiativeRiggingVRhot));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingDirect(final int initiativeRiggingDirect) {
        LOG.entering(initiativeRiggingDirect);
        this.initiativeRiggingDirect.setText(String.valueOf(initiativeRiggingDirect));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativePhysicalDice(final int initiativePhysicalDice) {
        return setInitiativePhysicalDice(initiativePhysicalDice, false);
    }

    @Override
    public AttributesView setInitiativePhysicalDice(final int initiativePhysicalDice, boolean highlight) {
        LOG.entering(initiativePhysicalDice, highlight);
        this.initiativePhysicalDice.setText(String.valueOf(initiativePhysicalDice));
        highlight(this.initiativePhysicalDice, highlight);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeAstralDice(final int initiativeAstralDice) {
        return setInitiativeAstralDice(initiativeAstralDice, false);
    }

    @Override
    public AttributesView setInitiativeAstralDice(final int initiativeAstralDice, boolean highlight) {
        LOG.entering(initiativeAstralDice, highlight);
        this.initiativeAstralDice.setText(String.valueOf(initiativeAstralDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeMatrixARDice(final int initiativeMatrixARDice) {
        return setInitiativeMatrixARDice(initiativeMatrixARDice, false);
    }

    @Override
    public AttributesView setInitiativeMatrixARDice(final int initiativeMatrixARDice, boolean highlight) {
        LOG.entering(initiativeMatrixARDice, highlight);
        this.initiativeMatrixARDice.setText(String.valueOf(initiativeMatrixARDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeMatrixVRcoldDice(final int initiativeMatrixVRcoldDice) {
        return setInitiativeMatrixVRcoldDice(initiativeMatrixVRcoldDice, false);
    }

    @Override
    public AttributesView setInitiativeMatrixVRcoldDice(final int initiativeMatrixVRcoldDice, boolean highlight) {
        LOG.entering(initiativeMatrixVRcoldDice, highlight);
        this.initiativeMatrixVRcoldDice.setText(String.valueOf(initiativeMatrixVRcoldDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeMatrixVRhotDice(final int initiativeMatrixVRhotDice) {
        return setInitiativeMatrixVRhotDice(initiativeMatrixVRhotDice, false);
    }

    @Override
    public AttributesView setInitiativeMatrixVRhotDice(final int initiativeMatrixVRhotDice, boolean highlight) {
        LOG.entering(initiativeMatrixVRhotDice, highlight);
        this.initiativeMatrixVRhotDice.setText(String.valueOf(initiativeMatrixVRhotDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingARDice(final int initiativeRiggingARDice) {
        return setInitiativeRiggingARDice(initiativeRiggingARDice, false);
    }

    @Override
    public AttributesView setInitiativeRiggingARDice(final int initiativeRiggingARDice, boolean highlight) {
        LOG.entering(initiativeRiggingARDice, highlight);
        this.initiativeRiggingARDice.setText(String.valueOf(initiativeRiggingARDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingVRcoldDice(final int initiativeRiggingVRcoldDice) {
        return setInitiativeRiggingVRcoldDice(initiativeRiggingVRcoldDice, false);
    }

    @Override
    public AttributesView setInitiativeRiggingVRcoldDice(final int initiativeRiggingVRcoldDice, boolean highlight) {
        LOG.entering(initiativeRiggingVRcoldDice, highlight);
        this.initiativeRiggingVRcoldDice.setText(String.valueOf(initiativeRiggingVRcoldDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingVRhotDice(final int initiativeRiggingVRhotDice) {
        return setInitiativeRiggingVRhotDice(initiativeRiggingVRhotDice, false);
    }

    @Override
    public AttributesView setInitiativeRiggingVRhotDice(final int initiativeRiggingVRhotDice, boolean highlight) {
        LOG.entering(initiativeRiggingVRhotDice, highlight);
        this.initiativeRiggingVRhotDice.setText(String.valueOf(initiativeRiggingVRhotDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setInitiativeRiggingDirectDice(final int initiativeRiggingDirectDice) {
        return setInitiativeRiggingDirectDice(initiativeRiggingDirectDice, false);
    }

    @Override
    public AttributesView setInitiativeRiggingDirectDice(final int initiativeRiggingDirectDice, boolean highlight) {
        LOG.entering(initiativeRiggingDirectDice, highlight);
        this.initiativeRiggingDirectDice.setText(String.valueOf(initiativeRiggingDirectDice));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setEssence(final int essence) {
        LOG.entering(essence);
        this.essence.setText(String.valueOf(essence));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setPhysicalLimit(final int physicalLimit) {
        LOG.entering(physicalLimit);
        this.physicalLimit.setText(String.valueOf(physicalLimit));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setMentalLimit(final int mentalLimit) {
        LOG.entering(mentalLimit);
        this.mentalLimit.setText(String.valueOf(mentalLimit));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setSocialLimit(final int socialLimit) {
        LOG.entering(socialLimit);
        this.socialLimit.setText(String.valueOf(socialLimit));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setSpecial(final String special) {
        LOG.entering(special);
        this.special.getSelectionModel().select(special);
        return LOG.returning(this);
    }

    @Override
    public AttributesView setSpecialValue(final int specialValue) {
        LOG.entering(specialValue);
        this.specialValue.setText(String.valueOf(specialValue));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setBody(final int body) {
        LOG.entering(body);
        this.body.setText(String.valueOf(body));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setAgility(final int agility) {
        LOG.entering(agility);
        this.agility.setText(String.valueOf(agility));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setReaction(final int reaction) {
        LOG.entering(reaction);
        this.reaction.setText(String.valueOf(reaction));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setStrength(final int strength) {
        LOG.entering(strength);
        this.strength.setText(String.valueOf(strength));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setWillpower(final int willpower) {
        LOG.entering(willpower);
        this.willpower.setText(String.valueOf(willpower));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setLogic(final int logic) {
        LOG.entering(logic);
        this.logic.setText(String.valueOf(logic));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setIntuition(final int intuition) {
        LOG.entering(intuition);
        this.intuition.setText(String.valueOf(intuition));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setCharisma(final int charisma) {
        LOG.entering(charisma);
        this.charisma.setText(String.valueOf(charisma));
        return LOG.returning(this);
    }

    @Override
    public AttributesView setEdge(final int edge) {
        LOG.entering(edge);
        this.edge.setText(String.valueOf(edge));
        return LOG.returning(this);
    }

    @Override
    public AttributesView registerListener(final AttributesListener attributesListener) {
        this.attributesListener = attributesListener;
        return LOG.returning(this);
    }

    private boolean listenerRegistered() {
        return attributesListener != null;
    }

    private static void highlight(final Label label, final boolean highlight) {
        if (highlight) {
            label.getStyleClass().add("value-label");
        } else {
            label.getStyleClass().removeAll("value-label");
        }
    }

}
