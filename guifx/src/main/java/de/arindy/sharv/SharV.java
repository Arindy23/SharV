package de.arindy.sharv;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class SharV extends Application implements Initializable {

    public AnchorPane root;
    public RadioMenuItem lang_de;
    public RadioMenuItem lang_en;
    public ColorPicker color;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        Font.loadFont(getClass().getResource("/fonts/Inconsolata-Regular.ttf").toExternalForm(), 15);

        FXMLLoader loader = getLoader();

        loader.load();
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1024);
        primaryStage.setTitle("SharV");
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.getIcons().addAll(
                new Image(getClass().getResourceAsStream("/icon/icon20x20.png")),
                new Image(getClass().getResourceAsStream("/icon/icon40x40.png"))
        );
        primaryStage.show();
    }

    private FXMLLoader getLoader() {
        return new FXMLLoader(
                getClass().getResource("/fxml/sharv.fxml"),
                new ResourceWrapper()
        );
    }

    public void changeLocale(final ActionEvent event) throws IOException {
        if (event.getSource().equals(lang_en)) {
            Locale.setDefault(Locale.ENGLISH);
            reload();
        } else if (event.getSource().equals(lang_de)) {
            Locale.setDefault(Locale.GERMAN);
            reload();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        color.setValue(Color.valueOf("#ff0000"));
        setHighlightColor("#ff0000");
        switch (Locale.getDefault().getLanguage()) {
            case "en":
                lang_en.setSelected(true);
                break;
            case "de":
                lang_de.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void changeColor() {
        setHighlightColor(convertToHex(color.getValue()));
    }

    private void setHighlightColor(String color) {
        root.setStyle(String.format("-sharV-highlight: %s;", color));
    }

    private String convertToHex(Color color) {
        return String.format("#%02X%02X%02X",
                    (int) (color.getRed() * color.getOpacity() * 255),
                    (int) (color.getGreen() * color.getOpacity() * 255),
                    (int) (color.getBlue() * color.getOpacity() * 255));
    }

    private void reload() throws IOException {
        root.getScene().setRoot(getLoader().load());
    }

    private static class ResourceWrapper extends ListResourceBundle {
        @Override
        protected Object[][] getContents() {
            return new Object[0][];
        }
    }

}
