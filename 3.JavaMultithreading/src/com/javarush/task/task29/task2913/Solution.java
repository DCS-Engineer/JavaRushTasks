package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        if (a > b) {
            StringBuilder sb = new StringBuilder();
            for (int i = a; i >= b; i--){
                if (i == b){
                    sb.append(i);
                    return sb.toString();
                }
                sb.append(i + " ");
            }
        }
        else if (a < b) {
            StringBuilder sb = new StringBuilder();
            for (int i = a; i <= b; i++){
                if (i == b){
                    sb.append(i);
                    return sb.toString();
                }
                sb.append(i + " ");
            }
        }
        return Integer.toString(a);
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}
