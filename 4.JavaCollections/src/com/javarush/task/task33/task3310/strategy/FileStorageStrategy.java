package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length; i++){
            if (tab[i] == null) continue;
            for (Entry e = tab[i].getEntry(); e != null; e = e.next) {
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
        if (table[i] == null)
            createEntry(hash, key, value, i);
        else {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Long k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    e.value = value;
                }
            }
            addEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return 0L;
        for (FileBucket item : table) {
            if (item == null) continue;
            for (Entry e = item.getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return e.getKey();
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
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next){
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    public void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (FileBucket item: newTable){
            item = new FileBucket();
        }
        transfer(newTable);
        table = newTable;
    }

    public void transfer(FileBucket[] newTable){
        int newCapacity = newTable.length;
        for (int j = 0; j < table.length; j++){
            if (table[j] == null) continue;
            Entry e = table[j].getEntry();
            if (e != null){
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
            }
            table[j].remove();
            table[j] = null;
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }
}
