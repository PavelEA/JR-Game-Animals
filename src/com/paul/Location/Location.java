package com.paul.Location;

import com.paul.model.abstractClasses.Animal;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    public static Location [][] islandMap;
    private static final int X_MAX = 3;
    private static final int Y_MAX = 3;
    int x;
    int y;
    volatile int grassCount;
    ConcurrentHashMap<Long, Animal> animalsMap = new ConcurrentHashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.grassCount = ThreadLocalRandom.current().nextInt(0, 201); //todo decrease/increase everywhere to correct value!!
    }

    public static void fillMap() {
        islandMap = new Location[X_MAX][Y_MAX];
        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                islandMap[i][j] = new Location(i,j);
            }
        }
    }



    public synchronized void addAnimal(Animal animal){
        animalsMap.put(animal.getUuid(), animal);
    }

    public synchronized void deleteAnimal(Animal animal){
                animalsMap.remove(animal.getUuid());

    }

    public boolean decreaseGrassCount() {
        synchronized (this){
            if (this.grassCount == 0){
                return false;
            }
            this.grassCount = this.grassCount-1;
            return true;
        }
    }

    public void increaseGrassCount() {
        synchronized (this) {
            this.grassCount = grassCount+10;
        }
    }

    public Collection<Animal> getAnimals(){
        return animalsMap.values();
    }

    public static Location getLocationForAnimalId(Long id) {
        Location location = null;
        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                    Animal animal = islandMap[i][j].animalsMap.get(id);
                System.out.println(animal);
                    if (!(animal == null)) {
                        Position position = animal.getPosition();
                        location = islandMap[position.getX()][position.getY()];
                    }
            }
        }
        return location;
    }


    public static Location getLocation(int x, int y) {
        return islandMap[x][y];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
