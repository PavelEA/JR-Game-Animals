package com.paul.statistic;

public class StatisticCollect {
    //    ALIVE("За этот день погибло животных - "),
//    DIE("Живых животных на острове - "),
//    LARGEST_POPULATION("За этот день родилось животных - "),
//    REPRODUCTION("Самая большая популяция животных - "),
//    SMALLEST_POPULATION("Самая маленькая популяция животных - ");

   private static volatile int evencount =0;

    public synchronized static void addEvent() {
        evencount = evencount+1;
    }

    public static int getEvencount() {
        return evencount;
    }
    public synchronized static void clear(){
        evencount = 0;
    }

    public synchronized static void addEvent1() {
        ;
    }
    public static int getStatistic() {

        return 0;
    }

}
