package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest{

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Long startMillsGetIds = new Date().getTime();
        for (String item: strings) {
            ids.add(shortener.getId(item));
        }
        Long stopMillsGetIds = new Date().getTime();
        return stopMillsGetIds - startMillsGetIds;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Long startMillsGetStrings = new Date().getTime();
        for (Long item: ids) {
            strings.add(shortener.getString(item));
        }
        Long stopMillsGetStrings = new Date().getTime();
        return stopMillsGetStrings - startMillsGetStrings;
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++){
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();

        Long timeIdsShort1 = getTimeToGetIds(shortener1, origStrings, ids1);
        Long timeIdsShort2 = getTimeToGetIds(shortener2, origStrings, ids2);


        Long timeValuesShort1 = getTimeToGetStrings(shortener1, ids1, new HashSet<String>());
        Long timeValuesShort2 = getTimeToGetStrings(shortener2, ids2, new HashSet<String>());

        Assert.assertTrue(timeIdsShort1 > timeIdsShort2);
        Assert.assertEquals(timeValuesShort1, timeValuesShort2, 30);
    }
}
