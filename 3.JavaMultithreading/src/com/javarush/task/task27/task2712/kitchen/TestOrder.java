package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        this.dishes = new ArrayList<>();
        int listSize = (int) (Math.random() * Dish.values().length);
        for (int i = 0; i < listSize; i++){
            dishes.add(Dish.values()[(int) (Math.random() * Dish.values().length)]);
        }
    }
}
