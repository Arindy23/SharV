package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.CharacterView;
import de.arindy.sharv.api.gui.ConditionMonitorView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ListResourceBundle;

public class SharVGUI extends Application {

    public static final String TITLE = "SharV";
    private static final int MIN_HEIGHT = 768;
    private static final int MIN_WIDTH = 1200;
    private final Logger LOG;

    @FXML
    private CharacterView character;
    @FXML
    private SharVMenu menu;
    @FXML
    private ConditionMonitorView conditionMonitor;
    @FXML
    private HBox content;

    public SharVGUI() {
        LOG = Logger.get(getClass().getName());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        LOG.entering();
        Font.loadFont(getClass().getResource("/fonts/Inconsolata-Regular.ttf").toExternalForm(), 15);

        primaryStage.setHeight(MIN_HEIGHT);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setWidth(MIN_WIDTH);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(getLoader().load()));
        primaryStage.getIcons().addAll(
                icons()
        );
        primaryStage.setOnCloseRequest(e -> new ExitAction().perform());
        primaryStage.show();
    }

    private Image[] icons() {
        return new Image[]{
                new Image(getClass().getResourceAsStream("/icon/icon32x32.png")),
                new Image(getClass().getResourceAsStream("/icon/icon40x40.png")),
                new Image(getClass().getResourceAsStream("/icon/icon48x48.png")),
                new Image(getClass().getResourceAsStream("/icon/icon64x64.png")),
                new Image(getClass().getResourceAsStream("/icon/icon128x128.png")),
                new Image(getClass().getResourceAsStream("/icon/icon256x256.png"))
        };
    }

    public void initialize() {
        menu.changeStyle("");
        menu.setHighlightColor("#ff0000");
    }

    static FXMLLoader getLoader() {
        return new FXMLLoader(
                SharVGUI.class.getResource("/fxml/sharv.fxml"),
                new ResourceWrapper()
        );
    }

    private static class ResourceWrapper extends ListResourceBundle {
        @Override
        protected Object[][] getContents() {
            return new Object[0][];
        }
    }

}
