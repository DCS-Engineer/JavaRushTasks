package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        int divider = 2;
        while (true) {
            if (n == 1) break;
            if (n % divider == 0) {
                System.out.print(divider + " ");
                recursion(n / divider);
                break;
            }
            else divider++;
        }
    }

    public static void main(String[] args) {
        new Solution().recursion(132);
//        System.out.println(132 % 12);
    }
}
