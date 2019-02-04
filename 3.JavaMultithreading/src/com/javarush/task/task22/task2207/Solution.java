package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try {
            //Считываем имя файла из консоли
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            //Прочитаем содержимое файла в переменную StringBuilder
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            StringBuilder sbFromFile = new StringBuilder();
            while (fileReader.ready()){
                sbFromFile.append(" " + fileReader.readLine());
            }
            sbFromFile.append(" ");
            fileReader.close();
            //Получим List из слов, разделенных пробелами
            ArrayList<String> arrayList = new ArrayList<String>();
            Collections.addAll(arrayList, sbFromFile.toString().split(" "));
            for (int i = 0; i < arrayList.size(); i++){
                if (arrayList.get(i).equals("") | arrayList.get(i).contains(" "))
                    arrayList.remove(i);
            }
            System.out.println("Размер листа " + arrayList.size());
            for (int i = 0; i < arrayList.size(); i++){
                int indexFirst = sbFromFile.indexOf(" " + arrayList.get(i) + " ");
                int indexSecond = sbFromFile.indexOf(" " + new StringBuilder(arrayList.get(i)).reverse().toString() + " ", indexFirst + 1);
                if (indexFirst != -1 & indexSecond != -1){
                    Pair pair = new Pair();
                    pair.first = arrayList.get(i);
                    pair.second = new StringBuilder(arrayList.get(i)).reverse().toString();
                    result.add(pair);
                    sbFromFile.delete(indexFirst + 1, indexFirst + 1 + arrayList.get(i).length());
                    sbFromFile.delete(indexSecond + 1 - arrayList.get(i).length(), indexSecond + 1);
                }
            }
            for (Pair item : result
                 ) {
                System.out.println(item.first + " " + item.second);
            }
        }
        catch (IOException e){}
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
            first = "";
            second = "";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}