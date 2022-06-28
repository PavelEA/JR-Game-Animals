package com.paul.model;

import com.paul.model.abstractClasses.Carnivore;

public class Fox extends Carnivore {
    public Fox() {
        super(2);
        minFertility =4;
        maxFertility = 12;
        emoji="🦊";
    }
}
