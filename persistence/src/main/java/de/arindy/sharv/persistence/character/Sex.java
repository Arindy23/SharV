package de.arindy.sharv.persistence.character;

import lombok.Data;

import java.util.Objects;

@Data
public class Sex {

    private String id;
    private String name;

    public Sex() {
        this("");
    }

    public Sex(final String id) {
        this.id = id;
        this.name = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sex sex = (Sex) o;
        return Objects.equals(id, sex.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }

}
