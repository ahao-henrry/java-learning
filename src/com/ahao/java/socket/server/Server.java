package com.ahao.java.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器
 *
 * @author ahao
 * @since 2019-04-17 20:54:45
 * */
public class Server {
    // 用来存放客户端 socket
    public static ConcurrentHashMap<String, Socket> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Server().initSocketServer();
    }

    public void initSocketServer() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader socketReader;
        try {
            serverSocket = new ServerSocket(8890);
            System.out.println("now i'm listening.");
            while (true) {
                socket = serverSocket.accept();
                PrintWriter messageWriter = new PrintWriter(socket.getOutputStream());
                socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receive = socketReader.readLine();
                String userName = receive.split("&")[0];
                String password = receive.split("&")[1];
                if ("123".equals(password)) {
                    messageWriter.println("1");
                    messageWriter.flush();
                    clients.put(userName, socket);
                    new Thread(new MessageRepeater(userName, socket), "user" + userName).start();
                } else {
                    messageWriter.println("2");
                    messageWriter.flush();
                    socket.close();
                }
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
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
