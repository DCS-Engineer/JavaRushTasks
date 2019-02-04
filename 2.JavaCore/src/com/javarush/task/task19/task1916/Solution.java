package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException{
        ArrayList<String> listOfFile1 = new ArrayList<String>();
        ArrayList<String> listOfFile2 = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedReader readerFile1 = new BufferedReader(new FileReader(fileName1));
        while (readerFile1.ready()){
            listOfFile1.add(readerFile1.readLine());
        }
        readerFile1.close();

        BufferedReader readerFile2 = new BufferedReader(new FileReader(fileName2));
        while (readerFile2.ready()){
            listOfFile2.add(readerFile2.readLine());
        }
        readerFile2.close();

        boolean sameFlag = false;
        for (int i = 0, j = 0;;){
            if ((i < listOfFile1.size()) && (j < listOfFile2.size()) && listOfFile1.get(i).equals(listOfFile2.get(j)) && !sameFlag){
                lines.add(new LineItem(Type.SAME, listOfFile1.get(i)));
                i++;
                j++;
                sameFlag = true;
            }
            else if ((j + 1 < listOfFile2.size()) && sameFlag && !listOfFile1.get(i).equals(listOfFile2.get(j)) && !listOfFile1.get(i).equals(listOfFile2.get(j + 1))){
                lines.add(new LineItem(Type.REMOVED, listOfFile1.get(i)));
                i++;
                sameFlag = false;
            }
            else if ((j + 1 < listOfFile2.size()) && sameFlag && !listOfFile1.get(i).equals(listOfFile2.get(j)) && listOfFile1.get(i).equals(listOfFile2.get(j + 1))){
                lines.add(new LineItem(Type.ADDED, listOfFile2.get(j)));
                j++;
                sameFlag = false;
            }
            else if ((j == listOfFile2.size()) && (i < listOfFile1.size()) && sameFlag){
                lines.add(new LineItem(Type.REMOVED, listOfFile1.get(i)));
                i++;
                sameFlag = false;
            }
            else if ((i == listOfFile1.size()) && (j < listOfFile2.size()) && sameFlag){
                lines.add(new LineItem(Type.ADDED, listOfFile2.get(j)));
                j++;
                sameFlag = false;
            }
            else if ((i == listOfFile1.size()) && (j == listOfFile2.size())){
                break;
            }
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
