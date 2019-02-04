package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;

    @Override
    public void run() {
        try {
            Thread.sleep(0);
            while (!Thread.currentThread().isInterrupted()){
                System.out.println(thread.getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(String threadName) {
        Thread thread = new Thread(this, threadName);
        this.thread = thread;
        thread.start();
//        System.out.println(thread.getName());
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
