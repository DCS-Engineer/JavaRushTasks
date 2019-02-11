package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {

        // Delete before checking task
//        String pathToClasses = packageName.replaceAll("%20", " ");
//        File[] files = new File(pathToClasses.substring(1, pathToClasses.length())).listFiles();
        //
        String sep = System.getProperty("file.separator");
        String pathName = packageName;
        if (!(packageName.endsWith(sep))){
            pathName = pathName.concat(sep);
        }
        File[] files = new File(pathName).listFiles();
        MyLoader loader = new MyLoader();
        for (File file: files) {
            if (file.isFile() & file.getName().endsWith(".class")){
                hiddenClasses.add(loader.findClass(file.getPath()));
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz: hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                try {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor c: constructors) {
                        if (c.getParameterTypes().length == 0){
                            Class[] interfaces = clazz.getInterfaces();
                            if (interfaces.length > 0){
                                for (Class intefaze: interfaces) {
                                    if (intefaze.getSimpleName().equals("HiddenClass")){
                                        c.setAccessible(true);
                                        return (HiddenClass) c.newInstance();
                                    }
                                }
                            }
                        }
                    }
                }
                catch (IllegalAccessException e) {e.printStackTrace();}
                catch (InstantiationException e) {e.printStackTrace();}
                catch (InvocationTargetException e) {e.printStackTrace();}
            }
        }
        return null;
    }

    public static class MyLoader extends ClassLoader{

        @Override
        protected Class findClass(String name) throws ClassNotFoundException {
            try {
                byte[] classBytes = Files.readAllBytes(Paths.get(name));
                return defineClass(null, classBytes, 0, classBytes.length);
            }
            catch (IOException e) {return super.findClass(name);}
        }
    }
}

