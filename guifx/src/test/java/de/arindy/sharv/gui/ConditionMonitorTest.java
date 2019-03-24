package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.ConditionMonitorListener;
import de.arindy.sharv.api.gui.ConditionMonitorView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class ConditionMonitorTest extends HeadlessGUITest {

    private ConditionMonitorView conditionMonitor;
    private TestConditionMonitorListener conditionMonitorListener;

    @Start
    private void start(Stage stage) {
        super.start(stage, new Scene(createConditionMonitorPane()));
    }

    private ConditionMonitorPane createConditionMonitorPane() {
        final ConditionMonitorPane conditionMonitorPane = new ConditionMonitorPane();
        this.conditionMonitor = conditionMonitorPane;
        conditionMonitorPane.registerListener(createConditionMonitorListener());
        return conditionMonitorPane;
    }

    private ConditionMonitorListener createConditionMonitorListener() {
        conditionMonitorListener = new TestConditionMonitorListener();
        return conditionMonitorListener;
    }

    @Test
    void physicalDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> conditionMonitor.setPhysicalDamageMax(18));
        awaitSharV();
        robot(r).clickOn("#physicalDamage#5");
        verifyThat(conditionMonitorListener.physicalDamage, is(5));
    }

    @Test
    void unPhysicalDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            conditionMonitor.setPhysicalDamageMax(9);
            conditionMonitor.setPhysicalDamage(6);
        });
        awaitSharV();
        robot(r).clickOn("#physicalDamage#5");
        verifyThat(conditionMonitorListener.physicalDamage, is(4));
    }

    @Test
    void stunDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> conditionMonitor.setStunDamageMax(12));
        awaitSharV();
        robot(r).clickOn("#stunDamage#5");
        verifyThat(conditionMonitorListener.stunDamage, is(5));
    }

    @Test
    void unStunDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            conditionMonitor.setStunDamageMax(9);
            conditionMonitor.setStunDamage(6);
        });
        awaitSharV();
        robot(r).clickOn("#stunDamage#5");
        verifyThat(conditionMonitorListener.stunDamage, is(4));
    }

    @Test
    void setDicePoolModifierShouldUpdateDicePoolModifierLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> conditionMonitor.setDicePoolModifier(-3));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#dicePoolModifier"), LabeledMatchers.hasText("-3"));
    }

    private static class TestConditionMonitorListener implements ConditionMonitorListener {

        private int physicalDamage;
        private int stunDamage;

        @Override
        public void changePhysicalDamage(int physicalDamage) {
            this.physicalDamage = physicalDamage;
        }

        @Override
        public void changeStunDamage(int stunDamage) {
            this.stunDamage = stunDamage;
        }
    }

}
