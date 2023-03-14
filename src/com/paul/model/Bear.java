package com.paul.model;

import com.paul.model.abstractClasses.Carnivore;


public class Bear extends Carnivore {
    public Bear() {
        super(2);
        maxFertility = 5;
        minFertility = 2;
        emoji = "🐻";
    }
}
