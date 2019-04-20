package com.ahao.java.socket.client;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Statics {
    private static ConcurrentHashMap<String, Socket> sockets = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Chat> chats = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, Socket> getSockets() {
        return sockets;
    }

    public static ConcurrentHashMap<String, Chat> getChats() {
        return chats;
    }
}
