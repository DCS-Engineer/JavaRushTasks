package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException
    {
        List<String> listOfPath = new ArrayList<String>();
        PriorityQueue<File> files = new PriorityQueue<>();
        files.add(new File(root));
        while (files.size() != 0)
        {
            File[] fileArray = files.poll().listFiles();
            if (fileArray.length != 0)
            {
                for (File item : fileArray)
                {
                    if (item.isFile())
                    {
                        listOfPath.add(item.getAbsolutePath());
                    }
                    else if (item.isDirectory())
                    {
                        files.add(item);
                    }
                }
            }
        }
        return listOfPath;
    }

    public static void main(String[] args){

    }
}
