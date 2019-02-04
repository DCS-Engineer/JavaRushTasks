package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber == null){return false;}

        if (telNumber.matches("^\\+\\d{12}")){
            System.out.println("1st");
            return true;
        }

        if (telNumber.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{7}")){
            System.out.println("2nd");
            return true;
        }

        if (telNumber.matches("^\\+\\d{8}-\\d{2}-\\d{2}")){
            System.out.println("3rd");
            return true;
        }

        if (telNumber.matches("^\\d{6}-\\d{4}")){
            System.out.println("4th");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
    }
}
