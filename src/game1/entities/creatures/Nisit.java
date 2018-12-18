package game1.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import game1.Handler;
import game1.entities.Entity;
import game1.gfx.Animation;
import game1.gfx.Assets;
import game1.tiles.Tile;
	
public class Nisit extends Creature{
    public static int width = 64;
	public static int height = 64;
	public static int speed = 3;
	private boolean active = false;
	private Animation animRight;
	private Animation animLeft;
	
	
	public Nisit(Handler handler,int x, int y){
		super("Nisit", handler, x, y, width, height, 0);
		this.handler = handler;
		bounds.x = 5;
		bounds.y = 6;
		bounds.width = 50;
		bounds.height = 58;
		health = 10;
		head_right = false;
		animRight = new Animation(150, Assets.nisit_right);
		animLeft = new Animation(150, Assets.nisit_left);
		speed = 7;
	}

	@Override
	public void tick() {
		getxOffset();
		hitbox = new Rectangle(x+bounds.x - xOffset, y+30, bounds.width, bounds.height);
		head = new Rectangle(x-xOffset + bounds.x, y, bounds.width, 30);
		animRight.tick();
		animLeft.tick();
		active();
		if (active) {
			bot_walk();
			move();
			dead();
		}
	}
	
	private void getxOffset() {
		// TODO Auto-generated method stub
		xOffset = handler.getGameCamera().getxOffset();
		if (handler.getWorld().chapter==1&&xOffset>10560)
			xOffset = 10560;
		else if (handler.getWorld().chapter!=1&&xOffset>8000)
			xOffset = 8000;
	}
	
	private void active() {
		// TODO Auto-generated method stub
		if (x-xOffset<((handler.getPlayer().active_point)))
			active = true;
	}

	private void dead() {
		// TODO Auto-generated method stub
//		if (x/64 == handler.getPlayer().getX()/64 && y-50 <= handler.getPlayer().getY()) {
//			handler.getPlayer().setY(y-80);
//			handler.getSoundPlayer().player("nisit_die.wav");
//			handler.getWorld().entityManager.removeEntity(this);
//			handler.getPlayer().jump_com = true;
//			handler.getPlayer().score += 50;
//		}
		
		if (hitbox.intersects(handler.getPlayer().hitbox))
			handler.getPlayer().dead = true;
		
		else if (head.intersects(handler.getPlayer().hitbox)){
			handler.getPlayer().setY(y-80);
			handler.getSoundPlayer().player("nisit_die.wav");
			handler.getWorld().entityManager.removeEntity(this);
			handler.getPlayer().jump_com = true;
			handler.getPlayer().score += 50;
		}
			
		
//		if ((x<=(handler.getPlayer().getX()+32)&& (x+64)>=(handler.getPlayer().getX()+32)) && (y-30 <= handler.getPlayer().getY())) {
//			handler.getPlayer().setY(y-80);
//			handler.getSoundPlayer().player("nisit_die.wav");
//			handler.getWorld().entityManager.removeEntity(this);
//			handler.getPlayer().jump_com = true;
//			handler.getPlayer().score += 50;
//		}
			
		for (int i=0;i<handler.getWorld().entityManager.entities.size();i++) {
			Entity temp = handler.getWorld().entityManager.entities.get(i);
			if (temp.name.equals("Bullet")&&(temp.getX()+3>=x+bounds.x-5&&temp.getX()<=x+bounds.x+bounds.width+5)&&temp.getY()>=y+bounds.y&&temp.getY()<=y+bounds.y+bounds.getHeight()) {
				handler.getSoundPlayer().player("nisit_die.wav");
				handler.getWorld().entityManager.removeEntity(this);
				handler.getWorld().entityManager.removeEntity(temp);
				handler.getPlayer().score += 20;
			}
		}
		
	}

	public void bot_walk() {
		xMove = 0;
		yMove = 0;
		if (!collisionWithLowerFloor) {
			yMove = gravity;
		}
		else if (!collisionWithRight && head_right) {
			xMove = speed;
		}
		
		else if (!collisionWithLeft && !head_right) {
			xMove = -speed;
		}
		int ty = ((int) (y + yMove + bounds.y + bounds.getHeight()) / Tile.TILEWIDTH);
		if (collisionWithRight || ((!collisionWithTile(((int)(x + bounds.x) / Tile.TILEHEIGHT+1), ty))))
			head_right = false;
		if (collisionWithLeft || ((!collisionWithTile(((int)(x + bounds.x + bounds.width) / Tile.TILEHEIGHT-1), ty))))
			head_right = true;
	}


	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimFrame(), (int)(x-xOffset), (int)(y), width, height, null);
	}
	
	private BufferedImage getCurrentAnimFrame() {
		if (head_right) {
			return animRight.getCurrentFrame();
		}else {
			return animLeft.getCurrentFrame();
		}
	}
}
