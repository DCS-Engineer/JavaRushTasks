package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);

        //напишите тут ваш код
        if (softReference != null)
            return softReference.get();
        else return null;
    }

    public AnyObject put(Long key, AnyObject value) {
        try {
            if (cacheMap.containsKey(key)){
                AnyObject anyObject = cacheMap.get(key).get();
                SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
                softReference.clear();
                return anyObject;
            }
        }
        catch (NullPointerException e){
            SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
            softReference.clear();
            return null;
        }
        return null;
    }

    public AnyObject remove(Long key) {
        try {
            if (cacheMap.containsKey(key)){
                AnyObject anyObject = cacheMap.get(key).get();
                SoftReference<AnyObject> softReference = cacheMap.remove(key);
                softReference.clear();
                return anyObject;
            }
        }
        catch (NullPointerException e){
            SoftReference<AnyObject> softReference = cacheMap.remove(key);
            softReference.clear();
            return null;
        }
        return null;

    }
}