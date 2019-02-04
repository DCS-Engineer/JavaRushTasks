package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
//        for (int i = 1; i < 100; i++){
//            solution.createExpression(i);
//        }
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        StringBuilder sb = new StringBuilder(number + " =");
        if (number == 1){
            sb.append(" +" + number);
        }
        else {
            recursiveHelpMethod(number);
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).equals("0")) {
                    sb.append(" " + list.get(i) + " " + String.valueOf((int) Math.pow(3, i)));
                }
            }
        }
        System.out.println(sb.toString());
    }

    public void recursiveHelpMethod(int number) {
//        System.out.println("Number: " + number);
        int remainder = number % 3;
//        System.out.println("Remainder: " + remainder);
        int whole = number / 3;
//        System.out.println("Whole: " + whole);
        switch (remainder) {
            case 0: {
                list.add("0");
                break;
            }
            case 1: {
                list.add("+");
                break;
            }
            case 2: {
                list.add("-");
                whole++;
                break;
            }
        }

        if (whole == 1) {
            list.add("+");
        } else {
            recursiveHelpMethod(whole);
        }
    }
}