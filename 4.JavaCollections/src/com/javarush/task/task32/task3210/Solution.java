package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
            long fileLength = randomAccessFile.length();
            long filePointer = Long.parseLong(args[1]);
            String text = args[2];
            randomAccessFile.seek(filePointer);
            byte[] buffer = new byte[text.length()];
            randomAccessFile.read(buffer, 0, text.length());
            String readText = new String(buffer);
            randomAccessFile.seek(fileLength);
            if (text.equalsIgnoreCase(readText)) randomAccessFile.write("true".getBytes());
            else randomAccessFile.write("false".getBytes());
            randomAccessFile.close();
        }
        catch (FileNotFoundException e)
        {

        }
        catch (IOException e)
        {

        }
    }
}
