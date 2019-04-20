package com.ahao.java.socket.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * 带界面的服务器
 *
 * @author ahao
 * @since 2019-04-15 19:47:10
 */
public class GuiServer extends JFrame implements Runnable,ActionListener {
    private JScrollPane scrollTextArea;
    private static JTextArea historyTextArea;
    private JPanel jPanel;
    private static JTextField messageTextField;
    private static JButton sendBtn;
    private static PrintWriter messageWriter = null;

    public GuiServer() {
        historyTextArea = new JTextArea();
        scrollTextArea = new JScrollPane(historyTextArea);
        scrollTextArea.getHorizontalScrollBar().setAutoscrolls(true);
        jPanel = new JPanel();
        messageTextField = new JTextField(50);
        messageTextField.setPreferredSize(new Dimension(800, 50));
        sendBtn = new JButton("send");
        sendBtn.addActionListener(this);
        sendBtn.setPreferredSize(new Dimension(100, 50));

        jPanel.add(messageTextField);
        jPanel.add(sendBtn);

        this.add(scrollTextArea);
        this.add(jPanel, "South");

        this.setSize(1000, 900);
        this.setTitle("Server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static JButton getSendBtn() {
        return sendBtn;
    }

    public static PrintWriter getMessageWriter() {
        return messageWriter;
    }

    public static JTextField getMessageTextField() {
        return messageTextField;
    }

    public static JTextArea getHistoryTextArea() {
        return historyTextArea;
    }

    public static void main(String[] args) {
        new GuiServer().initSocketServer();
    }
    public void initSocketServer() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader socketReader;
        try {
            serverSocket = new ServerSocket(8890);
            socket = serverSocket.accept();
            System.out.println("client connected");
            messageWriter = new PrintWriter(socket.getOutputStream());
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String receive = socketReader.readLine();
                historyTextArea.append("Client(");
                historyTextArea.append(String.format("%1$tF %1$tT", Calendar.getInstance()));
                historyTextArea.append("): ");
                historyTextArea.append(System.lineSeparator());
                historyTextArea.append(receive);
                historyTextArea.append(System.lineSeparator());
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

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(GuiServer.getSendBtn())) {
            PrintWriter printWriter = GuiServer.getMessageWriter();
            if (null == printWriter) {
                System.out.println("init ServerSocket failed.");
                return;
            }

            JTextField MessageTextField = GuiServer.getMessageTextField();
            printWriter.println(MessageTextField.getText());
            printWriter.flush();

            JTextArea historyTextArea = GuiServer.getHistoryTextArea();
            historyTextArea.append("Server(");
            historyTextArea.append(String.format("%1$tF %1$tT", Calendar.getInstance()));
            historyTextArea.append("): ");
            historyTextArea.append(System.lineSeparator());
            historyTextArea.append(MessageTextField.getText());
            historyTextArea.append(System.lineSeparator());

            MessageTextField.setText("");
        }
    }
}
