package com.ahao.java.socket.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 登陆界面
 * @author ahao
 * @version 2019-04-08 21:41:34
 * */
public class Login extends JFrame implements ActionListener {
	private JPanel userNamePanel;
    private JLabel userNameLabel;
    private JTextField userName;
    private JPanel passwordPanel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JPanel btnPanel;
    private JButton loginBtn;
    private JButton cancelBtn;

	public Login() throws HeadlessException {
		this.userNamePanel = new JPanel();
		this.userNameLabel = new JLabel("userName");
		this.userName = new JTextField(10);

		this.passwordPanel = new JPanel();
		this.passwordLabel = new JLabel("password");
		this.password = new JPasswordField(10);

		this.btnPanel = new JPanel();
		this.loginBtn = new JButton("login");
        this.loginBtn.addActionListener(this);
		this.cancelBtn = new JButton("cancel");

		this.userNamePanel.add(userNameLabel);
		this.userNamePanel.add(userName);
		this.add(userNamePanel);

		this.passwordPanel.add(passwordLabel);
		this.passwordPanel.add(password);
		this.add(passwordPanel);

		this.btnPanel.add(loginBtn);
		this.btnPanel.add(cancelBtn);
		this.add(btnPanel);

		this.setLayout(new GridLayout(3, 1));
		this.setSize(500, 150);
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.loginBtn) {
            try {
                Socket socket = new Socket("127.224.243.23", 8890);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(userName.getText() + "&" + new String(password.getPassword()));
                printWriter.flush();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s = bufferedReader.readLine();
                if ("1".equals(s)) {
                    System.out.println("登录成功");
                    new Thread(new SocketThread(socket), "Socket" + userName.getText()).start();
                    Statics.getSockets().put(userName.getText(), socket);
                    new FriendList(userName.getText());
                    this.dispose();
                } else {
                    System.out.println("用户名密码错误");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
