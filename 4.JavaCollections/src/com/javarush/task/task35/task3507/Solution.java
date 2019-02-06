package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals){
        Set<Animal> result = new HashSet<>();
        MyLoader loader = new MyLoader();
//        String pathToClasses = pathToAnimals.replaceAll("%20", " ");
//        File[] fileArray = new File(pathToClasses.substring(1, pathToClasses.length())).listFiles();
        File[] fileArray = new File(pathToAnimals).listFiles(); // it's not working. throws NPE
        for (File item: fileArray){
            if (!item.isDirectory()) {
                try {
                    Class findedClass = loader.findClass(item.getAbsolutePath());
                    Constructor[] constructors = findedClass.getConstructors();
                    Constructor defaultConstructor = null;
                    boolean isPublicDefaultConstructor = false;
                    for (Constructor c: constructors
                         ) {
                        if (c.getParameterTypes().length == 0){
                            isPublicDefaultConstructor = true;
                            defaultConstructor = c;
                        }
                    }
                    if (isPublicDefaultConstructor & Animal.class.isAssignableFrom(findedClass)) {
                        Animal createdObject = (Animal) defaultConstructor.newInstance();
                        result.add(createdObject);
                    }
                } catch (ClassNotFoundException e) {
                } catch (IllegalAccessException e) {
                } catch (InstantiationException e) {
                } catch (InvocationTargetException e) {
                }
            }
        }


        return result;
    }

    public static class MyLoader extends ClassLoader{

        @Override
        protected Class findClass(String name) throws ClassNotFoundException {
            Class result = null;
            try {
                byte[] classBytes = Files.readAllBytes(Paths.get(name));
                String className = Solution.class.getPackage().getName() + ".data." + Paths.get(name).getFileName().toString().split("\\.")[0];
                result = defineClass(className, classBytes, 0, classBytes.length);
            } catch (IOException e) {}
            return result;
        }
    }
}
