package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    static final long serialVersionUID = -5024744406713321676L;
    private transient HashMap<E, Object> map;

    private static final Object PRESENT = new Object();


    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max(16, (int) Math.ceil(collection.size() / .75f)));
        this.addAll(collection);
    }

    public AmigoSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }
    public AmigoSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    AmigoSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }
    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public Object clone() {
        try {
            AmigoSet<E> obj = (AmigoSet<E>) super.clone();
            obj.map = (HashMap<E, Object>) map.clone();
            return obj;
        } catch (Exception e) {
            throw new InternalError();
        }
    }
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.writeInt(HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity"));
        outputStream.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor"));
        outputStream.writeInt(map.size());
        for(E element: map.keySet()){
            outputStream.writeObject(element);
        }
    }
    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
        int capacity = inputStream.readInt();
        float loadFactor = inputStream.readFloat();
        int size = inputStream.readInt();
        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++){
            add((E) inputStream.readObject());
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return false;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }
}
