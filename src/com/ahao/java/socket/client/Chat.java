package com.ahao.java.socket.client;

import com.ahao.java.socket.to.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

/**
 * 带界面的服务器
 *
 * @author ahao
 * @since 2019-04-15 20:23:24
 */
public class Chat extends JFrame implements ActionListener {
    private JScrollPane scrollTextArea;
    private JTextArea historyTextArea;
    private JPanel jPanel;
    private JTextField messageTextField;
    private JButton sendBtn;
    private String ownId;
    private String friendId;

    public Chat(String ownId, String friendId) {
        this.ownId = ownId;
        this.friendId = friendId;
        historyTextArea = new JTextArea();
        scrollTextArea = new JScrollPane(historyTextArea);
        scrollTextArea.getHorizontalScrollBar().setAutoscrolls(true);
        jPanel = new JPanel();
        messageTextField = new JTextField(20);
        messageTextField.setPreferredSize(new Dimension(800, 50));
        sendBtn = new JButton("send");
        sendBtn.addActionListener(this);
        sendBtn.setPreferredSize(new Dimension(100, 50));

        jPanel.add(messageTextField);
        jPanel.add(sendBtn);

        this.add(scrollTextArea);
        this.add(jPanel, "South");

        this.setSize(500, 400);
        this.setLocation(1100, 0);
        this.setTitle(ownId + " to " + friendId);
        this.setVisible(true);
    }

    public JTextArea getHistoryTextArea() {
        return historyTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.sendBtn)) {
            ObjectOutputStream oos;
            Message message;
            try {
                oos = new ObjectOutputStream(Statics.getSockets().get(this.ownId).getOutputStream());
                String time = String.format("%1$tF %1$tT", Calendar.getInstance());
                message = new Message(this.ownId, this.friendId, this.messageTextField.getText(), time);
                oos.writeObject(message);
                oos.flush();

                this.historyTextArea.append(this.ownId + "(");
                this.historyTextArea.append(time);
                this.historyTextArea.append("): ");
                this.historyTextArea.append(System.lineSeparator());
                this.historyTextArea.append(this.messageTextField.getText());
                this.historyTextArea.append(System.lineSeparator());

                this.messageTextField.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }
}
