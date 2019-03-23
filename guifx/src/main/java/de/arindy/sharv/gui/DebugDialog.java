package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import de.arindy.sharv.gui.logging.TextAreaHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class DebugDialog {

    private final Logger LOG = Logger.get(getClass().getName());

    private final Stage stage;
    private TextAreaHandler logOutput;

    DebugDialog() {
        stage = new Stage();
        stage.setOnCloseRequest(windowEvent -> {
            LOG.info("Use Debug Toogle!");
            windowEvent.consume();
        });
        stage.setTitle("SharV-Debug");
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/debug.fxml"));
            final Parent parent = fxmlLoader.load();
            logOutput = new TextAreaHandler((TextArea) fxmlLoader.getNamespace().get("logOutput"));
            stage.setScene(new Scene(parent));
        } catch (IOException e) {
            LOG.exception(e.getMessage(), e);
        }
    }

    void show() {
        Logger.addHandler(logOutput);
        stage.show();
    }

    void hide() {
        Logger.removeHandler(logOutput);
        stage.hide();
    }

}
