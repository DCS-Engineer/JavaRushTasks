package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        List<Tablet> tabletList = new ArrayList<>();
        DirectorTablet directorTablet = new DirectorTablet();
        // Create cooks
        Cook cookOne = new Cook("Grachev");
        cookOne.setQueue(orderQueue);
        Thread threadOne = new Thread(cookOne);
        threadOne.start();
        Cook cookTwo = new Cook("Nazarchuk");
        cookTwo.setQueue(orderQueue);
        Thread threadTwo = new Thread(cookTwo);
        threadTwo.start();

        Waiter waiterOne = new Waiter();
        cookOne.addObserver(waiterOne);
        cookTwo.addObserver(waiterOne);
        for (int i = 0; i < 5; i++){
            tabletList.add(new Tablet(i + 1));
            tabletList.get(i).setQueue(orderQueue);
        }
        /*Stream.iterate(1, i -> i + 1).limit(10).forEach(i -> tabletList.add(new Tablet(i)));
        tabletList.stream().forEach(t -> t.addObserver(t.getNumber() % 2 == 0 ? cookOne : cookTwo));*/

        Thread testRandomOrderGeneratorTask = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        testRandomOrderGeneratorTask.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        testRandomOrderGeneratorTask.interrupt();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
