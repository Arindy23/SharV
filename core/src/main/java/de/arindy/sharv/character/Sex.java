package de.arindy.sharv.character;

import lombok.Data;

import java.util.Objects;

@Data
public class Sex {

    private String name;

    public Sex() {
        this("");
    }

    public Sex(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sex sex1 = (Sex) o;
        return Objects.equals(name, sex1.name);
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
