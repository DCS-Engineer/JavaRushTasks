package com.javarush.task.task31.task3109;

import java.io.FileInputStream;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        int index = fileName.lastIndexOf(".");
        String extension = index >= 0 ? fileName.substring(index) : "";
        try {
            switch (extension) {
                case ".xml": {
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    properties.loadFromXML(fileInputStream);
                    fileInputStream.close();
                    break;
                }
                case ".txt": {
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    properties.load(fileInputStream);
                    fileInputStream.close();
                    break;
                }
                default: {
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    properties.load(fileInputStream);
                    fileInputStream.close();
                    break;
                }
            }
        } catch (Exception e) {
            return properties;
        }
        return properties;
    }
}
