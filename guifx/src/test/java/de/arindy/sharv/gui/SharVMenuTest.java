package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import java.util.Locale;

import static javafx.application.Application.STYLESHEET_MODENA;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class SharVMenuTest extends HeadlessGUITest {

    private Pane root;
    private TextField color;

    @Start
    private void start(Stage stage) {
        this.root = new Pane(new SharVMenu());
        root.setPrefSize(600, 200);
        stage.setScene(new Scene(root));
        final TextField color = new TextField();
        color.setText("ColorTest");
        root.getChildren().add(color);
        stage.show();
    }

    @Test
    void changeLocaleToGermanShouldSetDefaultLocaleToGerman(final FxRobot robot) {
        robot.clickOn("#options").clickOn("#language").clickOn("#lang_de");
        verifyThat(Locale.getDefault(), is(Locale.GERMAN));
    }

    @Test
    void changeLocaleToEnglishShouldSetDefaultToEnglishLocale(final FxRobot robot) {
        robot.clickOn("#options").clickOn("#language").moveTo("#lang_de").clickOn("#lang_en");
        verifyThat(Locale.getDefault(), is(Locale.ENGLISH));
    }

    @Test
    void toggleDebugLogShouldToggleDebug(final FxRobot robot) {
        robot.clickOn("#options").clickOn("#debugLogging");
        verifyThat(Logger.isDebugLevel(), is(true));
        robot.clickOn("#options").clickOn("#debugLogging");
        verifyThat(Logger.isDebugLevel(), is(false));
    }

    @Test
    void switchingStyleToSharVShouldSetAddSharVStylesheet(final FxRobot robot) {
        robot.clickOn("#options").clickOn("#style").clickOn("#style_sharV");
        verifyThat(root.getStylesheets(), hasItem("/css/sharV.css"));
    }

    @Test
    void switchingStyleToModenaShouldSetAddModenaStylesheet(final FxRobot robot) {
        robot.clickOn("#options").clickOn("#style").moveTo("#style_sharV").clickOn("#style_modena");
        verifyThat(root.getStylesheets(), hasItem(STYLESHEET_MODENA));
    }

}
