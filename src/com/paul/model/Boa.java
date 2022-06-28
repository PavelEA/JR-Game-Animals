package com.paul.model;
import com.paul.model.abstractClasses.Carnivore;


public class Boa extends Carnivore {
    public Boa() {
        super(1);
        minFertility =10;
        maxFertility = 12;
        emoji = "🐍";
    }
}
