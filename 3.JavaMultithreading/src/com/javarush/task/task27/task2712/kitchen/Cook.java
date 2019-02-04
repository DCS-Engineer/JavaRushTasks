package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Restaurant;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy = false;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            if (!queue.isEmpty()){
                if (!this.isBusy() & queue.peek() != null) startCookingOrder(queue.poll());
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order){
        this.busy = true;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), getName(), order.getTotalCookingTime(), order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + order.toString() + ", cooking time " + order.getTotalCookingTime() + "min");
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
        }
        setChanged();
        notifyObservers(order);
        this.busy = false;
    }

    public void setQueue(LinkedBlockingQueue<Order> orderQueue) {
        this.queue = orderQueue;
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }
}
