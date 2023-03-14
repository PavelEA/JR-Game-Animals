package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Goat extends Herbivore {
    public Goat() {
        super(9);
        minFertility =1;
        maxFertility = 3;
        emoji = "🐐";
    }
}
