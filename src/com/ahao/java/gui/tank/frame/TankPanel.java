package com.ahao.java.gui.tank.frame;


import com.ahao.java.gui.tank.tank.Bullet;
import com.ahao.java.gui.tank.tank.Tank;
import com.ahao.java.gui.tank.tank.Tank.TankBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Panel 类，画坦克之类的东西
 * */
public class TankPanel extends JPanel implements Runnable {
    // 这里把这个 TankPanel 做成单例模式的，因为它本来也不需要多个
	private static final TankPanel INSTANCE = new TankPanel();
    // 友军坦克
    private Tank friendlyTank;
    // 敌军坦克
	private List<Tank> enemyTanks;

	// 构造函数设置为 private 的，不让其他的地方new
	private TankPanel() {
        friendlyTank = new TankBuilder(385, 530).speed(10).build();
        enemyTanks = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            Tank enemyTank = new TankBuilder(150 * (i + 1), 15)
                    .type(2)
                    .color(Color.PINK)
                    .direction(2)
                    .speed(5)
                    .build();
            Thread thread = new Thread(enemyTank, "enemyTank" + i);
            thread.start();
            enemyTanks.add(enemyTank);
        }
    }

	public static TankPanel getInstance() {
		return INSTANCE;
	}

	public Tank getFriendlyTank() {
		return friendlyTank;
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
        // 填充背景色
		graphics.fillRect(0, 0, 800, 600);

        // 画友军坦克及子弹
		if (friendlyTank.getAlive()) {
            this.drawTank(friendlyTank.getX(), friendlyTank.getY(),
                    graphics, friendlyTank.getColor(), friendlyTank.getDirection());
            this.drawBullet(friendlyTank, graphics);

            // 画敌军坦克和子弹
            for (int i = 0; i < enemyTanks.size(); i++) {
                Tank enemyTank = enemyTanks.get(i);
                if (enemyTank.getAlive()) {
                    this.drawTank(enemyTank.getX(), enemyTank.getY(),
                            graphics, enemyTank.getColor(), enemyTank.getDirection());
                    this.drawBullet(enemyTank, graphics);
                } else {
                    enemyTanks.remove(enemyTank);
                }
            }
        }
	}

	/**
	 * 分别根据不同的方向来画坦克
	 * @param x 坦克横坐标
     * @param y 坦克纵坐标
     * @param graphics 画笔
     * @param color 坦克颜色
     * @param direction 坦克方向
	 * */
	public void drawTank(Integer x, Integer y, Graphics graphics, Color color, Integer direction) {
	    // 设置颜色
        graphics.setColor(color);
        // 先画正方形
        graphics.fill3DRect(x - 8, y - 8, 16, 16, false);
        // 再画圆形
        graphics.fillOval(x - 8, y - 8, 16, 16);
        // 炮筒和两个长方形要根据坦克方向来画
        if ((Tank.DIRECTION_UP == direction) || (Tank.DIRECTION_DOWN == direction)) {
            if (Tank.DIRECTION_UP == direction) {
                graphics.drawLine(x, y, x, y - 15);
            } else {
                graphics.drawLine(x, y, x, y + 15);
            }
            graphics.fill3DRect(x - 13, y - 15, 5, 30, false);
            graphics.fill3DRect(x + 8, y - 15, 5, 30, false);
        } else {
            if (Tank.DIRECTION_LEFT == direction) {
                graphics.drawLine(x, y, x - 15, y);
            } else {
                graphics.drawLine(x, y, x + 15, y);
            }
            graphics.fill3DRect(x - 15, y - 13, 30, 5, false);
            graphics.fill3DRect(x - 15, y + 8, 30, 5, false);
        }
	}

	/**
     * 根据坦克画子弹
     * @param tank 要发射子弹的坦克
     * @param graphics 画笔
     * */
    private void drawBullet(Tank tank, Graphics graphics) {
	    List<Bullet> bullets = tank.getBullets();
        if (bullets.size() > 0) {
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                if (bullet.getAlive()) {
                    graphics.fillOval(bullet.getX(), bullet.getY(), 2, 2);
                } else {
                    bullets.remove(bullet);
                }
            }
        }
    }

    /**
     * 打到坦克的时候的操作（这个肯定是指敌军打到我军或者是我军打到敌军）
     * @param bullet 子弹
     * @param tank 坦克
     * */
    private void shotTank(Bullet bullet, Tank tank) {
        Integer bulletX = bullet.getX();
        Integer bulletY = bullet.getY();
        Integer tankX = tank.getX();
        Integer tankY = tank.getY();
        if ((Math.abs(bulletX - tankX) < 15) && (Math.abs(bulletY - tankY) < 15)) {
            bullet.setAlive(false);
            tank.setAlive(false);
        }
    }

	@Override
	public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 每100毫秒检查一下我军子弹是不是击中敌军坦克
            List<Bullet> bullets = friendlyTank.getBullets();
            for (Bullet bullet : bullets) {
                if (bullet.getAlive()) {
                    for (Tank enemyTank : enemyTanks) {
                        this.shotTank(bullet, enemyTank);
                    }
                }
            }

            // 每100毫秒检查一下我军是不是被敌人击中
            for (Tank enemyTank : enemyTanks) {
                for (Bullet bullet : enemyTank.getBullets()) {
                    if (bullet.getAlive()) {
                        this.shotTank(bullet, friendlyTank);
                    }
                }
            }

            // 当然这个 repaint 方法是肯定要调的，不然怎么有新画面呢
            this.repaint();
        }
	}
}
