package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {

            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);

        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return 0L;
    }

    public void putEntry(Entry entry){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))){
            for (Entry e = entry; e != null; e = e.next){
                outputStream.writeObject(e);
            }
            outputStream.flush();

        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry(){
        if (getFileSize() == 0L) return null;
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))){
            Entry e = (Entry) inputStream.readObject();
            return e;
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
