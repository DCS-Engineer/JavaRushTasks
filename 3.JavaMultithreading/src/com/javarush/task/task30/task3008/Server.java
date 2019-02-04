package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            SocketAddress remoteAddress = socket.getRemoteSocketAddress();
            ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом " + remoteAddress);
            String userName = null;
            try {
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (ClassNotFoundException e1){
                try {
                    socket.close();
                }
                catch (IOException e) {
                }
                finally {
                    ConsoleHelper.writeMessage("Ошибка обмена данными с удаленным адресом");
                }
            }
            catch (IOException e2) {
                try {
                    socket.close();
                }
                catch (IOException e) {
                }
                finally {
                    ConsoleHelper.writeMessage("Ошибка обмена данными с удаленным адресом");
                }
            }
            finally {
                if (userName != null){
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                    ConsoleHelper.writeMessage("Закрыто соединение с удаленным адресом " + remoteAddress);
                }

            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            while (true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME){
                    if (!answer.getData().isEmpty()){
                        if (!connectionMap.containsKey(answer.getData())){
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }

            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> entryMap : connectionMap.entrySet()
                 ) {
                if (!entryMap.getKey().equals(userName)){
                    Message message = new Message(MessageType.USER_ADDED, entryMap.getKey());
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                Message messageReceive = connection.receive();
                if (messageReceive.getType() == MessageType.TEXT){
                    Message messageSend = new Message(MessageType.TEXT, userName + ": " + messageReceive.getData());
                    sendBroadcastMessage(messageSend);
                }
                else ConsoleHelper.writeMessage("Ошибка. Сообщение не соответствует условиям");
            }
        }
    }

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String, Connection> entryMap : connectionMap.entrySet()
             ) {
            try {
                entryMap.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не отправлено.");
            }
        }
    }

    public static void main(String[] args) {
        int portServer = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portServer);
            ConsoleHelper.writeMessage("Сервер запущен.");
            while (true){
                new Handler(serverSocket.accept()).start();
            }
        }
        catch (Exception e){
            try {
                serverSocket.close();
            } catch (IOException e1) {
                ConsoleHelper.writeMessage("Ошибка. Закрытие соединения с сервером.");
            }
        }

    }
}
