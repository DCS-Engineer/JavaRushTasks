package com.javarush.task.task29.task2909.car;

import java.util.Date;

abstract public class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    private boolean canPassengersBeTransferred(){
        if (isDriverAvailable() && fuel > 0)
            return true;
        else
            return false;
    }

    public double getWinterConsumption(int length){
        return length * winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length){
        return length * summerFuelConsumption;
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
        if (date.after(summerStart) && date.before(summerEnd))
            return true;
        else
            return false;
    }

    public static Car create(int type, int numberOfPassengers){
        switch (type){
            case 0:
                return new Truck(numberOfPassengers);
            case 1:
                return new Sedan(numberOfPassengers);
            case 2:
                return new Cabriolet(numberOfPassengers);
        }
        return null;
    }

    public void fill(double numberOfLiters) throws Exception{
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        if (isSummer(date, SummerStart, SummerEnd)) {
            return getSummerConsumption(length);
        } else {
            return getWinterConsumption(length);
        }
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred())
            return numberOfPassengers;
        else
            return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    abstract public int getMaxSpeed();
}