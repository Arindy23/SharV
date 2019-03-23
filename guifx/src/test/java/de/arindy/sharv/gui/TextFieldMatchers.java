package de.arindy.sharv.gui;

import javafx.scene.control.TextField;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.testfx.matcher.base.GeneralMatchers;

import java.util.Objects;

public class TextFieldMatchers {

    private TextFieldMatchers() {
    }

    @Factory
    static Matcher<TextField> hasText(final String text) {
        String descriptionText = "has text \"" + text + "\"";
        return GeneralMatchers.typeSafeMatcher(
                TextField.class,
                descriptionText,
                (textNode) -> textNode.getClass().getSimpleName() + " with text: \"" + textNode.getText() + "\"",
                (textNode) -> Objects.equals(text, textNode.getText())
        );
    }

}
