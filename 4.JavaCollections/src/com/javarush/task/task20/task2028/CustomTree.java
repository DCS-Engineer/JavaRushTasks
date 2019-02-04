package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private Entry<String> lastElement;
    private int size, indexOfCurrentRoot;
    private ArrayList<Entry<String>> listOfAllElements;

    public CustomTree() {
        root = new Entry<>("корень");
        lastElement = root;
        size = 0;
        indexOfCurrentRoot = 0;
        listOfAllElements = new ArrayList<>();
        listOfAllElements.add(root);

    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public void checkChildren(){
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    @Override
    public boolean add(String s) {
        Entry newEntry = new Entry(s);

        if (!lastElement.isAvailableToAddChildren()){
            if (lastElement.leftChild == null) lastElement.availableToAddLeftChildren = true;
            if (lastElement.rightChild == null) lastElement.availableToAddRightChildren = true;
        }

        if(lastElement.availableToAddLeftChildren){
            lastElement.leftChild = newEntry;
            lastElement.checkChildren();
            newEntry.parent = lastElement;
        }
        else if(!lastElement.availableToAddLeftChildren & lastElement.availableToAddRightChildren) {
            lastElement.rightChild = newEntry;
            lastElement.checkChildren();
            newEntry.parent = lastElement;
            indexOfCurrentRoot++;
            lastElement = listOfAllElements.get(indexOfCurrentRoot);
        }

        listOfAllElements.add(newEntry);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();
        Entry<String> removeObject = getElementByName(o.toString());
        Entry<String> parent = removeObject.parent;
        if (removeObject.leftChild != null) remove(removeObject.leftChild.elementName);
        if (removeObject.rightChild != null) remove(removeObject.rightChild.elementName);
        if (removeObject.leftChild == null && removeObject.rightChild == null){
            if (parent.rightChild == removeObject){
                parent.rightChild = null;
                parent.availableToAddRightChildren = true;
            }
            else {
                parent.leftChild = null;
                parent.availableToAddLeftChildren = true;
            }
            listOfAllElements.remove(removeObject);
            size--;

            for (Entry<String> item: listOfAllElements
                 ) {
                if (item.isAvailableToAddChildren()){
                    lastElement = item;
                    indexOfCurrentRoot = listOfAllElements.indexOf(item);
                    break;
                }
            };
        }

        return true;
    }

    protected Entry<String> getElementByName(String name){
        Entry<String> entry = null;
        for (Entry item: listOfAllElements
             ) {
            if (item.elementName.equalsIgnoreCase(name))
                entry = item;
        }
        return entry;
    }

    public String getParent(String s){
        String nameOfParent = null;
        for (Entry item: listOfAllElements
             ) {
            if (item.elementName.equalsIgnoreCase(s)) {
                nameOfParent = item.parent.elementName;
            }
        }
        return nameOfParent;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.size;
    }
}
