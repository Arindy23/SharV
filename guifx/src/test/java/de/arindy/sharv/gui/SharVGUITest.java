package de.arindy.sharv.gui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class SharVGUITest extends HeadlessGUITest {

    @Start
    private void start(Stage stage) throws Exception {
        new SharV().start(stage);
    }

    @Test
    void shouldStartWithoutFailing(final FxRobot robot) {
        verifyThat(robot.listWindows().iterator().next().getScene().getRoot().getId(), is("root"));
    }

}
