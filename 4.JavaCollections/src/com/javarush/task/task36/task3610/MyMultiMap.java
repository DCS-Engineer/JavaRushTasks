package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int valuesCount = 0;
        for (K key: map.keySet()) {
            valuesCount += map.get(key).size();
        }
        return valuesCount;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        if (map.containsKey(key)){
            V lastValue = null;
            if (map.get(key).size() < repeatCount){
                int listSize = map.get(key).size();
                lastValue = map.get(key).get(listSize - 1);
                map.get(key).add(value);
            }
            else if (map.get(key).size() == repeatCount){
                int listSize = map.get(key).size();
                lastValue = map.get(key).get(listSize - 1);
                map.get(key).remove(0);
                map.get(key).add(value);
            }
            return lastValue;
        }
        else {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if (map.containsKey(key)){
            V firstValue = map.get(key).get(0);
            map.get(key).remove(0);
            if (map.get(key).size() == 0){
                map.remove(key, map.get(key));
            }
            return firstValue;
        }
        else {
            return null;
        }
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        List<V> listOfAllValues = new ArrayList<>();
        for (K key: map.keySet()) {
            listOfAllValues.addAll(map.get(key));
        }
        return listOfAllValues;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        boolean isConsist = false;
        for (K key: map.keySet()) {
            if (map.get(key).contains(value)) isConsist = true;
        }
        return isConsist;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}