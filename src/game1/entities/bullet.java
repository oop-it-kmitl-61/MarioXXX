package game1.entities;
import java.awt.Graphics;
import java.awt.Rectangle;

import game1.Handler;
import game1.gfx.Assets;
	
public class bullet extends Entity{
    public static int width = 3*10;
	public static int height = 1*10;
	public int speed;
	private int xOffset;
	public Rectangle hitbox;
	
	
	public bullet(Handler handler, int speed,int x, int y) {
		super("Bullet", handler, x, y, width, height);
		this.handler = handler;
		this.speed = speed;
	}

	@Override
	public void tick() {
		getxOffset();
		hitbox = new Rectangle(x - xOffset, y, width, height);
		bullet_move();
	}
	
	private void getxOffset() {
		// TODO Auto-generated method stub
		xOffset = handler.getGameCamera().getxOffset();
		if (handler.getWorld().chapter==1&&xOffset>10560)
			xOffset = 10560;
		else if (handler.getWorld().chapter!=1&&xOffset>8000)
			xOffset = 8000;
	}
	
	private void bullet_move() {
		x += speed;
		if (speed>0) {
			if (collision((int)x+20, (int)y))
				handler.getWorld().entityManager.removeEntity(this);
		}
		else if (speed<0) {
			if (collision((int)x, (int)y))
				handler.getWorld().entityManager.removeEntity(this);
		}
	}
	
	private boolean collision(int x, int y) {
		// TODO Auto-generated method stub
		return handler.getWorld().getTile(x/64, y/64).isSolid();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bullet, (int)(x-xOffset), (int)(y), width, height, null);
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
