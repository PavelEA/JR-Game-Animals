package com.paul.model;

import com.paul.model.abstractClasses.Herbivore;

public class Duck extends Herbivore {
    public Duck() {
        super(13);
        minFertility =14;
        maxFertility = 20;
        emoji = "🦆";
    }
}
