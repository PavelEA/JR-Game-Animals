package com.paul.model;

import com.paul.model.abstractClasses.Carnivore;

public class Eagle extends Carnivore {
    public Eagle() {
        super(4);
        minFertility =2;
        maxFertility = 4;
        emoji = "\uD83E\uDD85";
    }
}
