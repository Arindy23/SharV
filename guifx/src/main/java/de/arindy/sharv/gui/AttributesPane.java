package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.Attributes;
import de.arindy.sharv.gui.jfx.ContentAwareTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;

public class AttributesPane implements Attributes {

    private Logger LOG = Logger.get(getClass().getName());

    //<editor-fold desc="Variables">
    public Label bodyMax;
    public Label bodyEffective;
    public Label agilityMax;
    public Label agilityEffective;
    public Label reactionMax;
    public Label reactionEffective;
    public Label strengthMax;
    public Label strengthEffective;
    public Label willpowerMax;
    public Label willpowerEffective;
    public Label logicMax;
    public Label logicEffective;
    public Label intuitionMax;
    public Label intuitionEffective;
    public Label charismaMax;
    public Label charismaEffective;
    public Label composure;
    public Label judgeIntentions;
    public Label memory;
    public Label liftCarry;
    public Label walk;
    public Label run;
    public Label sprint;
    public Label initiativePhysical;
    public Label initiativeAstral;
    public Label initiativeMatrixAR;
    public Label initiativeMatrixVRcold;
    public Label initiativeMatrixVRhot;
    public Label initiativeRiggingAR;
    public Label initiativeRiggingVRcold;
    public Label initiativeRiggingVRhot;
    public Label initiativeRiggingDirect;
    public Label initiativePhysicalDice;
    public Label initiativeAstralDice;
    public Label initiativeMatrixARDice;
    public Label initiativeMatrixVRcoldDice;
    public Label initiativeMatrixVRhotDice;
    public Label initiativeRiggingARDice;
    public Label initiativeRiggingVRcoldDice;
    public Label initiativeRiggingVRhotDice;
    public Label initiativeRiggingDirectDice;
    public Label essence;
    public Label physicalLimit;
    public Label mentalLimit;
    public Label socialLimit;

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

    public void onBody(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onAgility(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onReaction(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    public void onStrength(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);
    }

    public void onWillpower(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    public void onLogic(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    public void onIntuition(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    public void onCharisma(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    public void onSpecial(ActionEvent actionEvent) {
        LOG.entering(actionEvent);

    }

    public void onSpecialValue(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    public void onEdge(InputMethodEvent inputMethodEvent) {
        LOG.entering(inputMethodEvent);

    }

    @Override
    public Attributes setBodyMax(int bodyMax) {
        this.bodyMax.setText(String.valueOf(bodyMax));
        this.body.setMaxValue(bodyMax);
        return this;
    }

    @Override
    public Attributes setBodyEffective(int bodyEffective) {
        this.bodyEffective.setText(String.valueOf(bodyEffective));
        return this;
    }

    @Override
    public Attributes setAgilityMax(int agilityMax) {
        this.agilityMax.setText(String.valueOf(agilityMax));
        this.agility.setMaxValue(agilityMax);
        return this;
    }

    @Override
    public Attributes setAgilityEffective(int agilityEffective) {
        this.agilityEffective.setText(String.valueOf(agilityEffective));
        return this;
    }

    @Override
    public Attributes setReactionMax(int reactionMax) {
        this.reactionMax.setText(String.valueOf(reactionMax));
        this.reaction.setMaxValue(reactionMax);
        return this;
    }

    @Override
    public Attributes setReactionEffective(int reactionEffective) {
        this.reactionEffective.setText(String.valueOf(reactionEffective));
        return this;
    }

    @Override
    public Attributes setStrengthMax(int strengthMax) {
        this.strengthMax.setText(String.valueOf(strengthMax));
        this.strength.setMaxValue(strengthMax);
        return this;
    }

    @Override
    public Attributes setStrengthEffective(int strengthEffective) {
        this.strengthEffective.setText(String.valueOf(strengthEffective));
        return this;
    }

    @Override
    public Attributes setWillpowerMax(int willpowerMax) {
        this.willpowerMax.setText(String.valueOf(willpowerMax));
        this.willpower.setMaxValue(willpowerMax);
        return this;
    }

    @Override
    public Attributes setWillpowerEffective(int willpowerEffective) {
        this.willpowerEffective.setText(String.valueOf(willpowerEffective));
        return this;
    }

    @Override
    public Attributes setLogicMax(int logicMax) {
        this.logicMax.setText(String.valueOf(logicMax));
        this.logic.setMaxValue(logicMax);
        return this;
    }

    @Override
    public Attributes setLogicEffective(int logicEffective) {
        this.logicEffective.setText(String.valueOf(logicEffective));
        return this;
    }

    @Override
    public Attributes setIntuitionMax(int intuitionMax) {
        this.intuitionMax.setText(String.valueOf(intuitionMax));
        this.intuition.setMaxValue(intuitionMax);
        return this;
    }

    @Override
    public Attributes setIntuitionEffective(int intuitionEffective) {
        this.intuitionEffective.setText(String.valueOf(intuitionEffective));
        return this;
    }

    @Override
    public Attributes setCharismaMax(int charismaMax) {
        this.charismaMax.setText(String.valueOf(charismaMax));
        this.charisma.setMaxValue(charismaMax);
        return this;
    }

    @Override
    public Attributes setCharismaEffective(int charismaEffective) {
        this.charismaEffective.setText(String.valueOf(charismaEffective));
        return this;
    }

    @Override
    public Attributes setComposure(int composure) {
        this.composure.setText(String.valueOf(composure));
        return this;
    }

    @Override
    public Attributes setJudgeIntentions(int judgeIntentions) {
        this.judgeIntentions.setText(String.valueOf(judgeIntentions));
        return this;
    }

    @Override
    public Attributes setMemory(int memory) {
        this.memory.setText(String.valueOf(memory));
        return this;
    }

    @Override
    public Attributes setLiftCarry(int liftCarry) {
        this.liftCarry.setText(String.valueOf(liftCarry));
        return this;
    }

    @Override
    public Attributes setWalk(int walk) {
        this.walk.setText(String.valueOf(walk));
        return this;
    }

    @Override
    public Attributes setRun(int run) {
        this.run.setText(String.valueOf(run));
        return this;
    }

    @Override
    public Attributes setSprint(int sprint) {
        this.sprint.setText(String.valueOf(sprint));
        return this;
    }

    @Override
    public Attributes setInitiativePhysical(int initiativePhysical) {
        this.initiativePhysical.setText(String.valueOf(initiativePhysical));
        return this;
    }

    @Override
    public Attributes setInitiativeAstral(int initiativeAstral) {
        this.initiativeAstral.setText(String.valueOf(initiativeAstral));
        return this;
    }

    @Override
    public Attributes setInitiativeMatrixAR(int initiativeMatrixAR) {
        this.initiativeMatrixAR.setText(String.valueOf(initiativeMatrixAR));
        return this;
    }

    @Override
    public Attributes setInitiativeMatrixVRcold(int initiativeMatrixVRcold) {
        this.initiativeMatrixVRcold.setText(String.valueOf(initiativeMatrixVRcold));
        return this;
    }

    @Override
    public Attributes setInitiativeMatrixVRhot(int initiativeMatrixVRhot) {
        this.initiativeMatrixVRhot.setText(String.valueOf(initiativeMatrixVRhot));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingAR(int initiativeRiggingAR) {
        this.initiativeRiggingAR.setText(String.valueOf(initiativeRiggingAR));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingVRcold(int initiativeRiggingVRcold) {
        this.initiativeRiggingVRcold.setText(String.valueOf(initiativeRiggingVRcold));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingVRhot(int initiativeRiggingVRhot) {
        this.initiativeRiggingVRhot.setText(String.valueOf(initiativeRiggingVRhot));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingDirect(int initiativeRiggingDirect) {
        this.initiativeRiggingDirect.setText(String.valueOf(initiativeRiggingDirect));
        return this;
    }

    @Override
    public Attributes setInitiativePhysicalDice(int initiativePhysicalDice) {
        return setInitiativePhysicalDice(initiativePhysicalDice, false);
    }

    @Override
    public Attributes setInitiativePhysicalDice(int initiativePhysicalDice, boolean highlight) {
        this.initiativePhysicalDice.setText(String.valueOf(initiativePhysicalDice));
        highlight(this.initiativePhysicalDice, highlight);
        return this;
    }

    @Override
    public Attributes setInitiativeAstralDice(int initiativeAstralDice) {
        return setInitiativeAstralDice(initiativeAstralDice, false);
    }

    @Override
    public Attributes setInitiativeAstralDice(int initiativeAstralDice, boolean highlight) {
        this.initiativeAstralDice.setText(String.valueOf(initiativeAstralDice));
        return this;
    }

    @Override
    public Attributes setInitiativeMatrixARDice(int initiativeMatrixARDice) {
        return setInitiativeMatrixARDice(initiativeMatrixARDice, false);
    }

    @Override
    public Attributes setInitiativeMatrixARDice(int initiativeMatrixARDice, boolean highlight) {
        this.initiativeMatrixARDice.setText(String.valueOf(initiativeMatrixARDice));
        return this;
    }

    @Override
    public Attributes setInitiativeMatrixVRcoldDice(int initiativeMatrixVRcoldDice) {
        return setInitiativeMatrixVRcoldDice(initiativeMatrixVRcoldDice, false);
    }

    @Override
    public Attributes setInitiativeMatrixVRcoldDice(int initiativeMatrixVRcoldDice, boolean highlight) {
        this.initiativeMatrixVRcoldDice.setText(String.valueOf(initiativeMatrixVRcoldDice));
        return this;
    }

    @Override
    public Attributes setInitiativeMatrixVRhotDice(int initiativeMatrixVRhotDice) {
        return setInitiativeMatrixVRhotDice(initiativeMatrixVRhotDice, false);
    }

    @Override
    public Attributes setInitiativeMatrixVRhotDice(int initiativeMatrixVRhotDice, boolean highlight) {
        this.initiativeMatrixVRhotDice.setText(String.valueOf(initiativeMatrixVRhotDice));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingARDice(int initiativeRiggingARDice) {
        return setInitiativeRiggingARDice(initiativeRiggingARDice, false);
    }

    @Override
    public Attributes setInitiativeRiggingARDice(int initiativeRiggingARDice, boolean highlight) {
        this.initiativeRiggingARDice.setText(String.valueOf(initiativeRiggingARDice));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingVRcoldDice(int initiativeRiggingVRcoldDice) {
        return setInitiativeRiggingVRcoldDice(initiativeRiggingVRcoldDice, false);
    }

    @Override
    public Attributes setInitiativeRiggingVRcoldDice(int initiativeRiggingVRcoldDice, boolean highlight) {
        this.initiativeRiggingVRcoldDice.setText(String.valueOf(initiativeRiggingVRcoldDice));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingVRhotDice(int initiativeRiggingVRhotDice) {
        return setInitiativeRiggingVRhotDice(initiativeRiggingVRhotDice, false);
    }

    @Override
    public Attributes setInitiativeRiggingVRhotDice(int initiativeRiggingVRhotDice, boolean highlight) {
        this.initiativeRiggingVRhotDice.setText(String.valueOf(initiativeRiggingVRhotDice));
        return this;
    }

    @Override
    public Attributes setInitiativeRiggingDirectDice(int initiativeRiggingDirectDice) {
        return setInitiativeRiggingDirectDice(initiativeRiggingDirectDice, false);
    }

    @Override
    public Attributes setInitiativeRiggingDirectDice(int initiativeRiggingDirectDice, boolean highlight) {
        this.initiativeRiggingDirectDice.setText(String.valueOf(initiativeRiggingDirectDice));
        return this;
    }

    @Override
    public Attributes setEssence(int essence) {
        this.essence.setText(String.valueOf(essence));
        return this;
    }

    @Override
    public Attributes setPhysicalLimit(int physicalLimit) {
        this.physicalLimit.setText(String.valueOf(physicalLimit));
        return this;
    }

    @Override
    public Attributes setMentalLimit(int mentalLimit) {
        this.mentalLimit.setText(String.valueOf(mentalLimit));
        return this;
    }

    @Override
    public Attributes setSocialLimit(int socialLimit) {
        this.socialLimit.setText(String.valueOf(socialLimit));
        return this;
    }

    private static void highlight(Label label, boolean highlight) {
        if (highlight) {
            label.getStyleClass().add("value-label");
        } else {
            label.getStyleClass().removeAll("value-label");
        }
    }

}
