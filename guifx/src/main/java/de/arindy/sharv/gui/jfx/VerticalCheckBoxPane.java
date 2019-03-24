package de.arindy.sharv.gui.jfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class VerticalCheckBoxPane extends CheckBoxPane {

    private IntegerProperty maxColumns = new SimpleIntegerProperty();

    public Integer getMaxColumns() {
        return maxColumns.get();
    }

    public IntegerProperty getMaxColumnsIntegerProperty() {
        return maxColumns;
    }

    public void setMaxColumns(final Integer maxColumns) {
        this.maxColumns.set(maxColumns);
    }

    public int calculateColumn(final int i) {
        return (i - 1) % getMaxColumns();
    }

    public int calculateRow(final int i) {
        return (i - 1) / getMaxColumns();
    }
}
