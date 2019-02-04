package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    static AtomicInteger priorityNumber = new AtomicInteger(1);

    public MyThread() {
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(Runnable target) {
        super(target);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(String name) {
        super(name);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (priorityNumber.get() < Thread.MAX_PRIORITY) setPriority(priorityNumber.getAndIncrement());
        else {
            setPriority(priorityNumber.get());
            priorityNumber.set(Thread.MIN_PRIORITY);
        }
    }
}
