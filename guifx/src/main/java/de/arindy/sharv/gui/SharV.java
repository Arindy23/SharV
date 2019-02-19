package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.CharacterView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class SharV extends Application implements Initializable {

    private final Logger LOG = Logger.get(getClass().getName());

    private static String activeStyle = "style_sharV";

    @FXML
    public CharacterView character;

    public static void main(String[] args) {
        launch(args);
    }

    public AnchorPane root;

    //<editor-fold desc="Menu">
    public Menu file;
    public MenuItem close;
    public Menu characterMenu;
    public MenuItem characterLoad;
    public MenuItem characterSave;
    public Menu control;
    public MenuItem controlQualities;
    public Menu options;
    public Menu language;
    public Menu style;
    public RadioMenuItem lang_de;
    public RadioMenuItem lang_en;
    public RadioMenuItem style_sharV;
    public RadioMenuItem style_modena;
    public RadioMenuItem debugLogging;
    public ColorPicker color;
    //</editor-fold>

    @Override
    public void start(final Stage primaryStage) throws IOException {
        LOG.entering();
        Font.loadFont(getClass().getResource("/fonts/Inconsolata-Regular.ttf").toExternalForm(), 15);

        FXMLLoader loader = getLoader();

        loader.load();
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);
        primaryStage.setTitle("SharV");
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.getIcons().addAll(
                icons()
        );
        primaryStage.setOnCloseRequest(e -> exit());
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
        LOG.entering(url, resourceBundle);
        changeStyle();
        setHighlightColor("#ff0000");
        debugLogging.setSelected(Logger.isDebugLevel());
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
        initializeMenuText();
        character.getPersonalData().setNuyen(99999);
    }

    private void initializeMenuText() {
        setText(file, "file");
        setText(close, "close");
        setText(characterMenu, "character.menu");
        setText(characterLoad, "character.load");
        setText(characterSave, "character.save");
        setText(control, "control");
        setText(controlQualities, "control.qualities");
        setText(options, "options");
        setText(language, "language");
        setText(style, "style");
        setText(lang_de, "locale.german");
        setText(lang_en, "locale.english");
        setText(debugLogging, "options.debugToggle");
    }

    public void loadCharacter() {
        LOG.entering();
    }

    public void saveCharacter() {
        LOG.entering();
    }

    public void changeLocale(final ActionEvent event) throws IOException {
        LOG.entering(event);
        if (event.getSource().equals(lang_en)) {
            Locale.setDefault(Locale.ENGLISH);
            reload();
        } else if (event.getSource().equals(lang_de)) {
            Locale.setDefault(Locale.GERMAN);
            reload();
        }
    }

    public void changeStyle(final ActionEvent event) {
        LOG.entering(event);
        activeStyle = ((RadioMenuItem) event.getSource()).getId();
        changeStyle();
    }

    public void switchDebugLog(final ActionEvent actionEvent) {
        LOG.entering(actionEvent);
        if (((RadioMenuItem) actionEvent.getSource()).isSelected()) {
            Logger.setDebugLevel();
            LOG.debug("Debug Logging enabled");
        } else {
            LOG.debug("Debug Logging disabled");
            Logger.setInfoLevel();
        }
    }

    public void changeColor() {
        LOG.entering();
        setHighlightColor(convertToHex(color.getValue()));
    }

    public void exit() {
        LOG.entering();
        Platform.exit();
    }

    private void changeStyle() {
        LOG.entering();
        if (activeStyle.isEmpty() || Objects.equals(activeStyle, style_sharV.getId())) {
            setStyle("/css/sharV.css");
            style_modena.setSelected(false);
            style_sharV.setSelected(true);
        } else if (Objects.equals(activeStyle, style_modena.getId())) {
            setStyle(STYLESHEET_MODENA);
            style_modena.setSelected(true);
            style_sharV.setSelected(false);
        }
    }

    private void setText(final Menu menu, final String value) {
        menu.setText(ResourceBundle.getBundle("lang/menu").getString(value));
    }

    private void setText(final MenuItem menuItem, final String value) {
        menuItem.setText(ResourceBundle.getBundle("lang/menu").getString(value));
    }

    private void setStyle(final String style) {
        LOG.entering(style);
        root.getStylesheets().clear();
        if (style != null && !style.isEmpty()) {
            root.getStylesheets().add(style);
        }
        root.getStylesheets().add("/css/layouts.css");
        root.getStylesheets().add("/css/borderedTitled.css");
    }

    private void setHighlightColor(final String highlightColor) {
        LOG.entering(highlightColor);
        root.setStyle(String.format("-sharV-highlight: %s;", highlightColor));
        color.setValue(Color.valueOf(highlightColor));
    }

    private String convertToHex(Color color) {
        LOG.entering(color);
        return LOG.returning(
                String.format("#%02X%02X%02X",
                        (int) (color.getRed() * color.getOpacity() * 255),
                        (int) (color.getGreen() * color.getOpacity() * 255),
                        (int) (color.getBlue() * color.getOpacity() * 255))
        );

    }

    private void reload() throws IOException {
        LOG.entering();
        root.getScene().setRoot(getLoader().load());
    }

    private FXMLLoader getLoader() {
        LOG.entering();
        return LOG.returning(
                new FXMLLoader(
                        getClass().getResource("/fxml/sharv.fxml"),
                        new ResourceWrapper()
                )
        );
    }

    private static class ResourceWrapper extends ListResourceBundle {
        @Override
        protected Object[][] getContents() {
            return new Object[0][];
        }
    }

}
