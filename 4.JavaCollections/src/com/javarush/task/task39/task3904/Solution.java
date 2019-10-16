package com.javarush.task.task39.task3904;

/*
Лестница
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) return 0;
        long t1 = 0, t2 = 0, t3 = 0;
        for (int k = 0; k <= n; k++){
            if (t3 == 0) t3++;
            else {
                long tmp = t1 + t2 + t3;
                t1 = t2;
                t2 = t3;
                t3 = tmp;
            }
        }
        return t3;
    }
}

