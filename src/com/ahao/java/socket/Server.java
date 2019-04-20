package com.ahao.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketServer
 * @author ahao
 * @since 2019-04-14 21:07:39
 * */
public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        // server.once();
        server.keepAlive();
    }

    /**
     * 只能一次通信
     * */
    private void once() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("I am Running on port 8889");
            socket = serverSocket.accept();

            // 接收客户端发送的数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println(s);

            // 给客户端发送数据
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(s);
            printWriter.flush();
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

    /**
     * 保持连接
     * */
    private void keepAlive() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8889);
            System.out.println("I am Running on port 8889");
            socket = serverSocket.accept();

            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            while (true) {
                String receive = socketReader.readLine();
                System.out.println("'" + receive + "', client said.");

                System.out.println("What do you want to say to Client : ");
                String send = consoleReader.readLine();
                printWriter.println(send);
                printWriter.flush();
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
