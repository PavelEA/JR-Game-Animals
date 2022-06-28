package com.paul.model.abstractClasses;

import com.paul.Config.Utilities;
import com.paul.Location.Location;
import com.paul.Location.Position;
import com.paul.settings.AnimalFactory;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.paul.Location.Location.islandMap;


public abstract class Animal implements Runnable {
    public String emoji ;

    public String getEmoji(){
        return emoji;
    }


    private final long uuid;
    private boolean alive;
    private int dayForDead;
    private final int groupId;
    private final int [] readyForEat;
    private Position position;

    private final double weight;
    private final double maxNumOfAnimalsOnOneCage;
    private final double travelSpeed;
    private final double kilogramsOfFullSaturation;

    private double currentSaturation;

    private boolean readyForReproduction;
    public int minFertility;
    public int maxFertility;

    public int getGroupId(){
        return groupId;
    }

    public int getMinFertility(){
        return minFertility;
    }

    public int getMaxFertility(){
        return maxFertility;
    }

    public boolean getReadyForReproduction() {
        return readyForReproduction;
    }
    public void setReadyForReproduction(boolean readyForReproduction) {
        this.readyForReproduction = readyForReproduction;
    }


    public Animal(int groupId){
        this.groupId=groupId;
        uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        position = new Position();
        Location.getLocation(getPosition().getX(), getPosition().getY()).addAnimal(this);
        double[] characteristic  = Utilities.getCharacterById(groupId);
        weight = characteristic[0];
        maxNumOfAnimalsOnOneCage = characteristic[1];
        travelSpeed = characteristic[2];
        kilogramsOfFullSaturation = characteristic[3];
        readyForReproduction = false;
        readyForEat = Utilities.getEatForId(groupId);
        dayForDead = 3;
        alive = true;
        currentSaturation = characteristic[3];
    }

    private List<Animal> getPossibleAnimalsToEat() {
        Collection<Animal> animalList = getLocation().getAnimals();

        List<Integer> typesForEat = new ArrayList<>();
        for (int i =0;i<readyForEat.length;i++){
            if (readyForEat[i]!= 0){
                typesForEat.add(i);
            }
        }

        List<Animal> animalsToEat = new ArrayList<>();
        for(Animal animal:animalList){
            if (typesForEat.contains(animal.getGroupId()) && animal.isAlive() && animal.getUuid() != uuid){
                animalsToEat.add(animal);
            }
        }

        return animalsToEat;
    }

    private boolean isAlive() {
        return alive;
    }

    public void eat() {
        if (currentSaturation < kilogramsOfFullSaturation) {
            int[] whoCanIEat = getReadyForEat();

            List<Animal> possibleToEat = getPossibleAnimalsToEat();

            if (possibleToEat.isEmpty()) {
                return;
            }

            int luckNumber = ThreadLocalRandom.current().nextInt(0, possibleToEat.size());

            Animal target = possibleToEat.get(luckNumber);

            int probabilityToEat = whoCanIEat[target.getGroupId()];

            int wishMeLuck = ThreadLocalRandom.current().nextInt(0, 101);

            if (wishMeLuck < probabilityToEat) {
                currentSaturation = saturation(target);
                target.die();
            } else {
                if (canEatGrass()) {
                    if (getLocation().decreaseGrassCount()){
                        saturation(1);
                    }
                    return;
                }
                hunger();
            }
        }
     else {
        hunger();
    }}

    public void hunger(){
        double hunger = currentSaturation;
        double increasedHunger = hunger/100*35;
        hunger -= increasedHunger;
        if (hunger<0){
            hunger = 0;
        }
        currentSaturation = hunger;
    }
    public boolean canEatGrass() {
        return false;
    }

    private Location getLocation(){
        return Location.getLocation(this.getPosition().getX(), this.getPosition().getY());
    }

    public void die(){
        getLocation().deleteAnimal(this);
        alive = false;

    }

    public void reproduction() {
        if (getReadyForReproduction()) {
            Location locationWhereReproduction = getLocation();
            Collection<Animal> targetForReproduction = locationWhereReproduction.getAnimals();
            for (Animal findPartner : targetForReproduction) {
                if (getGroupId() == findPartner.getGroupId() && findPartner.getUuid() != getUuid() && findPartner.isAlive()) {
                    if (findPartner.getReadyForReproduction()) {
                        setReadyForReproduction(false);
                        findPartner.setReadyForReproduction(false);
                        int luckNumber = ThreadLocalRandom.current().nextInt(getMinFertility(), getMaxFertility() + 1);
                        for (int j = 0; j < luckNumber; j++) {
                            Animal child = new Animal(getGroupId()) {
                                @Override
                                public String getEmoji() {
                                    return emoji;
                                }

                                @Override
                                public int getGroupId() {
                                    return groupId;
                                }

                                @Override
                                public int getMinFertility() {
                                    return minFertility;
                                }

                                @Override
                                public int getMaxFertility() {
                                    return maxFertility;
                                }
                            };
                            getLocation().addAnimal(child);
                            Thread thread = new Thread(child); //todo define logic in the separate class
                            thread.start();
                        }
                        return;
                    }
                }

            }
        }
    }

    public void move() {
        Position positionNow = getPosition();
        int canMove = ThreadLocalRandom.current().nextInt(0, (int) maxNumOfAnimalsOnOneCage + 1);
        for (int i = 0; i < canMove; i++) {
            int whichSide = ThreadLocalRandom.current().nextInt(0, 5);
            switch (whichSide) {
                case 1 -> {
                    if (positionNow.getY() == 0) {
//                        System.out.println("Влево не могу! Дальше только в бездну");
                    } else {
//                        System.out.println("Иду влево");
                        getLocation().deleteAnimal(this);
                        positionNow = new Position(positionNow.getX(), positionNow.getY() - 1);
                        setPosition(positionNow);
                        getLocation().addAnimal(this);
                    }
                }
                case 2 -> {
                    if (positionNow.getX() == 0) {
//                        System.out.println("Вверх не могу! Дальше только в бездну");
                    } else {
//                        System.out.println("Иду вверх");
                        getLocation().deleteAnimal(this);
                        positionNow = new Position(positionNow.getX() - 1, positionNow.getY());
                        setPosition(positionNow);
                        getLocation().addAnimal(this);
                    }
                }
                case 3 -> {
                    if (positionNow.getY() == islandMap[0].length - 1) {
//                        System.out.println("Вправо не могу! Дальше только в бездну");
                    } else {
//                        System.out.println("Иду вправо");
                        getLocation().deleteAnimal(this);
                        positionNow = new Position(positionNow.getX(), positionNow.getY() + 1);
                        setPosition(positionNow);
                        getLocation().addAnimal(this);
                    }
                }
                case 4 -> {
                    if (positionNow.getX() == islandMap.length - 1) {
//                        System.out.println("Вниз не могу! Дальше только в бездну");
                    } else {
//                        System.out.println("Иду вниз");
                        getLocation().deleteAnimal(this);
                        positionNow = new Position(positionNow.getX() + 1, positionNow.getY());
                        setPosition(positionNow);
                        getLocation().addAnimal(this);
                    }
                }
            }
            setReadyForReproduction(true);
        }
    }

    public double saturation(Animal deadAnimal){
        return saturation(deadAnimal.getWeight());
    }

    public double saturation(double kg){
        double saturation =  currentSaturation + kg;
        if (saturation>kilogramsOfFullSaturation){
            saturation = kilogramsOfFullSaturation;
        }
        return saturation;
    }

    public void loadDayForDead(Animal animal){
        animal.dayForDead-=1;
        if (dayForDead == 0){
            die();
        }
    }

    public static void display() {
        Collection<Animal> targetForDraw;
        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                System.out.print("[ ");
                targetForDraw = islandMap[i][j].getAnimals();

                Map<Integer, Long> collect = targetForDraw.stream().collect(Collectors.groupingBy(Animal::getGroupId, Collectors.counting()));
                for (Long a :collect.values()) {
                    if (a > 20){
                        Set<Map.Entry<Integer, Long>> entries = collect.entrySet();
                        for (Map.Entry<Integer,Long> pair : entries) {
                            if (a == pair.getValue()){
                                for (Animal findAnimal : targetForDraw){
                                  System.out.print(findAnimal.getEmoji() + " ");
                                    //System.out.print(findAnimal.getGroupId());
                                    break;
                                }
                            }
                        }
                    }
                }System.out.print("]");

            }
            System.out.println();
        }
    }

    public int[] getReadyForEat() {
        return this.readyForEat;
    }
    public double getWeight() {
        return this.weight;
    }
    public double getMaxNumOfAnimalsOnOneCage() {
        return this.maxNumOfAnimalsOnOneCage;
    }
    public double getTravelSpeed() {
        return this.travelSpeed;
    }
    public double getKilogramsOfFullSaturation() {
        return this.kilogramsOfFullSaturation;
    }
    public long getUuid() {
        return uuid;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }

    public void action(){
        //    StatisticCollect.addEvent();
        move();
        eat();
        reproduction();
    }

    @Override
    public void run() {
        while(true){
            if (!isAlive()){
                return;
            }
            action();
            synchronized (AnimalFactory.mutex){

                try {
                    AnimalFactory.mutex.wait();
                } catch (InterruptedException e) {
                    System.out.println("You died");
                }
            }
        }
    }
}
