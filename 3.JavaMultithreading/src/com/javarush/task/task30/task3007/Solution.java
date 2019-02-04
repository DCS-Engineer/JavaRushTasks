package com.javarush.task.task30.task3007;

/* 
Найдем число 2 в максимальной степени
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(maxPowerOf2(140_000));   //131072
        System.out.println(maxPowerOf2(1026));      //1024
        System.out.println(maxPowerOf2(17));        //16
    }

    public static int maxPowerOf2(int x) {
        x &= ~(x >> 1);
        x &= ~(x >> 2);
        x &= ~(x >> 3);
        x &= ~(x >> 4);
        x &= ~(x >> 5);
        x &= ~(x >> 6);
        x &= ~(x >> 7);
        x &= ~(x >> 8);
        x &= ~(x >> 9);
        x &= ~(x >> 10);
        x &= ~(x >> 11);
        x &= ~(x >> 12);
        x &= ~(x >> 13);
        x &= ~(x >> 14);
        x &= ~(x >> 15);
        x &= ~(x >> 16);
        x &= ~(x >> 17);
        x &= ~(x >> 18);
        x &= ~(x >> 19);
        x &= ~(x >> 20);
        x &= ~(x >> 21);
        x &= ~(x >> 22);
        x &= ~(x >> 23);
        x &= ~(x >> 24);
        x &= ~(x >> 25);
        x &= ~(x >> 26);
        x &= ~(x >> 27);
        x &= ~(x >> 28);
        x &= ~(x >> 29);
        x &= ~(x >> 30);
        x &= ~(x >> 31);
        return x;
    }
}