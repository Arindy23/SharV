package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.api.gui.DefaultMenuListener;
import de.arindy.sharv.api.gui.MenuListener;
import de.arindy.sharv.api.gui.MenuView;
import de.arindy.sharv.api.gui.Reloadable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import static javafx.application.Application.STYLESHEET_MODENA;

@Singleton
public class SharVMenu implements MenuView {

    private final Logger LOG;

    private DebugDialog debugDialog;
    private Reloadable reloadable;

    @FXML
    private HBox root;

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
    private Menu modules;
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
    private MenuListener menuListener;
    private PersistAction persistAction;
    private ExitAction exitAction;
    //</editor-fold>

    public SharVMenu() {
        LOG = Logger.get(getClass().getName());
        debugDialog = new DebugDialog();
        menuListener = new DefaultMenuListener();
    }

    @Inject
    public SharVMenu withMenuListener(final MenuListener menuListener) {
        this.menuListener = menuListener.register(this);
        return this;
    }

    @Inject
    public SharVMenu withPersistAction(final PersistAction persistAction) {
        this.persistAction = persistAction;
        return this;
    }

    @Inject
    public SharVMenu withExitAction(final ExitAction exitAction) {
        this.exitAction = exitAction;
        return this;
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
        menuListener.initializeMenu();
    }

    public void loadCharacter() {
        LOG.entering();
        persistAction.loadCharacter();
    }

    public void saveCharacter() {
        LOG.entering();
        persistAction.saveCharacter();
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
        final String highlightColor = convertToHex(color.getValue());
        setHighlightColor(highlightColor);
        menuListener.changeColor(highlightColor);
    }

    public void exit() {
        exitAction.perform(new WindowEvent(root.getParent().getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
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

    private void reload() {
        LOG.entering();
        debugDialog.hide();
        reloadable.reload();
    }

    private void setStyleSheet(final String style) {
        LOG.entering(style);
        root.getParent().getStylesheets().clear();
        if (style != null && !style.isEmpty()) {
            root.getParent().getStylesheets().add(style);
        }
        root.getParent().getStylesheets().add("/css/layouts.css");
        root.getParent().getStylesheets().add("/css/borderedTitled.css");
    }

    @Override
    public MenuView setHighlightColor(final String highlightColor) {
        LOG.entering(highlightColor);
        if (highlightColor != null && !highlightColor.isEmpty()) {
            root.getParent().setStyle(String.format("-sharV-highlight: %s;", highlightColor));
            color.setValue(Color.valueOf(highlightColor));
        }
        return this;
    }

    public void onModule(final ActionEvent event) {
        LOG.entering(event);
        RadioMenuItem source = (RadioMenuItem) event.getSource();
        if (source.isSelected()) {
            menuListener.activateModule(source.getText());
        } else {
            menuListener.deactivateModule(source.getText());
        }
    }

    @Override
    public MenuView addAvailableModules(final String... modules) {
        for (final String moduleName : FXCollections.observableArrayList(modules).sorted()) {
            final RadioMenuItem module = new RadioMenuItem(moduleName);
            module.setOnAction(this::onModule);
            this.modules.getItems().add(module);
        }
        return this;
    }

    @Override
    public MenuView setActivatedModules(final String... modules) {
        this.modules.getItems().forEach(menuItem -> ((RadioMenuItem) menuItem).setSelected(false));
        this.modules.getItems().forEach(menuItem -> {
                    for (String module : modules) {
                        if (Objects.equals(menuItem.getText(), module)) {
                            ((RadioMenuItem) menuItem).setSelected(true);
                        }
                    }
                }
        );
        return this;
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

    @Override
    public void setReloadable(final Reloadable reloadable) {
        this.reloadable = reloadable;
    }

}
