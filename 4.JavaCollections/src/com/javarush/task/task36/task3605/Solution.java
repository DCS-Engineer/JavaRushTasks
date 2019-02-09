package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> strings = new TreeSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = "";
        while ((line = reader.readLine()) != null){
            char[] charArray = line.toLowerCase().toCharArray();
            for (char item: charArray) {
                if ((byte)item >= 97 & (byte)item <= 122 ){
                    strings.add(String.valueOf(item));
                }
            }
        }
        if (strings.size() <= 5){
            for (String item: strings) {
                System.out.print(item);
            }
        }
        else {
            int count = 0;
            for (String item: strings) {
                if (count == 5) break;
                System.out.print(item);
                count++;
            }
        }
    }
}
