package de.arindy.sharv.persistence.character;

import de.arindy.sharv.persistence.mapper.Loadable;
import lombok.Data;

import java.util.Objects;

@Data
public class Metatype implements Loadable {

    private String source;
    private String id;
    private String name;

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
        return Objects.equals(id, metatype.id);
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
