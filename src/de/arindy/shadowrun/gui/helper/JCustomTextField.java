package de.arindy.shadowrun.gui.helper;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JCustomTextField extends JTextField {

    private static final long serialVersionUID = 1L;
    private int maxLength = -1;
    //default regex, accept everything
    private String regexCheck = ".*";
    private boolean isBlocked = false;

    public JCustomTextField() {
        super();
        getDocument().putProperty("owner", this);
    }

    @Override
    protected Document createDefaultModel() {
        return new RegexDocument();
    }

    public void setMaximumLength(int max) {
        maxLength = max;
        this.setColumns(max);
    }

    public void setRegexFilter(String regex) {
        regexCheck = regex;
    }

    private final class RegexDocument extends PlainDocument {

        private static final long serialVersionUID = 1L;

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            int caretPosition = getCaretPosition();
            String tempString = getText(0, getLength());

            if (!isBlocked) {
                if (offs < maxLength && offs >= 0) {
                    if (str.equals("(") || str.equals(")")) {
                        str = "()";

                    }
                    super.insertString(offs, str, a);
                    setCaretPosition(caretPosition + 1);
                }

                if (maxLength < 0) {
                    if (!getText(0, getLength()).matches(regexCheck)) {
                        super.remove(0, getLength());
                        super.insertString(0, tempString, a);
                        setCaretPosition(caretPosition);
                    }
                } else {
                    if (offs >= maxLength || offs < 0
                            || !getText(0, getLength()).matches(regexCheck)
                            || getLength() > maxLength) {
                        super.remove(0, getLength());
                        super.insertString(0, tempString, a);
                        setCaretPosition(caretPosition);
                    }
                }
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            int caretPosition = getCaretPosition();
            String tempString = getText(0, getLength());

            if (!isBlocked) {
                super.remove(offs, len);

                if (maxLength < 0) {
                    if (!getText(0, getLength()).matches(regexCheck)) {
                        super.remove(0, getLength());
                        super.insertString(0, tempString, null);
                        setCaretPosition(caretPosition);
                    }
                } else {
                    if (!getText(0, getLength()).matches(regexCheck)
                            || getLength() > maxLength) {
                        super.remove(0, getLength());
                        super.insertString(0, tempString, null);
                        setCaretPosition(caretPosition);
                    }
                }
            }
        }
    }
}