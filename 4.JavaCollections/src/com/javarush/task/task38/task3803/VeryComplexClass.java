package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/


public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = Integer.valueOf(42);
        String s = (String) obj;
    }

    public void methodThrowsNullPointerException() {
        Object[] arr = null;
        System.out.println(arr[2]);
    }

    public static void main(String[] args) {
    }
}
