package de.arindy.sharv.character;

import lombok.Data;

import java.util.Objects;

@Data
public class Metatype {

    private String name;

    protected Metatype() {
        this("");
    }

    public Metatype(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metatype metatype = (Metatype) o;
        return Objects.equals(name, metatype.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

}
