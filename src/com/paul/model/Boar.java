package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Boar extends Herbivore {
    public Boar() {
        super(11);
        minFertility =5;
        maxFertility = 12;
        emoji = "🐗";
    }
}
