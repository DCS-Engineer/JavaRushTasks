package com.javarush.task.task20.task2025;

import java.util.LinkedList;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;
        int porog = 10;
        LinkedList<Long> list = new LinkedList<>();
        // initial powers for the number 0-9
        long[] powers = { 0l, 1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l };

        for (long i = 1; i < N; i++) {
            if (i == porog) {
                porog *= 10;
                // calculate i^length
                for (int pi = 1; pi < 10; pi++) {
                    powers[pi] *= pi;
                }
            }
            long s = i;
            long k = 0;
            while (s > 0) {
                int r = (int)(s % 10);
                k += powers[r];
                if (k > i)
                    break;
                s /= 10;
            }

            if (k == i)
                list.add(i);
        }
        result = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {

        /*long millsStart;
        long millsStop;

        millsStart = System.currentTimeMillis();
        long[] arr = getNumbers(999999999);
        millsStop = System.currentTimeMillis();

        System.out.println("Время выполнения: " + (millsStop - millsStart) + " ms");

        for (long item : arr) {
            System.out.print(item + " ");
        }*/
    }
}
/*
        //Это мой способ нахождения чисел Армстронга
        long[] result = null;
        ArrayList<Long> resultList = new ArrayList<Long>();

        //Запусаем перебор чисел до числа N (не включая) для поиска чисел Армстронга
        for (long i = 1; i < N; i++){
            if (i < 10){
                resultList.add(i);
            }
            else {
                //int degree = (int) Math.log10(i) + 1;
                int degree = 0;
                long n = i;
                while (n != 0){
                    degree++;
                    n /= 10;
                }
                n = i;
                long sum = 0;
                while (n > 0){
                    int digit = (int) n % 10;
                    int powNum = digit;
                    for (int j = 0; j < degree - 1; j++)
                        powNum *= digit;
                    sum += powNum;
                    n /= 10;
                }
                if (sum == i)
                    resultList.add(i);
            }
        }
        result = new long[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
 */
/*
        //Это способ подсмотрел на stackoverflow
        public static long[] getNumbers(long N) {
        long[] result = null;
        return LongStream.range(1, N)
                .filter(Solution::isArmstrong)
                .toArray();
    }

    public static boolean isArmstrong(long number){
        int degree = 0;
        long n = number;
        while (n != 0){
            degree++;
            n /= 10;
        }
        n = number;
        long sum = 0;
        while (n > 0){
            int digit = (int) n % 10;
            int powNum = digit;
            for (int j = 0; j < degree - 1; j++)
                powNum *= digit;
            sum += powNum;
            n /= 10;
        }
        return sum == number;
    }
 */

