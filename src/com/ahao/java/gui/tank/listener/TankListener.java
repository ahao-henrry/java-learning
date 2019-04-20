package com.ahao.java.gui.tank.listener;

import com.ahao.java.gui.tank.frame.TankPanel;
import com.ahao.java.gui.tank.tank.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 事件监听处理，在这里处理友军坦克的事件，比如按键移动以及按键发射子弹等
 * @author ahao
 * @version 2019-04-09 21:14:44
 * */
public class TankListener implements KeyListener {
    private final TankPanel tankPanel = TankPanel.getInstance();

	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
        Tank tank = tankPanel.getFriendlyTank();
		if (keyChar == 'w') {
            tank.setDirection(1);
            tank.moveUp();
		} else if (keyChar == 's') {
            tank.setDirection(2);
            tank.moveDown();
		} else if (keyChar == 'a') {
            tank.setDirection(3);
            tank.moveLeft();
		} else if (keyChar == 'd') {
            tank.setDirection(4);
            tank.moveRight();
		} else {
		}

		if (keyChar == 'j') {
            tank.shoot();
		}
        tankPanel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
