package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.Reloadable;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;

public class SharVGUI extends Application implements Reloadable {

    public static final String TITLE = "SharV";
    private static final int MIN_HEIGHT = 768;
    private static final int MIN_WIDTH = 1200;
    private final Logger LOG;

    @FXML
    public AnchorPane root;

    @Inject
    private FXMLLoader fxmlLoader;
    @Inject
    private SharVMenu menu;

    public SharVGUI() {
        LOG = Logger.get(getClass().getName());
    }

    public static void main(String[] args) {
        launch(args);
    }

    static URL fxmlFile() {
        return SharVGUI.class.getResource("/fxml/sharv.fxml");
    }

    @Override
    public void start(@Observes @StartupScene final Stage primaryStage) throws IOException {
        LOG.entering();
        Font.loadFont(getClass().getResource("/fonts/Inconsolata-Regular.ttf").toExternalForm(), 15);
        fxmlLoader.setLocation(fxmlFile());
        fxmlLoader.setResources(new ResourceWrapper());
        primaryStage.setHeight(MIN_HEIGHT);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setWidth(MIN_WIDTH);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(fxmlLoader.load()));
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
        menu.setReloadable(this);
        menu.changeStyle("");
        menu.setHighlightColor("#ff0000");
    }

    @Override
    public void reload() {
        try {
            fxmlLoader.setLocation(fxmlFile());
            fxmlLoader.setResources(new ResourceWrapper());
            ((Stage) root.getScene().getWindow()).setTitle(TITLE);
            root.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ResourceWrapper extends ListResourceBundle {
        @Override
        protected Object[][] getContents() {
            return new Object[0][];
        }
    }

}
