package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Deer extends Herbivore {

    public Deer() {
        super(6);
        minFertility =1;
        maxFertility = 2;
        emoji = "🦌";
    }
}
