package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;
        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++){
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (value.equals(e.value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null)
            return;
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry e = table[i]; e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                e.value = value;
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return 0L;
        for (Entry item : table) {
            for (Entry e = item; e != null; e = e.next)
                if (value.equals(e.value))
                    return item.getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }

    public int hash(Long k){
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    public int indexFor(int hash, int length){
        return hash & (length - 1);
    }

    public Entry getEntry(Long key){
        return table[indexFor(hash(key), table.length)];
    }

    public void resize(int newCapacity){
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    public void transfer(Entry[] newTable){
        Entry[] source = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < source.length; j++){
            Entry e = source[j];
            if (e != null){
                source[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }
}
