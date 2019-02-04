package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread t1 = new Thread(){
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                synchronized (o2){
                    Thread.currentThread().interrupt();
                }
            }
        };
        synchronized (o1){
            t1.start();
            while (true){
                if (t1.getState() == Thread.State.BLOCKED)
                    break;
            }

            t2.start();
            while (!t2.isInterrupted()){
                Thread.State threadState = t2.getState();
                if (threadState != Thread.State.RUNNABLE){
                    if (threadState == Thread.State.BLOCKED){
                        return false;
                    }
                    else return true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
