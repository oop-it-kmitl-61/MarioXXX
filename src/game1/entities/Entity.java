package game1.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game1.Handler;

public abstract class Entity {
	protected Handler handler;
	protected int x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected static int gravity = 5;
	public String name;
	public Rectangle hitbox;
	public Entity(String name, Handler handler,int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.name = name;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
}