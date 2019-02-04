package com.javarush.task.task30.task3013;

/* 
Набираем код
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = Integer.MAX_VALUE - 133;
        System.out.println(Integer.toString(number, 2));

        String result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);
    }

    public int resetLowerBits(int number) {
        //напишите тут ваш код
        number &= ~(number >> 1);
        number &= ~(number >> 2);
        number &= ~(number >> 3);
        number &= ~(number >> 4);
        number &= ~(number >> 5);
        number &= ~(number >> 6);
        number &= ~(number >> 7);
        number &= ~(number >> 8);
        number &= ~(number >> 9);
        number &= ~(number >> 10);
        number &= ~(number >> 11);
        number &= ~(number >> 12);
        number &= ~(number >> 13);
        number &= ~(number >> 14);
        number &= ~(number >> 15);
        number &= ~(number >> 16);
        number &= ~(number >> 17);
        number &= ~(number >> 18);
        number &= ~(number >> 19);
        number &= ~(number >> 20);
        number &= ~(number >> 21);
        number &= ~(number >> 22);
        number &= ~(number >> 23);
        number &= ~(number >> 24);
        number &= ~(number >> 25);
        number &= ~(number >> 26);
        number &= ~(number >> 27);
        number &= ~(number >> 28);
        number &= ~(number >> 29);
        number &= ~(number >> 30);
        number &= ~(number >> 31);
        return number;
    }
}