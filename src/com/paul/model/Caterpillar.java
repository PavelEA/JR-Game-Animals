package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(14);
        minFertility =25;
        maxFertility = 75;
        emoji = "🐛";
    }
}
