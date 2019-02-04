package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        String decimalNumber = null;
        // If input string "s" is number in Hexademical System
        if (s.startsWith("0x")){
            String sub = s.substring(2, s.length());
            decimalNumber = String.valueOf(Integer.parseInt(sub, 16));
        }
        // If input string "s" is number in Binary System
        else if (s.startsWith("0b")){
            String sub = s.substring(2, s.length());
            decimalNumber = String.valueOf(Integer.parseInt(sub, 2));
        }
        // If input string "s" is number in Octal System
        else if (s.startsWith("0")){
            String sub = s.substring(1, s.length());
            decimalNumber = String.valueOf(Integer.parseInt(sub, 8));
        }
        else {
            decimalNumber = String.valueOf(Integer.parseInt(s, 10));
        }
        return decimalNumber;
    }
}
