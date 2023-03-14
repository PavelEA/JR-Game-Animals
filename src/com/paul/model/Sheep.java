package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Sheep extends Herbivore {
    public Sheep() {
        super(10);
        minFertility =3;
        maxFertility = 5;
        emoji ="🐑";
    }
}
