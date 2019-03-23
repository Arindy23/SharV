package de.arindy.sharv.gui;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.concurrent.Semaphore;

@ExtendWith(ApplicationExtension.class)
abstract class HeadlessGUITest {

    @BeforeAll
    private static void prepareHeadless() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }

    void awaitSharV() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        SharV.performAction(semaphore::release);
        semaphore.acquire();
    }

    Robot robot(final FxRobot robot) {
        return new Robot(robot);
    }

    static class Robot {

        private final FxRobot robot;

        Robot(FxRobot robot) {
            this.robot = robot;
        }

        Robot clickOn(String name) {
            robot.clickOn(name);
            return this;
        }

        Robot selectComboBoxItem(final String name, final String item) {
            final ComboBox<?> actualComboBox = lookupComboBox(name);
            for (Node child : actualComboBox.getChildrenUnmodifiable()) {
                if (child.getStyleClass().contains("arrow-button")) {
                    Node arrowRegion = ((Pane) child).getChildren().get(0);
                    robot.clickOn(arrowRegion);
                    robot.clickOn(item);
                }
            }
            return this;
        }

        ComboBox<Object> lookupComboBox(String name) {
            return robot.lookup(name).queryComboBox();
        }

        Robot write(final String value) {
            robot.write(value);
            return this;
        }

        Labeled lookupLabel(String name) {
            return robot.lookup(name).queryLabeled();
        }

        TextFlow lookupTextFlow(final String name) {
            return robot.lookup(name).queryTextFlow();

        }

        TextField lookupTextField(final String name) {
            return robot.lookup(name).queryAs(TextField.class);
        }
    }

}
