package com.ahao.java.gui.tank.tank;

public class Bullet implements Runnable {
    // 横坐标
	private Integer x;
	// 纵坐标
    private Integer y;
    // 速度
    private Integer speed = 5;
    // 方向
    private Integer direction;
    //是否存活
    private Boolean alive = true;

    // 这里的构造函数必须要传这几个参数，因为这几个参数是根据坦克当时的属性来决定的
	public Bullet(Integer x, Integer y, Integer direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

    public Integer getDirection() {
	    return direction;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    @Override
	public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (1 == this.direction) {
                y -= speed;
            } else if (2 == this.direction) {
                y += speed;
            } else if (3 == this.direction) {
                x -= speed;
            } else {
                x += speed;
            }

            if (x < 0 || x > 800 || y < 0 || y > 600) {
                this.alive = false;
                break;
            }
        }
	}
}
