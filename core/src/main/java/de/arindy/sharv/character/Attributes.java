package de.arindy.sharv.character;

import lombok.Data;

@Data
public class Attributes {

    private PhysicalAttributes physical;
    private MentalAttributes mental;

    public Attributes() {
        physical = new PhysicalAttributes();
        mental = new MentalAttributes();
    }

}
