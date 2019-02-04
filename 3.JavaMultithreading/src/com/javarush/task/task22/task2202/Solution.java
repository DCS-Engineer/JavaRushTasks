package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string){
        String result;
        if (string == null || string.isEmpty())
            throw new TooShortStringException();
        String[] arrayOfWords = string.split(" ");
        if (arrayOfWords.length < 5)
            throw new TooShortStringException();
        result = arrayOfWords[1] + " " + arrayOfWords[2] + " " + arrayOfWords[3] + " " + arrayOfWords[4];
        return result;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
