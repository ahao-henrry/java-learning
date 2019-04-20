package com.ahao.java.socket.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 好友列表
 *
 * @author ahao
 * @since 2019-04-16 20:24:16
 * */
public class FriendList extends JFrame implements MouseListener {
    // 列表头
    private JLabel header;

    // 好友列表
    private JScrollPane friendsList;
    private JPanel listPanel;

    private String ownId;

    public FriendList(String ownId) {
        this.ownId = ownId;

        header = new JLabel("My Friends");

        listPanel = new JPanel(new GridLayout(50, 1, 5, 5));
        Image image = new ImageIcon("./resources/icon.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        for (int i = 0; i < 50; i++) {
            JLabel friend = new JLabel(i + 1 + "", new ImageIcon(image), JLabel.LEFT);
            friend.setSize(350, 30);
            friend.addMouseListener(this);
            listPanel.add(friend);
        }
        friendsList = new JScrollPane(listPanel);

        this.add(header, "North");
        this.add(friendsList, "Center");

        this.setSize(400, 1000);
        this.setTitle(this.ownId + "'s Friend List");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String friendId = ((JLabel) e.getSource()).getText();
            Statics.getChats().put(this.ownId + friendId, new Chat(this.ownId, friendId));
            System.out.println();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setForeground(Color.cyan);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setForeground(Color.black);
    }
}
