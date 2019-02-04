package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() throws IOException{
        return bis.readLine();
    }

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> orderDishList = new ArrayList<>();
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        ConsoleHelper.writeMessage("Введите название блюда из предложенных. Для завершения введите 'exit':");
        while (true) {
            String inputDish = ConsoleHelper.readString();
            if (inputDish.equalsIgnoreCase("exit"))
                break;
            try
            {
                orderDishList.add(Dish.valueOf(inputDish));
            }
            catch (IllegalArgumentException e)
            {
                ConsoleHelper.writeMessage("К сожалению, такого блюда нет в меню");
            }
        }
        return orderDishList;
    }
}
