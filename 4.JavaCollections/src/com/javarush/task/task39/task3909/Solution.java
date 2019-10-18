package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("qwer", "qwert") + " expected: true");
        System.out.println(isOneEditAway("qwer", "tqwert") + " expected: false");
        System.out.println(isOneEditAway("qwer", "qwter") + " expected: true");
        System.out.println(isOneEditAway("qwer", "") + " expected: false");
        System.out.println(isOneEditAway("", "") + " expected: true");
        System.out.println(isOneEditAway("qwer", "qwer") + " expected: true");
        System.out.println(isOneEditAway("qwwwwwwwwwwwww", "wwwwwwwwwwwww") + " expected: true");

        System.out.println(isOneEditAway("01", "102") + " expected: false");
        System.out.println(isOneEditAway("01", "12") + " expected: false");
        System.out.println(isOneEditAway("ботинок", "почтичто") + " expected: false");
        System.out.println(isOneEditAway("2", "33") + " expected: false");
        System.out.println(isOneEditAway("1234", "12367") + " expected: false");
        System.out.println(isOneEditAway("123", "1023") + " expected: true");
        System.out.println(isOneEditAway("22266", "2266") + " expected: true");
        System.out.println(isOneEditAway("2266", "22266") + " expected: true");
        System.out.println(isOneEditAway("am", "mak") + " expected: false");
        System.out.println(isOneEditAway("123", "1453") + " expected: false");
        System.out.println(isOneEditAway("12", "1") + " expected: true");
        System.out.println(isOneEditAway("1", "12") + " expected: true");
        System.out.println(isOneEditAway("KissMyShinyReactor!", "KissMyShinyReactor") + " expected: true");
        System.out.println(isOneEditAway("KissMyShinyReactor", "KissMyShinyReactor!") + " expected: true");
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null ||
                Math.abs(first.length() - second.length()) > 1) return false;
        if (first.equals(second) ||
                (first.length() == 0 && second.length() == 0) ||
                (first.length() == 0 && second.length() == 1) ||
                (first.length() == 1 && second.length() == 0)) return true;
        int diff = 0;
        if (first.length() - second.length() == 1){
            if (first.startsWith(second) || first.endsWith(second)) return true;
            for (int i = 0; i < first.length() - 1; i++) {
                String tmp = first.substring(0, i) + first.substring(i + 1);
                if (tmp.equals(second)) return true;
            }
        }
        else if (second.length() - first.length() == 1){
            if (second.startsWith(first) || second.endsWith(first)) return true;
            for (int i = 0; i < second.length() - 1; i++) {
                String tmp = second.substring(0, i) + second.substring(i + 1);
                if (tmp.equals(first)) return true;
            }
        }
        else if (first.length() == second.length()){
            char[] firstChars = first.toCharArray();
            char[] secondChars = second.toCharArray();
            for (int i = 0; i < first.length(); i++) {
                if (firstChars[i] != secondChars[i]) diff++;
            }
            if (diff == 1) return true;
        }
        return false;
    }
}
