package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import java.io.IOException;
import java.util.List;

public class Order {
    protected List<Dish> dishes;
    private final Tablet tablet;

    public Order(Tablet tablet) throws IOException{
        this.tablet = tablet;
        initDishes();
    }

    public int getTotalCookingTime(){
        int summaryCookingTime = 0;
        for (Dish dish: dishes
             ) {
            summaryCookingTime += dish.getDuration();
        }
        return summaryCookingTime;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()){
            return "";
        }
        else {
            return "Your order: " + dishes.toString() + " of " + tablet.toString();
        }
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws IOException{
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
