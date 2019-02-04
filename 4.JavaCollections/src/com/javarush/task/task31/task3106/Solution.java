package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;


/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        if (args.length > 2){
            String resultFileName = args[0];
            List<String> fileNamePart = new ArrayList<>();
            for (int i = 1; i < args.length; i++){
                fileNamePart.add(args[i]);
            }
            Collections.sort(fileNamePart);
            List<FileInputStream> fisArrayList = new ArrayList<>();
            for (String item : fileNamePart){
                fisArrayList.add(new FileInputStream(item));
            }
            SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(fisArrayList));
            ZipInputStream zipInputStream = new ZipInputStream(sequenceInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
            byte[] buffer = new byte[1024];
            while (zipInputStream.getNextEntry() != null){
                int count;
                while ((count = zipInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, count);
                }
            }
            sequenceInputStream.close();
            zipInputStream.close();
            fileOutputStream.close();
        }
    }
}
