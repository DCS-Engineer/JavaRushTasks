package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            String[] arrayOfWheels = loadWheelNamesFromDB();
            if (arrayOfWheels.length == 0 | arrayOfWheels.length > 4 ) throw new IllegalArgumentException();
            if (arrayOfWheels.length > 0 && arrayOfWheels.length < 4) throw new IllegalArgumentException();
            this.wheels = new ArrayList<>();
            for (String item : arrayOfWheels
                 ) {
                Wheel wheel = Wheel.valueOf(item);
                this.wheels.add(wheel);
            }

        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
