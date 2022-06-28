package com.paul.DialogForUser;


import java.util.Scanner;

public class Dialog {
   public static void hello() {
        System.out.println(" Добро пожаловать в игру! \n " +
                "Хотите ли вы изменить настройки игры: \n Да ( нажмите 1 ) \n Нет (Нажмите 2)\n 0 для выхода");
        int answer = new Scanner(System.in).nextInt();
        switch (answer){
            case 1 -> nextStep();
            case 2 -> nextStep1();
            case 0 -> nextStep2();
            default -> hello();
        }


    }
    static void nextStep(){
        System.out.println("Это следующий шаг ");
    }
    static void nextStep1(){
        System.out.println("Это следующий шаг 1 ");
    }
    static void nextStep2(){
       System.out.println("Выходим");}
}
