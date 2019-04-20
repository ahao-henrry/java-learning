package com.ahao.java.gui.tank.tank;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 坦克类
 * @author ahao
 * @version 2019-04-09 19:46:57
 * */
public class Tank implements Runnable {
	// 横坐标
    private Integer x;
	// 纵坐标
    private Integer y;
	// 方向(1,2,3,4分别代表上下左右)
    private Integer direction;
    // 是否还存活
    private Boolean alive;
    // 速度
    private final Integer speed;
    // 类型(敌方2,我方1)
    private final Integer type;
    // 颜色
    private final Color color;
    // 子弹
    private List<Bullet> bullets;
    // 当坦克移动到边缘的时候让它移动到别的方向去所要使用的数组
    private static final int[] MOVE_UP_ARRAY = {2, 3, 4};
    private static final int[] MOVE_DOWN_ARRAY = {1, 3, 4};
    private static final int[] MOVE_LEFT_ARRAY = {1, 2, 4};
    private static final int[] MOVE_RIGHT_ARRAY = {1, 2, 3};
    // 坦克移动方向
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;
    public static final int DIRECTION_RIGHT = 4;

	public Tank(TankBuilder tankBuilder) {
		this.x = tankBuilder.x;
		this.y = tankBuilder.y;
		this.direction = tankBuilder.direction;
		this.speed = tankBuilder.speed;
		this.type = tankBuilder.type;
		this.color = tankBuilder.color;
		this.alive = true; // 这个属性在创建的时候肯定是true，因为不可能让它一出来就是死的把
        this.bullets = new CopyOnWriteArrayList<>();
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getDirection() {
		return direction;
	}

    public void setDirection(Integer direction) {
	    this.direction = direction;
    }

	public Integer getSpeed() {
		return speed;
	}

	public Integer getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
	    this.alive = alive;
    }

    // 向上移动
	public void moveUp() {
		y -= speed;
	}

	// 向下移动
	public void moveDown() {
		y += speed;
	}

	// 向左移动
	public void moveLeft() {
		x -= speed;
	}

	// 向右移动
	public void moveRight() {
		x += speed;
	}

    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * 让坦克发射子弹
     * */
    public void shoot() {
        Integer x = this.getX();
        Integer y = this.getY();
        Bullet bullet;
        if (DIRECTION_UP == this.direction){
            bullet = new Bullet(x, y - 15, this.direction);
        } else if (DIRECTION_DOWN == this.direction) {
            bullet = new Bullet(x, y + 15, this.direction);
        } else if (DIRECTION_LEFT == this.direction) {
            bullet = new Bullet(x - 15, y, this.direction);
        } else {
            bullet = new Bullet(x + 15, y, this.direction);
        }
        Thread thread = new Thread(bullet, "bullet" + x + y);
        thread.start();
        bullets.add(bullet);
    }

    /**
     * 坦克根据不同的方向移动
     * */
    private void tankMove() {
	    if (DIRECTION_UP == this.direction){
            y -= speed;
        } else if (DIRECTION_DOWN == this.direction) {
            y += speed;
        } else if (DIRECTION_LEFT == this.direction) {
            x -= speed;
        } else {
            x += speed;
        }
    }

    @Override
    public void run() {
	    int changeDirectionTimes = 0;
	    int shotTimes = 0;
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 2s 变化一下方向
            changeDirectionTimes++;
            if (40 == changeDirectionTimes) {
                Integer direction;
                do {
                    direction = random.nextInt(5);
                } while (0 == direction);
                this.direction = direction;
                changeDirectionTimes = 0;
            }

            // 1s放射一颗子弹
            shotTimes++;
            if (20 == shotTimes) {
                this.shoot();
                shotTimes = 0;
            }

            // 当坦克跑到边框的时候就让它变位置
            Integer direction = random.nextInt(3);
            if (x <= 15) {
                this.direction = MOVE_LEFT_ARRAY[direction];
            } else if (x >= 785) {
                this.direction = MOVE_RIGHT_ARRAY[direction];
            } else if (y <= 15) {
                this.direction = MOVE_UP_ARRAY[direction];
            } else if (y >= 585) {
                this.direction = MOVE_DOWN_ARRAY[direction];
            }

            // 因为不管怎么样，坦克一直都是在移动着的，所以拿出来放到最后
            tankMove();
        }
    }

    /**
     * 构造坦克的辅助类，builder 设计模式
     * */
    public static class TankBuilder {
        // 横坐标
        private final Integer x;
        // 纵坐标
        private final Integer y;
        // 方向
        private Integer direction = DIRECTION_UP;
        // 速度
        private Integer speed = 1;
        // 类型(敌方2,我方1)
        private Integer type = 1;
        // 颜色
        private Color color = Color.MAGENTA;

        public TankBuilder(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public TankBuilder direction(Integer direction) {
            this.direction = direction;
            return this;
        }

        public TankBuilder speed(Integer speed) {
            this.speed = speed;
            return this;
        }

        public TankBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        public TankBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public Tank build() {
            return new Tank(this);
        }
    }
}
