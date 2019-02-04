package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        String result;
        String[] arrayOfWords;
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }
        int indexTab = string.indexOf("\t");
        if (indexTab == -1) {
            throw new TooShortStringException();
        }
        int countTab = 0;
        int tempIndex = 0;
        while (true){
            tempIndex = string.indexOf("\t", tempIndex);
            if (tempIndex == -1)
                break;
            countTab++;
            tempIndex++;
        }
        if (countTab < 2) {
            throw new TooShortStringException();
        }
        arrayOfWords = string.split("\\t");
        result = arrayOfWords[1];
        return result;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
