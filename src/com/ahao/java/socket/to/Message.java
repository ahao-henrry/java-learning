package com.ahao.java.socket.to;

import java.io.Serializable;

/**
 * 消息类
 *
 * @author ahao
 * @since 2019-04-17 21:40:44
 * */
public class Message implements Serializable {
    private String sender;
    private String receiver;
    private String content;
    private String time;

    public Message(String sender, String receiver, String content, String time) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
