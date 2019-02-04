package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same", "poeej", "fulm", "rek", "gsf", "rj", "rrj", "mglp", "jhvok", "orgn", "ranm");
        System.out.println("Размер листа: " + list.size());
        for (Word item: list
             ) {
            System.out.println(item.toString());
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<Word>();
        for (int n = 0; n < words.length; n++){
            char[] arrayCharOfWord = words[n].toCharArray();
            for (int i = 0; i < crossword.length; i++)
                for (int j = 0; j < crossword[i].length; j++){
                    if (crossword[i][j] == arrayCharOfWord[0]){
                        List tempList = searchWord(crossword, words[n], arrayCharOfWord, j, i);
                        if (tempList.size() > 0)
                            for (int index = 0; index < tempList.size(); index++)
                                list.add((Word) tempList.get(index));
                    }
                }
        }
        return list;
    }
    public static List<Word> searchWord(int[][] crossword, String word, char[] arrayCharOfWord, int startX, int startY){
        List<Word> list = new ArrayList<Word>();
        int indexX = startX;
        int indexY = startY;
        int endX;
        int endY;

        if (indexY - 1 >= 0 && indexX - 1 >= 0 && crossword[indexY - 1][indexX - 1] == arrayCharOfWord[1]){
            indexX--;
            indexY--;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexY - 1 >= 0 && indexX - 1 >= 0 && crossword[indexY - 1][indexX - 1] == arrayCharOfWord[index]){
                        indexX--;
                        indexY--;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexY - 1 >= 0 && crossword[indexY - 1][indexX] == arrayCharOfWord[1]){
            indexY--;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexY - 1 >= 0 && crossword[indexY - 1][indexX] == arrayCharOfWord[index]){
                        indexY--;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexY - 1 >= 0 && indexX + 1 < crossword[indexY - 1].length && crossword[indexY - 1][indexX + 1] == arrayCharOfWord[1]){
            indexX++;
            indexY--;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexY - 1 >= 0 && indexX + 1 < crossword[indexY - 1].length && crossword[indexY - 1][indexX + 1] == arrayCharOfWord[index]){
                        indexX++;
                        indexY--;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexX + 1 < crossword[indexY].length && crossword[indexY][indexX + 1] == arrayCharOfWord[1]){
            indexX++;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexX + 1 < crossword[indexY].length && crossword[indexY][indexX + 1] == arrayCharOfWord[index]){
                        indexX++;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexY + 1 < crossword.length && indexX + 1 < crossword[indexY + 1].length && crossword[indexY + 1][indexX + 1] == arrayCharOfWord[1]){
            indexX++;
            indexY++;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexY + 1 < crossword.length && indexX + 1 < crossword[indexY + 1].length && crossword[indexY + 1][indexX + 1] == arrayCharOfWord[index]){
                        indexX++;
                        indexY++;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexY + 1 < crossword.length && crossword[indexY + 1][indexX] == arrayCharOfWord[1]){
            indexY++;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexY + 1 < crossword.length && crossword[indexY + 1][indexX] == arrayCharOfWord[index]){
                        indexY++;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexY + 1 < crossword.length && indexX - 1 >= 0 && crossword[indexY + 1][indexX - 1] == arrayCharOfWord[1]){
            indexX--;
            indexY++;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexY + 1 < crossword.length && indexX - 1 >= 0 && crossword[indexY + 1][indexX - 1] == arrayCharOfWord[index]){
                        indexX--;
                        indexY++;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        if (indexX - 1 >= 0 && crossword[indexY][indexX - 1] == arrayCharOfWord[1]){
            indexX--;
            if (arrayCharOfWord.length == 2){
                endX = indexX;
                endY = indexY;
                Word findingWord = new Word(word);
                findingWord.setStartPoint(startX, startY);
                findingWord.setEndPoint(endX, endY);
                list.add(findingWord);
            }
            else {
                for (int index = 2; index < arrayCharOfWord.length; index++){
                    if (indexX - 1 >= 0 && crossword[indexY][indexX - 1] == arrayCharOfWord[index]){
                        indexX--;
                        if (index + 1 >= arrayCharOfWord.length){
                            endX = indexX;
                            endY = indexY;
                            Word findingWord = new Word(word);
                            findingWord.setStartPoint(startX, startY);
                            findingWord.setEndPoint(endX, endY);
                            list.add(findingWord);
                        }
                    }
                    else {
                        index = arrayCharOfWord.length;
                        indexX = startX;
                        indexY = startY;
                    }
                }
            }
        }
        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
