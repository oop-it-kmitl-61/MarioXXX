package game1.entities.creatures;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import game1.Handler;
import game1.entities.Entity;
import game1.tiles.Tile;

public abstract class Creature extends Entity {
	public int xMove, yMove;
	public int health, score;
	
	protected boolean collisionWithLowerFloor;
	protected boolean collisionWithUpperFloor;
	protected boolean collisionWithLeft;
	protected boolean collisionWithRight;
	protected boolean dead;
	protected static int gravity = 10;
	protected boolean head_right;
	protected int xOffset;
	protected Rectangle head;
	
	public ArrayList<int[]> crashed;
	public int crashedX;
	public int crashedY;
	
	boolean dupe;
	
	public Creature(String name, Handler handler,int x, int y, int width, int height, int score) {
		super(name, handler, x, y, width, height);
		crashed = new ArrayList<int[]>();
		head_right = true;
		health = 10;
		xMove = 0;
		yMove = 0;
		this.score = score;
		dead = false;
		hitbox = new Rectangle(x+bounds.x - handler.getGameCamera().getxOffset(), y+bounds.y, bounds.width, bounds.height);
	}
	
	public void move() {
		moveX();
		moveY();
	}
	
	public void moveX() {
		collisionWithLowerFloor = false;
		collisionWithUpperFloor = false;
		collisionWithLeft = false;
		collisionWithRight = false;
		if (xMove > 0) {
			head_right = true;
			int tx = (int) (x+bounds.x+bounds.width+xMove) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT)&&!collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				collisionWithRight = false;
				x += xMove;
			}else {
				collisionWithRight = true;
				x = tx*Tile.TILEWIDTH - bounds.x - bounds.width-1;
			}
		}else if(xMove <0) {
			head_right = false;
			int tx = (int) (x+xMove) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT)&&!collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				collisionWithLeft = false;
				x += xMove;
			}else {
				collisionWithLeft = true;
				x = tx*Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if (yMove < 0) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEWIDTH;
			if(!collisionWithTile((int)(x + bounds.x) / Tile.TILEHEIGHT, ty)&&!collisionWithTile((int)(x + bounds.x+bounds.width) / Tile.TILEHEIGHT, ty)) {
				y += yMove;
				collisionWithUpperFloor = false;
			}else {
				if (isScr((int)(x + bounds.x + bounds.x/2) / Tile.TILEHEIGHT, ty)) {
					int[] temp = new int[] {(int)(x + bounds.x + bounds.x/2) / Tile.TILEHEIGHT, ty};
					dupe = false;
					for(int i=0;i<crashed.size();i++) {
						if (Arrays.equals(temp, crashed.get(i)))
							dupe = true;
					}
					if(!dupe) {
						crashed.add(temp);
						score += 100;
						handler.getSoundPlayer().player("coin.wav");
					}
				}
					
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
				collisionWithUpperFloor = true;
			}
		}else if(yMove > 0) {
			int ty = (int) (y + yMove + bounds.y + bounds.getHeight()) / Tile.TILEWIDTH;
			if(!collisionWithTile((int)(x + bounds.x) / Tile.TILEHEIGHT, ty)&&!collisionWithTile((int)(x + bounds.x+bounds.width) / Tile.TILEHEIGHT, ty)) {
				y += yMove;
				collisionWithLowerFloor = false;
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height-1;
				collisionWithLowerFloor = true;
			}
		}
	}
	

	protected boolean isScr(int x, int y) {
		return handler.getWorld().getTile(x, y).isScr();
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
}
