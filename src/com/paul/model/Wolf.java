package com.paul.model;

import com.paul.model.abstractClasses.Carnivore;

public class Wolf extends Carnivore {

    public Wolf() {
        super(0);
        emoji ="\uD83D\uDC3A";
        minFertility = 2;
        maxFertility = 4;

    }
}
