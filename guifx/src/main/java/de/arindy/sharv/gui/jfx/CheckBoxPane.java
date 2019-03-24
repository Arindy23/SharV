package de.arindy.sharv.gui.jfx;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class CheckBoxPane extends GridPane {

    private IntegerProperty maxRows = new SimpleIntegerProperty();
    private IntegerProperty amount = new SimpleIntegerProperty();
    private IntegerProperty checkedAmount = new SimpleIntegerProperty();
    private StringProperty identifier = new SimpleStringProperty();
    private ObjectProperty<EventHandler<ActionEvent>> onAction = new SimpleObjectProperty<>();

    //<editor-fold desc="Getter/Setter">
    public int extractIndex(final CheckBox source) {
        return extractIntegerFromId(source.getId()) - (source.isSelected() ? 0 : 1);
    }

    public Integer getMaxRows() {
        return maxRows.get();
    }

    public IntegerProperty getMaxRowsIntegerProperty() {
        return maxRows;
    }

    public void setMaxRows(final Integer maxRows) {
        this.maxRows.set(maxRows);
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
            checkBox.setOnAction(onAction.getValue());
            add(checkBox, calculateColumn(i), calculateRow(i));
        }
        this.amount.set(amount);
    }

    public int getCheckedAmount() {
        return checkedAmount.get();
    }

    public IntegerProperty getCheckedAmountIntegerProperty() {
        return checkedAmount;
    }

    public void setCheckedAmount(final int checkedAmount) {
        for (Node node : getChildren()) {
            if (node instanceof CheckBox) {
                final CheckBox edge = ((CheckBox) node);
                edge.setSelected(checkedAmount >= extractIntegerFromId(edge.getId()));
            }
        }
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

    private int calculateColumn(final int i) {
        return (i - 1) % getMaxRows();
    }

    private int calculateRow(final int i) {
        return (i - 1) / getMaxRows();
    }

    private String createId(final int i) {
        return String.format("%s%d", getIdentifier(), i);
    }

    private int extractIntegerFromId(final String id) {
        int result;
        try {
            result = Integer.parseInt(id.replace(getIdentifier(), ""));
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }

}
