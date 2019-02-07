package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class classItem: classes) {
            Class superClazz = classItem.getSuperclass();
            ArrayList<Class> listOfSuperClasses = new ArrayList<>();
            while (superClazz != null){
                listOfSuperClasses.add(superClazz);
                superClazz = superClazz.getSuperclass();
            }
            for (Class superClassItem: listOfSuperClasses) {
                Class[] arrayOfInterfaces = superClassItem.getInterfaces();
                for (Class interfaceItem: arrayOfInterfaces) {
                    if (interfaceItem.getSimpleName().equals("List")){
                        Method[] arrayOfMethods = classItem.getMethods();
                        for (Method methodItem: arrayOfMethods) {
                            if (methodItem.getName().equals("get")){
                                try {
                                    Constructor constructor = classItem.getDeclaredConstructor();
                                    constructor.setAccessible(true);
                                    List emptyList = (List) constructor.newInstance();
                                    //
                                    emptyList.get(0);
                                }
                                catch (IndexOutOfBoundsException e){
                                    return classItem;
                                }
                                catch (NoSuchMethodException e) {}
                                catch (IllegalAccessException e) {}
                                catch (InstantiationException e) {}
                                catch (InvocationTargetException e) {}

                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
