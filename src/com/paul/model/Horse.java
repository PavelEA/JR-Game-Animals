package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Horse extends Herbivore {
    public Horse() {
        super(5);
        minFertility =1;
        maxFertility = 2;
        emoji = "🐎";
    }
}
