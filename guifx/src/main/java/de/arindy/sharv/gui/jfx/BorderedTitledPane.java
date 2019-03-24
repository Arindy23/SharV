package de.arindy.sharv.gui.jfx;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class BorderedTitledPane extends StackPane {

    private StringProperty title = new SimpleStringProperty();
    private ObjectProperty<Node> graphic = new SimpleObjectProperty<>();
    private ObjectProperty<Node> content = new SimpleObjectProperty<>();
    private ObjectProperty<Pos> titleAlignment = new SimpleObjectProperty<>();

    public BorderedTitledPane(final String paneName) {
        this();
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource(String.format("/fxml/%s.fxml", paneName)),
                ResourceBundle.getBundle(String.format("lang/%s", paneName))
        );
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public BorderedTitledPane() {
        this("", null);
    }

    public BorderedTitledPane(final String titleString, final Node contentNode) {
        final Label titleLabel = new Label();
        titleLabel.textProperty().bind(Bindings.concat(title, " "));
        titleLabel.getStyleClass().add("bordered-titled-title");
        titleLabel.graphicProperty().bind(graphic);

        titleAlignment.addListener(observable -> StackPane.setAlignment(titleLabel, titleAlignment.get()));

        final StackPane contentPane = new StackPane();

        getStyleClass().add("bordered-titled-border");
        getChildren().addAll(titleLabel, contentPane);

        content.addListener(observable -> {
            if (content.get() == null) {
                contentPane.getChildren().clear();
            } else {
                if (!content.get().getStyleClass().contains("bordered-titled-content")) {
                    content.get().getStyleClass().add("bordered-titled-content");
                }
                contentPane.getChildren().setAll(content.get());
            }
        });

        titleAlignment.set(Pos.TOP_LEFT);
        this.title.set(titleString);
        this.content.set(contentNode);
    }

    //<editor-fold desc="Getter/Setter">
    public String getTitle() {
        return title.get();
    }

    public StringProperty getTitleStringProperty() {
        return title;
    }

    public void setTitle(final String title) {
        this.title.set(title);
    }

    public Pos getTitleAlignment() {
        return titleAlignment.get();
    }

    public ObjectProperty<Pos> titleAlignmentProperty() {
        return titleAlignment;
    }

    public void setTitleAlignment(Pos titleAlignment) {
        this.titleAlignment.set(titleAlignment);
    }

    public Node getContent() {
        return content.get();
    }

    public ObjectProperty<Node> contentProperty() {
        return content;
    }

    public void setContent(Node content) {
        this.content.set(content);
    }

    public Node getGraphic() {
        return graphic.get();
    }

    public ObjectProperty<Node> graphicProperty() {
        return graphic;
    }

    public void setGraphic(Node graphic) {
        this.graphic.set(graphic);
    }
    //</editor-fold>

}
