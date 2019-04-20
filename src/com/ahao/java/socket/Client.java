package com.ahao.java.socket;

import java.io.*;
import java.net.Socket;

/**
 * SocketClient
 * @author ahao
 * @since 2019-04-14 21:21:35
 * */
public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        // client.once();
        client.keepAlive();
    }

    /**
     * 只能一次通信
     * */
    private void once() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.2.3", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("Hello");
            printWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保持连接
     * */
    private void keepAlive() {
        Socket socket = null;
        try {
            socket = new Socket("127.3.4.6", 8889);

            System.out.println("What do you want to say to Server : ");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String send = consoleReader.readLine();
                printWriter.println(send);
                printWriter.flush();

                String receive = socketReader.readLine();
                System.out.println("'" + receive + "', Server said.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
