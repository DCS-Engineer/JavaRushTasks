package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(isPowerOfThree(0));
//        System.out.println(isPowerOfThree(1));
//        System.out.println(isPowerOfThree(243));
//        System.out.println(isPowerOfThree(43046721));
//        System.out.println(isPowerOfThree(254));
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        return Math.pow(3, Math.round(Math.log(n) / Math.log(3))) == n;
    }
}
