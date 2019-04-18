package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.ConditionMonitorView;
import de.arindy.sharv.api.gui.DefaultConditionMonitorListener;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class ConditionMonitorTest extends HeadlessGUITest {

    private ConditionMonitorView conditionMonitor;
    private DefaultConditionMonitorListener conditionMonitorListener;

    @Start
    private void start(Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/fxml/conditionMonitor.fxml"),
                ResourceBundle.getBundle("lang/conditionMonitor")
        );
        super.start(stage, new Scene(fxmlLoader.load()));
        conditionMonitorListener = new DefaultConditionMonitorListener();
        conditionMonitor = ((ConditionMonitorPane) fxmlLoader.getController()).withConditionMonitorListener(conditionMonitorListener);
    }

    @Test
    void physicalDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> conditionMonitor.setPhysicalDamageMax(18));
        awaitSharV();
        robot(r).clickOn("#physicalDamage#5");
        verifyThat(conditionMonitorListener.getPhysicalDamage(), is(5));
    }

    @Test
    void unPhysicalDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            conditionMonitor.setPhysicalDamageMax(9);
            conditionMonitor.setPhysicalDamage(6);
        });
        awaitSharV();
        robot(r).clickOn("#physicalDamage#5");
        verifyThat(conditionMonitorListener.getPhysicalDamage(), is(4));
    }

    @Test
    void stunDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> conditionMonitor.setStunDamageMax(12));
        awaitSharV();
        robot(r).clickOn("#stunDamage#5");
        verifyThat(conditionMonitorListener.getStunDamage(), is(5));
    }

    @Test
    void unStunDamageShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            conditionMonitor.setStunDamageMax(9);
            conditionMonitor.setStunDamage(6);
        });
        awaitSharV();
        robot(r).clickOn("#stunDamage#5");
        verifyThat(conditionMonitorListener.getStunDamage(), is(4));
    }

    @Test
    void setDicePoolModifierShouldUpdateDicePoolModifierLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> conditionMonitor.setDicePoolModifier(-3));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#dicePoolModifier"), LabeledMatchers.hasText("-3"));
    }

}
