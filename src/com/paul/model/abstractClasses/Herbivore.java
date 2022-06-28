package com.paul.model.abstractClasses;

public abstract class Herbivore extends Animal {
    public Herbivore(int id) {
        super(id);
    }

    @Override
    public boolean canEatGrass() {
        return true;
    }
}
