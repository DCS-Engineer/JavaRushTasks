package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000L);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> keys = new HashSet<>();
        for (String item: strings) {
            keys.add(shortener.getId(item));
        }
        return keys;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for (Long item: keys) {
            strings.add(shortener.getString(item));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (long i = 0L; i < elementsNumber; i++){
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        for (String item: strings) {
            shortener.getId(item);
        }
        long startMillsGetIds = new Date().getTime();
        Set<Long> keys = getIds(shortener, strings);
        long stopMillsGetIds = new Date().getTime();
        System.out.println(String.format("Время выполнения метода getIds, %1$d мс", (stopMillsGetIds - startMillsGetIds)));
        long startMillsGetStrings = new Date().getTime();
        Set<String> stringSet = getStrings(shortener, keys);
        long stopMillsGetStrings = new Date().getTime();
        System.out.println(String.format("Время выполнения метода getStrings, %1$d мс", (stopMillsGetStrings - startMillsGetStrings)));
        if ((strings.size() == stringSet.size()) & (strings.equals(stringSet))){
            System.out.println("Тест пройден.");
        }
        else System.out.println("Тест не пройден.");
    }
}
