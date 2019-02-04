package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        if (args.length > 1)
        {
            Path fileName = Paths.get(args[0]);
            Path sourceZip = Paths.get(args[1]);
            Map<String, byte[]> zipEntryMap = new HashMap<>();
            // Reading all entries from archive to map
            try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(sourceZip)))
            {
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                while (zipEntry != null)
                {
                    zipEntryMap.put(zipEntry.getName(), readEntryBytes(zipEntry, zipInputStream));
                    zipInputStream.closeEntry();
                    zipEntry = zipInputStream.getNextEntry();
                }
            }
            // Write all entries include the file from args[0]
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(sourceZip)))
            {
                boolean isContain = false;
                for (Map.Entry<String, byte[]> entry : zipEntryMap.entrySet())
                {
                    if (entry.getKey().endsWith("/" + fileName.getFileName().toString()))
                    {
                        isContain = true;
                        zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                        Files.copy(fileName, zipOutputStream);
                        zipOutputStream.closeEntry();
                        continue;
                    }
                    zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                    zipOutputStream.write(entry.getValue());
                    zipOutputStream.closeEntry();
                }
                if (!isContain){
                    zipOutputStream.putNextEntry(new ZipEntry("new/" + fileName.getFileName().toString()));
                    Files.copy(fileName, zipOutputStream);
                    zipOutputStream.closeEntry();
                }
            }
        }
    }

    private static byte[] readEntryBytes(ZipEntry zipEntry, ZipInputStream zipInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count;
        while ((count = zipInputStream.read(buffer)) != -1)
        {
            byteArrayOutputStream.write(buffer, 0, count);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
