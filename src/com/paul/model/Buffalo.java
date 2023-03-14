package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(12);
        minFertility =1;
        maxFertility = 2;
        emoji = "\uD83D\uDC03";
    }
}
