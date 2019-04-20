package com.ahao.java.gui.tank.frame;

import com.ahao.java.gui.tank.listener.TankListener;

import javax.swing.*;

/**
 * Frame 类
 * @author ahao
 * @version 2019-04-09 23:43:47
 * */
public class TankFrame extends JFrame {
    private final TankPanel tankPanel = TankPanel.getInstance();

	public TankFrame() {
		Thread thread = new Thread(tankPanel);
		thread.start();
		this.addKeyListener(new TankListener());
		this.add(tankPanel);
		this.setSize(800, 600);
		this.setTitle("SuperTank");
		this.setIconImage(new ImageIcon("./resources/icon.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
