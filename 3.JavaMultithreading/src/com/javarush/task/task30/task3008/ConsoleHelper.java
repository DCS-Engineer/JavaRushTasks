package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        String inputString = null;
        boolean isEntered = false;
        while (!isEntered){
            try {
                inputString = bufferedReader.readLine();
                isEntered = true;
            }
            catch (IOException e){
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }

        return inputString;
    }

    public static int readInt(){
        int result = 0;
        boolean isEntered = false;
        while (!isEntered){
            try {
                result = Integer.parseInt(readString());
                isEntered = true;
            }
            catch (NumberFormatException e){
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }

        return result;
    }
}
