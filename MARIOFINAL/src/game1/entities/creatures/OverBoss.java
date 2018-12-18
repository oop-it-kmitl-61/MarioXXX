package game1.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import game1.Handler;
import game1.entities.Entity;
import game1.gfx.Animation;
import game1.gfx.Assets;
	
public class OverBoss extends Creature{
    public static int width = 64;
	public static int height = 128;
	public static int speed = 6;
	private boolean active = false;
	private Animation animRight;
	private Animation animLeft;
	public boolean dead;
	
	
	public OverBoss(Handler handler,int x, int y) {
		super("OverBoss", handler, x, y, width, height, 0);
		this.handler = handler;
		bounds.x = 5;
		bounds.y = 6;
		bounds.width = 50;
		bounds.height = 58*2;
		head_right = false;
		animRight = new Animation(200, Assets.over_boss_right);
		animLeft = new Animation(200, Assets.over_boss_left);
		health = 200;
		speed = 10;
		dead = false;
	}

	@Override
	public void tick() {
		getxOffset();
		hitbox = new Rectangle(x+bounds.x - xOffset, y+bounds.y, bounds.width, bounds.height);
		active();
		animRight.tick();
		animLeft.tick();
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
			xOffset =  8000;
	}
	
	private void active() {
		// TODO Auto-generated method stub
		if (x-xOffset<((handler.getPlayer().active_point)))
			active = true;
	}

	private void dead() {
		// TODO Auto-generated method stub
		if (hitbox.intersects(handler.getPlayer().hitbox))
			handler.getPlayer().dead = true;
			
		for (int i=0;i<handler.getWorld().entityManager.entities.size();i++) {
			Entity temp = handler.getWorld().entityManager.entities.get(i);
			if (temp.name.equals("Bullet")&&(temp.getX()+3>=x+bounds.x-5&&temp.getX()<=x+bounds.x+bounds.width+5)&&temp.getY()>=y+bounds.y&&temp.getY()<=y+bounds.y+bounds.getHeight()) {
				handler.getWorld().entityManager.removeEntity(temp);
				health -= 20;
				if (health<=0) {
					handler.getSoundPlayer().player("guard_die.wav");
					handler.getPlayer().over = true;
					handler.getWorld().entityManager.removeEntity(this);
					handler.getPlayer().score += 250;
				}
					
			}
		}
		
	}

	public void bot_walk() {
		xMove = 0;
		yMove = 0;
		if (!collisionWithLowerFloor) {
			yMove = speed;
		}
		else if (x < handler.getPlayer().getX()) {
			xMove = speed;
		}
		
		else if (x > handler.getPlayer().getX()) {
			xMove = -speed;
		}
		if (collisionWithRight)
			head_right = false;
		if (collisionWithLeft)
			head_right = true;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawString("HEALTH: " + Integer.toString(health), (int)(x-xOffset), (int)y-10);
		//g.setColor(Color.green);
		//g.fillRect((int) (x + bounds.x - xOffset), (int)y + bounds.y, bounds.width, bounds.height);
		g.drawImage(getCurrentAnimFrame(), (x-xOffset), (y), width, height, null);
	}
	
	private BufferedImage getCurrentAnimFrame() {
		if (head_right) {
			return animRight.getCurrentFrame();
		}else {
			return animLeft.getCurrentFrame();
		}
	}
}
