package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ThreadLocalRandom;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++){
            int selectSymbolInterval = ThreadLocalRandom.current().nextInt(1, 4);
            if (i == 1) outputStream.write(ThreadLocalRandom.current().nextInt(48, 58));
            else if (i == 4) outputStream.write(ThreadLocalRandom.current().nextInt(65, 91));
            else if (i == 6) outputStream.write(ThreadLocalRandom.current().nextInt(97, 123));
            else {
                switch (selectSymbolInterval){
                    case 1:
                        outputStream.write(ThreadLocalRandom.current().nextInt(48, 58));
                        break;
                    case 2:
                        outputStream.write(ThreadLocalRandom.current().nextInt(65, 91));
                        break;
                    case 3:
                        outputStream.write(ThreadLocalRandom.current().nextInt(97, 123));
                }
            }
        }
        return outputStream;
    }
}