package de.arindy.sharv.gui.jfx;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public abstract class CheckBoxPane extends GridPane {

    private IntegerProperty amount = new SimpleIntegerProperty();
    private IntegerProperty checkedAmount = new SimpleIntegerProperty();
    private StringProperty identifier = new SimpleStringProperty(UUID.randomUUID().toString());
    private ObjectProperty<EventHandler<ActionEvent>> onAction = new SimpleObjectProperty<>();

    public final void initialize() {
        for (CheckBox checkBox : getCheckBoxes()) {
            checkBox.setOnAction(this::checkBoxClicked);
        }
    }

    private void checkBoxClicked(final ActionEvent actionEvent) {
        onAction.getValue().handle(actionEvent);
    }

    //<editor-fold desc="Getter/Setter">
    public int extractIndex(final CheckBox source) {
        return extractIntegerFromId(source.getId()) - (source.isSelected() ? 0 : 1);
    }

    public String getIdentifier() {
        return identifier.get();
    }

    public StringProperty getIdentifierStringProperty() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier.set(identifier);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty getAmountIntegerProperty() {
        return amount;
    }

    public void setAmount(final int amount) {
        getChildren().clear();
        for (int i = 1; i <= amount; i++) {
            final CheckBox checkBox = new CheckBox("");
            checkBox.setId(createId(i));
            checkBox.setOnAction(this::checkBoxClicked);
            add(checkBox, calculateColumn(i), calculateRow(i));
        }
        this.amount.set(amount);
        setCheckedAmount(getCheckedAmount());
    }

    public int getCheckedAmount() {
        return checkedAmount.get();
    }

    public IntegerProperty getCheckedAmountIntegerProperty() {
        return checkedAmount;
    }

    public void setCheckedAmount(final int checkedAmount) {
        for (CheckBox edge : getCheckBoxes()) {
            edge.setSelected(checkedAmount >= extractIntegerFromId(edge.getId()));
        }
        this.checkedAmount.set(checkedAmount);
    }

    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return this.onAction;
    }

    public final void setOnAction(final EventHandler<ActionEvent> value) {
        this.onActionProperty().set(value);
    }

    public final EventHandler<ActionEvent> getOnAction() {
        return this.onActionProperty().get();
    }
    //</editor-fold>

    public Collection<CheckBox> getCheckBoxes() {
        final Collection<CheckBox> result = new HashSet<>();
        for (Node node : getChildren()) {
            if (node instanceof CheckBox) {
                result.add((CheckBox) node);
            }
        }
        return result;
    }

    public abstract int calculateColumn(final int i);

    public abstract int calculateRow(final int i);

    private String createId(final int i) {
        return String.format("%s#%d", getIdentifier(), i);
    }

    private int extractIntegerFromId(final String id) {
        int result;
        try {
            String[] split = id == null ? new String[0] : id.split("#");
            if (split.length == 2) {
                result = Integer.parseInt(split[1]);
            } else {
                result = 0;
            }
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }

}
