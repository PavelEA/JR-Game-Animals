package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Mouse extends Herbivore {
    public Mouse() {
        super(8);
        minFertility =5;
        maxFertility = 14;
        emoji = "🐁";
    }
}
