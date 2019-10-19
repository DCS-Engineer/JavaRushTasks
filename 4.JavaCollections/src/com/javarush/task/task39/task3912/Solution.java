package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {

        int result = maxSquare(new int[][]{{1, 0, 1, 0},
                                           {1, 1, 1, 1},
                                           {0, 1, 1, 1},
                                           {0, 1, 1, 1}});
        System.out.println(result);
    }

    public static int maxSquare(int[][] matrix) {
        int result = 0;
        int[][] cache = Arrays.copyOf(matrix, matrix.length);

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                if (i == 0 || j == 0){
                    if (result < cache[i][j]) result = cache[i][j];
                }
                else if (cache[i][j] > 0){
                    cache[i][j] = 1 + Math.min(cache[i][j - 1], Math.min(cache[i - 1][j], cache[i - 1][j - 1]));
                    if (cache[i][j] > result) result = cache[i][j];
                }
            }
        }

        return result*result;
    }
}
