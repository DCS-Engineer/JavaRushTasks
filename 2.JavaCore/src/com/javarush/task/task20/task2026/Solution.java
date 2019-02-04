package com.javarush.task.task20.task2026;

import java.util.Arrays;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        byte[][] tempArray = Arrays.copyOf(a, a.length);
        int rectCount = 0;
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempArray[i].length; j++) {
                if (tempArray[i][j] == 1){
                    rectCount++;
                    fillZeroes(i, j, tempArray);
                    i = 0;
                    j = 0;
                }
            }
        }
        return rectCount;
    }

    public static void fillZeroes (int x, int y, byte[][] array){
        for (int i = x; i < array.length;) {
            for (int j = y; j < array[i].length; ) {
                if (array[i][j] == 1) {
                    array[i][j] = 0;
                    if (j + 1 < array[i].length && array[i][j + 1] == 0)
                        break;
                    j++;
                }

            }
            if (i + 1 < array.length && array[i + 1][y] == 0) {
                i = array.length;
            }
            else i++;
        }
    }
}
