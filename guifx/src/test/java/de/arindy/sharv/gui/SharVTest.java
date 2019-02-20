package de.arindy.sharv.gui;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class SharVTest extends HeadlessGUITest {

    private AnchorPane root;

    @Start
    private void start(Stage stage) throws Exception {
        new SharV().start(stage);
    }

    @Test
    void shouldStartWithoutFailing(final FxRobot robot) {
        verifyThat(robot.listWindows().iterator().next().getScene().getRoot().getId(), is("root"));
    }

}
