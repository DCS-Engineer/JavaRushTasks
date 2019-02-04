package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (!tablets.isEmpty()){
            while (!Thread.currentThread().isInterrupted()){
                tablets.get((int) (Math.random() * tablets.size())).createTestOrder();
                try {
                    Thread.sleep(interval);
                }
                catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
