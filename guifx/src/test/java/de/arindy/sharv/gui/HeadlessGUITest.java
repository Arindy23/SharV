package de.arindy.sharv.gui;

import javafx.scene.control.Labeled;
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

    class Robot {

        private final FxRobot robot;

        Robot(FxRobot robot) {
            this.robot = robot;
        }

        Robot clickOnTextInput(final String name) {
            robot.clickOn(robot.lookup(name).queryTextInputControl());
            return this;
        }

        Robot write(final String value) {
            robot.write(value);
            return this;
        }

        Labeled lookupLabel(String name) {
            return robot.lookup(name).queryLabeled();
        }
    }

}
