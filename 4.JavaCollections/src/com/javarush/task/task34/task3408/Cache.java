package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (!cache.containsKey(key)){
            Constructor constructor = clazz.getConstructor(key.getClass());
            V object = (V) constructor.newInstance(key);
            cache.put(key, object);
            return object;
        }
        return cache.get(key);
    }

    public boolean put(V obj) {
        //TODO add your code here
        int cacheOldSize = size();
        Class objClass = obj.getClass();
        Method method = null;
        try {
            method = objClass.getDeclaredMethod("getKey", null);
            method.setAccessible(true);
            K objectSomeKey = (K) method.invoke(obj, null);
            cache.put(objectSomeKey, obj);
            if (size() > cacheOldSize) return true;
        }
        catch (InvocationTargetException e) {return false;}
        catch (IllegalAccessException e) {return false;}
        catch (NoSuchMethodException e) {return false;}
        return false;
    }

    public int size() {
        return cache.size();
    }
}
