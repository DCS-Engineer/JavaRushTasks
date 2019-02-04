package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
*/
public class Solution
{
    private static long directoriesCount = 0;
    private static long filesCount = 0;
    private static long sumOfBytes = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path sourcePath = Paths.get(reader.readLine());

        if (Files.isDirectory(sourcePath))
        {
            walkDirectory(sourcePath);
            System.out.println(String.format("Всего папок - %d", directoriesCount));
            System.out.println(String.format("Всего файлов - %d", filesCount));
            System.out.println(String.format("Общий размер - %d", sumOfBytes));
        }
        else
        {
            System.out.println(sourcePath.toString() + " - не папка");
        }
    }

    public static void walkDirectory(Path source) throws IOException
    {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(source))
        {
            for (Path item : directoryStream)
            {
                if (Files.isDirectory(item))
                {
                    directoriesCount++;
                    walkDirectory(item);
                }
                if (Files.isRegularFile(item))
                {
                    filesCount++;
                    sumOfBytes += Files.size(item);
                }
            }
        }
    }
}
