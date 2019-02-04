package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
            long fileLength = randomAccessFile.length();
            long filePointer = Long.parseLong(args[1]);
            byte[] byteArray = args[2].getBytes();
            if (filePointer >= fileLength)
            {
                randomAccessFile.seek(fileLength);
                randomAccessFile.write(byteArray);
            }
            else
            {
                randomAccessFile.seek(filePointer);
                randomAccessFile.write(byteArray);
            }
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
