package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        List<File> fileList = new ArrayList<File>();
        getFiles(fileList, path);
        fileList.sort(new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        try (FileWriter fileWriter = new FileWriter(allFilesContent))
        {
            for (File item : fileList)
            {
                FileReader fileReader = new FileReader(item);
                while (fileReader.ready())
                {
                    fileWriter.write(fileReader.read());
                }
                fileWriter.write("\n");
                fileWriter.flush();
                fileReader.close();
            }
        }
    }

    public static void getFiles(List<File> fileList, File directory)
    {
        for (File item : directory.listFiles())
        {
            if (item.isDirectory())
            {
                getFiles(fileList, item);
                continue;
            }
            else if (item.isFile())
            {
                if (item.length() <= 50)
                {
                    fileList.add(item);
                }
            }
        }
    }
}
