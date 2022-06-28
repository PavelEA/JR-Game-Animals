package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(7);
        minFertility = 5;
        maxFertility = 9;
        emoji = "🐇";
    }


}
