package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            BotClient.this.sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] text = message.split(": ");
            if (text.length > 1) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = null;
                switch (text[1]) {
                    case "дата":
                        dateFormat = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        dateFormat = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        dateFormat = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        dateFormat = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        dateFormat = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        dateFormat = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        dateFormat = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        dateFormat = new SimpleDateFormat("s");
                        break;
                }
                if (!(dateFormat == null)) {
                    BotClient.this.sendTextMessage("Информация для " + text[0] + ": " + dateFormat.format(calendar.getTime()));
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int numberBot = (int) (Math.random() * 100);
        return "date_bot_" + numberBot;
    }

    public static void main(String[] args) {
        Client botClient = new BotClient();
        botClient.run();
    }
}
