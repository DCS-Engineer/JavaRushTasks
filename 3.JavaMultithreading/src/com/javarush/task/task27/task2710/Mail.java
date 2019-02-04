package com.javarush.task.task27.task2710;

public class Mail {
    private String text;

    public String getText() {
        while (text == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.notify();
    }
}
