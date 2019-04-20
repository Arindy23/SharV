package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.controller.DefaultSaveGuard;
import de.arindy.sharv.controller.SaveGuard;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javax.inject.Inject;
import java.util.ResourceBundle;

public class ExitAction {

    private SaveGuard saveGuard;
    private PersistAction persistAction;

    public ExitAction() {
        this.saveGuard = new DefaultSaveGuard();
        this.persistAction = new PersistAction();
    }

    public void perform(WindowEvent event) {
        if (saveGuard.shouldSave()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ResourceBundle bundle = ResourceBundle.getBundle("lang/save");
            alert.setTitle(bundle.getString("title"));
            alert.setHeaderText(bundle.getString("title"));
            alert.setContentText(bundle.getString("text"));
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait().ifPresent(type -> {
                if (type == ButtonType.YES) {
                    if (persistAction.saveCharacter()) {
                        shutdown();
                    } else {
                        event.consume();
                    }
                } else if (type == ButtonType.NO) {
                    shutdown();
                } else {
                    event.consume();
                }
            });
        }
    }

    private void shutdown() {
        Logger.get(getClass().getName()).info("Shutting down");
        Platform.exit();
    }

    @Inject
    public ExitAction withSaveGuard(final SaveGuard saveGuard) {
        this.saveGuard = saveGuard;
        return this;
    }

    @Inject
    public ExitAction withPersistAction(final PersistAction persistAction) {
        this.persistAction = persistAction;
        return this;
    }

}
