package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable createExceptionFactory(Enum e) {
        if (e != null) {
            String tmp = e.toString().toLowerCase();
            StringBuilder message = new StringBuilder();
            message.append(tmp.substring(0, 1).toUpperCase());
            message.append(tmp.substring(1).replaceAll("_", " "));

            if (e instanceof ApplicationExceptionMessage){
                return new Exception(message.toString());
            }
            if (e instanceof DatabaseExceptionMessage){
                return new RuntimeException(message.toString());
            }
            if (e instanceof UserExceptionMessage){
                return new Error(message.toString());
            }
        }
        return new IllegalArgumentException();
    }
}
