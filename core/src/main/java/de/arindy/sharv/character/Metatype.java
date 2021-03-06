package de.arindy.sharv.character;

import de.arindy.sharv.IdFormatter;
import lombok.Data;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

@Data
public class Metatype implements Formattable {

    private String source;
    private String id;
    private String name;
    private int bodyMax;

    public Metatype() {
        this("");
    }

    public Metatype(final String id) {
        this.id = id;
        this.name = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metatype metatype = (Metatype) o;
        return Objects.equals(getId(), metatype.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void formatTo(Formatter formatter, int f, int width, int precision) {
        new IdFormatter(formatter, f, width, precision).formatTo(name, id);
    }

}
