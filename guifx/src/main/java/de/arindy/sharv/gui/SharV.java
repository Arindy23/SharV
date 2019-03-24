package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.CharacterView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class SharV extends Application implements Initializable {

    public static final String TITLE = "SharV";
    private static final int MIN_HEIGHT = 768;
    private static final int MIN_WIDTH = 1200;
    private final Logger LOG = Logger.get(getClass().getName());

    public CharacterView character;
    public SharVMenu menu;
    public ConditionMonitorPane conditionMonitor;

    public static void main(String[] args) {
        launch(args);
    }

    public AnchorPane root;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menu.changeStyle("");
        menu.setHighlightColor("#ff0000");
    }

    static FXMLLoader getLoader() {
        return new FXMLLoader(
                SharV.class.getResource("/fxml/sharv.fxml"),
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
