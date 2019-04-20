package com.ahao.java.socket.server;

import com.ahao.java.socket.to.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 消息转发类
 *
 * @author ahao
 * @since 2019-04-18 20:25:20
 * */
public class MessageRepeater implements Runnable {
    private String ownId;
    private Socket socket;

    public MessageRepeater(String ownId, Socket socket) {
        this.ownId = ownId;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("启动了" + this.ownId + "的线程");
            while (true) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                Socket toSocket = Server.clients.get(message.getReceiver());
                ObjectOutputStream oos = new ObjectOutputStream(toSocket.getOutputStream());
                oos.writeObject(message);
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != this.socket) {
                try {
                    this.socket.close();
                    Server.clients.remove(this.ownId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
