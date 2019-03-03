package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener){
        String firstString = "First string are equal third";
        String secondString = "Second string for tests";
        String thirdString = "First string are equal third";

        Long firstId = shortener.getId(firstString);
        Long secondId = shortener.getId(secondString);
        Long thirdId = shortener.getId(thirdString);

        Assert.assertNotEquals(secondId, firstId);
        Assert.assertNotEquals(secondId, thirdId);

        Assert.assertEquals(firstId, thirdId);

        String firstGetString = shortener.getString(firstId);
        String secondGetString = shortener.getString(secondId);
        String thirdGetString = shortener.getString(thirdId);

        Assert.assertEquals(firstString, firstGetString);
        Assert.assertEquals(secondString, secondGetString);
        Assert.assertEquals(thirdString, thirdGetString);
    }

    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy storageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy storageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy storageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy storageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy storageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
}
