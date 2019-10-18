package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
//        if (isPalindromePermutation("ss ffff,. 1133322  ; aa")) System.out.println("Yes");
//        else System.out.println("No");
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();
        Map<Character, Integer> integerMap = new HashMap<>();
        for (Character item: chars) {
            if (!integerMap.containsKey(item)) integerMap.put(item, 1);
            else {
                int val = integerMap.get(item) + 1;
                integerMap.replace(item, val);
            }
        }
        int even = 0, odd = 0;
        for (Map.Entry<Character,Integer> entry: integerMap.entrySet()) {
            if (entry.getValue() % 2 == 0) even++;
            else odd++;
        }
        if ((even > 0 & odd == 0) || (even > 0 & odd == 1) || (even == 0 & odd == 1)) return true;
        return false;
    }
}
