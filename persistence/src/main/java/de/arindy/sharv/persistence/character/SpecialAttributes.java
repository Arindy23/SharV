package de.arindy.sharv.persistence.character;

import lombok.Data;

@Data
public class SpecialAttributes {

    private Special special;
    private int specialValue;
    private int edge;
    private int burnedEdge;

}
