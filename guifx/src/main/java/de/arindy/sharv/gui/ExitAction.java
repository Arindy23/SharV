package de.arindy.sharv.gui;

import de.arindy.sharv.Logger;
import javafx.application.Platform;

public class ExitAction {

    public void perform() {
        Logger.get(getClass().getName()).info("Shutting down");
        Platform.exit();
    }

}
