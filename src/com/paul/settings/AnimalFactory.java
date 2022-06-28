package com.paul.settings;

import com.paul.Location.Location;
import com.paul.model.*;
import com.paul.model.abstractClasses.Animal;

import java.util.ArrayList;
import java.util.List;

import static com.paul.model.abstractClasses.Animal.display;

public class AnimalFactory {
    public static final Object mutex = new Object();

    public static void main(String[] args) {



        Location.fillMap();

        List<Animal> animals = new ArrayList<>();//todo define logic in the class
        for (int i = 0; i < 2 ; i++) {
            animals.add(new Bear());
        }
        for (int i = 0; i < 50 ; i++) {
            animals.add(new Wolf());
        }
//        for (int i = 0; i < 10 ; i++) {
//            animals.add(new Boa());
//        }
//        for (int i = 0; i < 10 ; i++) {
//            animals.add(new Fox());
//        }
//        for (int i = 0; i < 8 ; i++) {
//            animals.add(new Eagle());
//        }
//        for (int i = 0; i < 8 ; i++) {
//            animals.add(new Horse());
//        }
//        for (int i = 0; i < 8 ; i++) {
//            animals.add(new Deer());
//        }
//        for (int i = 0; i < 50 ; i++) {
//            animals.add(new Rabbit());
//        }
////        for (int i = 0; i < 100 ; i++) {
////            animals.add(new Mouse());
////        }
//        for (int i = 0; i < 40 ; i++) {
//            animals.add(new Goat());
//        }
//        for (int i = 0; i < 8 ; i++) {
//            animals.add(new Sheep());
//        }
//        for (int i = 0; i < 20 ; i++) {
//            animals.add(new Boar());
//        }
//        for (int i = 0; i < 3 ; i++) {
//            animals.add(new Buffalo());
//        }
//        for (int i = 0; i < 50 ; i++) {
//            animals.add(new Duck());
//        }
//        for (int i = 0; i < 100 ; i++) {
//            animals.add(new Caterpillar());
//        }


        for(Animal animal: animals){
        Thread thread = new Thread(animal);
        thread.start();
    }

        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         display();
//            Location.getLocation().increaseGrassCount();
//            System.out.println(" Increase Grass + 10 ");
//           System.out.println("event count "+ StatisticCollect.getEvencount());//todo organize the logic of collecting and displaying statistics
        synchronized (mutex){
            mutex.notifyAll();
        }
//        StatisticCollect.clear();
    }

    }
}
