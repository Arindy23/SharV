package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_MODENA;

public class SharVMenu extends HBox {

    private final Logger LOG;

    private DebugDialog debugDialog;

    //<editor-fold desc="Menu">
    @FXML
    private Menu file;
    @FXML
    private MenuItem close;
    @FXML
    private Menu characterMenu;
    @FXML
    private MenuItem characterLoad;
    @FXML
    private MenuItem characterSave;
    @FXML
    private Menu control;
    @FXML
    private MenuItem controlQualities;
    @FXML
    private Menu options;
    @FXML
    private Menu language;
    @FXML
    private Menu style;
    @FXML
    private RadioMenuItem lang_de;
    @FXML
    private RadioMenuItem lang_en;
    @FXML
    private RadioMenuItem style_sharV;
    @FXML
    private RadioMenuItem style_modena;
    @FXML
    private RadioMenuItem debugLogging;
    @FXML
    private ColorPicker color;
    //</editor-fold>

    public SharVMenu() {
        LOG = Logger.get(getClass().getName());
        final FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/fxml/menu.fxml"),
                ResourceBundle.getBundle("lang/menu")
        );
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        debugDialog = new DebugDialog();
    }

    public void initialize() {
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
        changeStyle(((RadioMenuItem) event.getSource()).getId());
    }

    public void switchDebugLog(final ActionEvent actionEvent) {
        LOG.entering(actionEvent);
        if (((RadioMenuItem) actionEvent.getSource()).isSelected()) {
            Logger.setDebugLevel();
            debugDialog.show();
        } else {
            Logger.setInfoLevel();
            debugDialog.hide();
        }
    }

    public void changeColor() {
        LOG.entering();
        setHighlightColor(convertToHex(color.getValue()));
    }

    public void exit() {
        new ExitAction().perform();
    }

    void changeStyle(final String activeStyle) {
        LOG.entering();
        if (activeStyle.isEmpty() || Objects.equals(activeStyle, style_sharV.getId())) {
            setStyleSheet("/css/sharV.css");
            style_modena.setSelected(false);
            style_sharV.setSelected(true);
        } else if (Objects.equals(activeStyle, style_modena.getId())) {
            setStyleSheet(STYLESHEET_MODENA);
            style_modena.setSelected(true);
            style_sharV.setSelected(false);
        }
    }

    private void reload() throws IOException {
        LOG.entering();
        debugDialog.hide();
        getParent().getScene().setRoot(SharVGUI.getLoader().load());
    }

    private void setStyleSheet(final String style) {
        LOG.entering(style);
        getParent().getStylesheets().clear();
        if (style != null && !style.isEmpty()) {
            getParent().getStylesheets().add(style);
        }
        getParent().getStylesheets().add("/css/layouts.css");
        getParent().getStylesheets().add("/css/borderedTitled.css");
    }

    void setHighlightColor(final String highlightColor) {
        LOG.entering(highlightColor);
        getParent().setStyle(String.format("-sharV-highlight: %s;", highlightColor));
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

}
