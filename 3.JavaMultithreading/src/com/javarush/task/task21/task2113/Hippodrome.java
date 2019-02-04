package com.javarush.task.task21.task2113;

import java.util.*;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void move(){
        for (int i = 0; i < horses.size(); i++){
            horses.get(i).move();
        }
    }

    void print(){
        for (int i = 0; i < horses.size(); i++){
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++)
            System.out.println();
    }

    public Horse getWinner(){
        Horse winner = null;
        for (int i = 0; i < horses.size() - 1; i++){
            if (horses.get(i).distance < horses.get(i + 1).distance)
                winner = horses.get(i + 1);
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().name + "!");
    }

    public static void main(String[] args) {
        List<Horse> list = new ArrayList<>();
        list.add(new Horse("Lucky", 3, 0));
        list.add(new Horse("Speedy", 3, 0));
        list.add(new Horse("Chappy", 3, 0));
        game = new Hippodrome(list);
        game.run();
        game.printWinner();
    }
}
