package com.ahao.java.socket.client;

import com.ahao.java.socket.to.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 管理 socket 的线程类
 *
 * @author ahao
 * @since 2019-04-19 20:37:58
 * */
public class SocketThread implements Runnable {
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
                Message message = (Message) ois.readObject();
                Chat chat = Statics.getChats().get(message.getReceiver() + message.getSender());
                chat.getHistoryTextArea().append(message.getSender() + "(");
                chat.getHistoryTextArea().append(message.getTime());
                chat.getHistoryTextArea().append("): ");
                chat.getHistoryTextArea().append(System.lineSeparator());
                chat.getHistoryTextArea().append(message.getContent());
                chat.getHistoryTextArea().append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != this.socket) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
