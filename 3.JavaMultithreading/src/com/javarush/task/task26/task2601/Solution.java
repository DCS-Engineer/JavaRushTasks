package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here

        Arrays.sort(array);
        float medium;
        if (array.length % 2 == 0)
            medium = (array[(array.length / 2) - 1] + array[array.length / 2]) / 2f;
        else
            medium = array[array.length / 2];
        final double finalMedium = medium;
        Arrays.sort(array, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                int result = (int)(Math.abs(o1 - finalMedium) - Math.abs(o2 - finalMedium));
                return result == 0 ? o1 - o2 : result;
            }
        });
        return array;
    }
}
/* -= Мое решение. Может не такое изящное=-
        Integer[] arrayEven = {8, 15, 5, 17};
        sort(arrayEven);
        for (Integer item : arrayEven
             ) {
            System.out.print(item + " ");
        }
        System.out.println();

        Integer[] arrayOdd = {13, 8, 15, 5, 17};
        sort(arrayOdd);
        for (Integer item : arrayOdd
                ) {
            System.out.print(item + " ");
        }

        Arrays.sort(array);
        Integer[] completeArray = new Integer[array.length];
        if (array.length % 2 == 0){
            int middle = array.length / 2;
            double median = ((double) array[middle - 1] + array[middle]) / 2;
            boolean isDone = false;
            int leftIndex = middle - 1;
            int rightIndex = middle;
            int i = 0;
            while (!isDone){
                if (leftIndex < 0 && rightIndex == array.length){
                    isDone = true;
                }
                if (!isDone){
                    double leftBias = -1.0;
                    double rightBias = -1.0;
                    if (leftIndex >= 0) leftBias = Math.abs(median - array[leftIndex]);
                    if (!(rightIndex >= array.length)) rightBias = Math.abs(median - array[rightIndex]);
                    if (leftBias < rightBias | rightBias == -1.0) {
                        completeArray[i] = array[leftIndex];
                        i++;
                        leftIndex--;
                    }
                    else if (rightBias < leftBias | leftBias == -1.0){
                        completeArray[i] = array[rightIndex];
                        i++;
                        rightIndex++;
                    }
                    else if (leftBias == rightBias){
                        completeArray[i] = array[leftIndex];
                        i++;
                        leftIndex--;
                    }
                }
            }
        }
        else {
            int middle = array.length / 2;
            int median = array[middle];
            completeArray[0] = median;
            boolean isDone = false;
            int leftIndex = middle - 1;
            int rightIndex = middle + 1;
            int i = 1;
            while (!isDone){
                if (leftIndex < 0 && rightIndex == array.length){
                    isDone = true;
                }
                if (!isDone){
                    int leftBias = -1;
                    int rightBias = -1;
                    if (leftIndex >= 0) leftBias = Math.abs(median - array[leftIndex]);
                    if (!(rightIndex >= array.length)) rightBias = Math.abs(median - array[rightIndex]);
                    if (leftBias < rightBias | rightBias == -1) {
                        completeArray[i] = array[leftIndex];
                        i++;
                        leftIndex--;
                    }
                    else if (rightBias < leftBias | leftBias == -1){
                        completeArray[i] = array[rightIndex];
                        i++;
                        rightIndex++;
                    }
                    else if (leftBias == rightBias){
                        completeArray[i] = array[leftIndex];
                        i++;
                        leftIndex--;
                    }
                }
            }
        }
 */
