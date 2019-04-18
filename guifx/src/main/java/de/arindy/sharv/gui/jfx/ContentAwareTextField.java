package de.arindy.sharv.gui.jfx;

import de.arindy.sharv.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.InputMethodHighlight;
import javafx.scene.input.InputMethodTextRun;

import java.util.Collections;

public class ContentAwareTextField extends TextField {

    public final static int UNRESTRICTED_MAX_LENGTH = -1;
    public final static int MAX_INTEGER_VALUE = -1;
    public final static String UNRESTRICTED_REGEX = ".*";

    private StringProperty regex = new SimpleStringProperty();
    private IntegerProperty maxLength = new SimpleIntegerProperty();
    private IntegerProperty maxValue = new SimpleIntegerProperty();

    public ContentAwareTextField() {
        this(UNRESTRICTED_REGEX, UNRESTRICTED_MAX_LENGTH, MAX_INTEGER_VALUE);
    }

    public ContentAwareTextField(final String regex) {
        this(regex, UNRESTRICTED_MAX_LENGTH, MAX_INTEGER_VALUE);
    }

    public ContentAwareTextField(final Integer maxLength) {
        this(UNRESTRICTED_REGEX, maxLength, MAX_INTEGER_VALUE);
    }

    public ContentAwareTextField(final String regex, final Integer maxLength, final Integer maxValue) {
        this.regex.set(regex);
        this.maxLength.set(maxLength);
        this.maxValue.set(maxValue);
    }

    @Override
    public void replaceText(final int start, final int end, final String text) {
        final String oldText = currentText();
        final int oldCaretPosition = getCaretPosition();
        boolean changed = false;
        if (isInRange(end, text)) {
            super.replaceText(start, end, text);
            changed = true;
        }
        if (isUnrestricted() && isNotRegexConform()) {
            setText(oldText, oldCaretPosition);
            changed = false;
        } else if (isOverLimit(end) || isNotRegexConform() || isTooLong()) {
            setText(oldText, oldCaretPosition);
            changed = false;
        } else if (isOverMaxValue()) {
            setText(String.valueOf(getMaxValue()), getCaretPosition());
            changed = false;
        }
        if (changed) {
            fireEvent(currentText());
        }
    }

    private String currentText() {
        return getText(0, getLength());
    }

    private boolean isOverMaxValue() {
        boolean result;
        try {
            result = getMaxValue() > 0 && Integer.parseInt(currentText()) > getMaxValue();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    private void setText(final String text, final int caretPosition) {
        log().entering(text, caretPosition);
        super.replaceText(0, getLength(), text);
        positionCaret(caretPosition);
    }

    private void fireEvent(final String text) {
        log().entering(text);
        fireEvent(
                new InputMethodEvent(
                        InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                        Collections.singletonList(new InputMethodTextRun(text, InputMethodHighlight.UNSELECTED_RAW)),
                        getId(),
                        getCaretPosition())
        );
    }

    private boolean isTooLong() {
        return getLength() > getMaxLength();
    }

    private boolean isNotRegexConform() {
        return !currentText().matches(getRegex());
    }

    private boolean isOverLimit(int end) {
        return end > getMaxLength() || end < 0;
    }

    private boolean isInRange(int end, String text) {
        return end >= 0 && (isUnrestricted() || end < getMaxLength()) || text.isEmpty();
    }

    private boolean isUnrestricted() {
        return getMaxLength() < 0;
    }

    private void recalculateText() {
        replaceText(0, getLength(), currentText());
    }

    private Logger log() {
        return Logger.get(getClass().getName() + ":" + getId());
    }

    //<editor-fold desc="Getter/Setter">

    public String getRegex() {
        return regex.get();
    }

    public StringProperty regexProperty() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex.set(regex);
    }

    public int getMaxLength() {
        return maxLength.get();
    }

    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength.set(maxLength);
    }

    public int getMaxValue() {
        return maxValue.get();
    }

    public IntegerProperty maxValueProperty() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue.set(maxValue);
        recalculateText();
    }
    //</editor-fold>

}
