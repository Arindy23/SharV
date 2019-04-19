package de.arindy.sharv.character;

import lombok.Data;

import java.util.Objects;

@Data
public class Special {

    private String name;

    public Special() {
        this("");
    }

    public Special(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return Objects.equals(getName(), special.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
