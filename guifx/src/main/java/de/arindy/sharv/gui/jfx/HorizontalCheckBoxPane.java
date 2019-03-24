package de.arindy.sharv.gui.jfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HorizontalCheckBoxPane extends CheckBoxPane {

    private IntegerProperty maxRows = new SimpleIntegerProperty();

    public Integer getMaxRows() {
        return maxRows.get();
    }

    public IntegerProperty getMaxRowsIntegerProperty() {
        return maxRows;
    }

    public void setMaxRows(final Integer maxRows) {
        this.maxRows.set(maxRows);
    }

    public int calculateColumn(final int i) {
        return (i - 1) / getMaxRows();
    }

    public int calculateRow(final int i) {
        return (i - 1) % getMaxRows();
    }
}
