package de.arindy.sharv.gui.logging;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TextAreaHandler extends Handler {

    private final TextArea area;

    public TextAreaHandler(final TextArea area) {
        this.area = Objects.requireNonNull(area);
    }

    @Override
    public void publish(LogRecord logRecord) {
        if (super.isLoggable(logRecord)) {
            final String message = this.getFormatter().format(logRecord);
            Platform.runLater(() -> {
                if (area.getText().length() == 0) {
                    area.setText(message);
                } else {
                    area.selectEnd();
                    area.insertText(area.getText().length(),
                            message);
                }
            });
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

}
