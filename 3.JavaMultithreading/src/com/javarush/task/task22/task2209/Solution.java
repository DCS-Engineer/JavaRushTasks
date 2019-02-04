package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        try {
            //Читаем имя файла из консоли
            BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = fileNameReader.readLine();
            fileNameReader.close();
            //Читаем содержимое файла
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            StringBuilder readString = new StringBuilder();
            while (fileReader.ready()){
                readString.append(fileReader.readLine());
            }
            fileReader.close();

            String[] arrayWords = readString.toString().split(" ");
            StringBuilder result = getLine(arrayWords);
            System.out.println(result.toString());
        }
        catch (IOException e){}
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if (words.length == 0)
            return new StringBuilder("");
        ArrayList<String> listSrc = new ArrayList<String>();
        ArrayList<String> listDest = new ArrayList<String>();
        Collections.addAll(listSrc, words);
        boolean notMoreWords = false;
        while (!notMoreWords){
            notMoreWords = true;
            if (listDest.isEmpty()){
                for (int i = 0; i < listSrc.size(); i++) {
                    String end = listSrc.get(i).substring(listSrc.get(i).length() - 1, listSrc.get(i).length()).toLowerCase();
                    for (int j = 0; j < listSrc.size(); j++){
                        if (!listSrc.get(i).equals(listSrc.get(j)) & listSrc.get(j).toLowerCase().startsWith(end)) {
                            listDest.add(listSrc.get(i));
                            listSrc.remove(i);
                            i = words.length;
                            j = words.length;
                            notMoreWords = false;
                        }
                    }
                }
            }
            else if (!listDest.isEmpty()){
                for (int i = 0; i < listSrc.size();){
                    String start = listSrc.get(i).substring(0, 1).toLowerCase();
                    String end = listSrc.get(i).substring(listSrc.get(i).length() - 1, listSrc.get(i).length()).toLowerCase();
                    if (listDest.get(0).toLowerCase().startsWith(end)){
                        listDest.add(0, listSrc.get(i));
                        listSrc.remove(i);
                        i = 0;
                    }
                    else if (listDest.get(listDest.size() - 1).toLowerCase().endsWith(start)){
                        listDest.add(listSrc.get(i));
                        listSrc.remove(i);
                        i = 0;
                    }
                    else i++;
                }
            }
        }
        for (String item : listDest
             ) {
            result.append(item + " ");
        }
        result.delete(result.length() - 1, result.length());
        return result;
    }
}
