package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(), url.getDefaultPort());
            OutputStream os = socket.getOutputStream();
            StringBuilder sb = new StringBuilder("GET ");
            sb.append(url.getFile());
            sb.append(" HTTP/1.1\r\nHost: ");
            sb.append(url.getHost());
            sb.append("\r\n\r\n");
            os.write(sb.toString().getBytes());
            os.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}