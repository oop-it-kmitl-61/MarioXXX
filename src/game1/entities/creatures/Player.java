package game1.entities.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import game1.Handler;
import game1.entities.bullet;
import game1.gfx.Animation;
import game1.gfx.Assets;
	
public class Player extends Creature{
    public static int width = 64;
	public static int height = 64;
	public static int speed = 10;
	private int jump_count;
	private int gun_cd = 0;
	private boolean jumpping;
	private Animation animRight;
	private Animation animLeft;
	public boolean jump_com = false;
	public int active_point;
	public int dead_col;
	public boolean ready;
	public boolean dead, dead_nof;
	boolean hav_gun;
	private int xOffset;
	public boolean over;
	public int temp_score;
	
	public Player(Handler handler,int x, int y, int score, int health) {
		super("Player", handler, x, y, width, height, score);
		this.score = score;
		temp_score = -2;
		this.health = health;
		jump_count = 0;
		this.handler = handler;
		jumpping = false;
		dead = false;
		ready = false;
		hav_gun = true;
		bounds.x = 5;
		bounds.y = 6;
		bounds.width = 48;
		bounds.height = 58;
		animRight = new Animation(150, Assets.player_right);
		animLeft = new Animation(150, Assets.player_left);
		dead_col = 5;
		over = false;
	}

	@Override
	public void tick() {
		getxOffset();
		if (handler.getGame().delta2 < 10)
			ready = true;
		if (ready) {
			hitbox = new Rectangle(x+bounds.x - xOffset, y+bounds.y+30, bounds.width, bounds.height-30);
			animRight.tick();
			animLeft.tick();
			getInput();
			move();
			handler.getGameCamera().camFollowPlayer(this);
			if(gun_cd > 0)
				gun_cd--;
			if (y > handler.getGame().getHeight()+128)
				dead = true;
			active_point();
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

	private void active_point() {
		// TODO Auto-generated method stub
//		active_point = -9999;   //for debug npc
		if (!ready)
			active_point = -1;
		else if (handler.getGameCamera().getxOffset()==0)
			active_point = 1700;
		else active_point =  x-xOffset+900;
	}

	public void getInput() {
		xMove = 0;
		yMove = 0;
		if((handler.getKeyManager().up && collisionWithLowerFloor && !jumpping)||(jump_com)) {
			jump_com = false;
			jumpping = true;
			jump_count = 20;
			handler.getSoundPlayer().player("jump.wav");
		}
		if (jump_count>0 && jumpping) {
			if (collisionWithUpperFloor)
				jump_count = 0;
			yMove = -speed;
			jump_count--;
		}
		else {
			jumpping = false;
			yMove = gravity;
		}
		if(handler.getKeyManager().down)
			yMove =+speed;
		if(handler.getKeyManager().left)
			xMove =-speed;
		if(handler.getKeyManager().right)
			xMove =speed;
		if(handler.getKeyManager().space) {
			if (gun_cd == 0) {
				handler.getSoundPlayer().player("laser_fire.wav");
				if (head_right)
					handler.getWorld().entityManager.addEntity(new bullet(handler, 18, (int)(x+5), (int)(y+33)));
				else if(!head_right)
					handler.getWorld().entityManager.addEntity(new bullet(handler, -18, (int)(x+5), (int)(y+33)));
				gun_cd = 50;
			} else gun_cd--;
			
		}
			
	}

	@Override
	public void render(Graphics g) {
		if (!ready) {
			g.setFont(new Font("Impact", Font.PLAIN, 100));
			g.setColor(Color.black);
			g.fillRect(0, 0, 1600, 900);
			g.setColor(Color.orange);
			g.drawString("LOADING", handler.getGame().getWidth()/2-150, (handler.getGame().getHeight()/2)+64);
			g.setColor(Color.YELLOW);
			g.drawString("LOADING", handler.getGame().getWidth()/2-150-4, (handler.getGame().getHeight()/2)+64-4);
		} else {
			g.setFont(new Font("Impact", Font.PLAIN, 50));
			g.setColor(Color.orange);
			g.drawString("LIVES:", 50*5-4, 64-4);
			g.drawString("SCORES: " + score, 50*15-4, 64-4);
			g.drawString("Name: " + handler.getWorld().name, 50*25-4, 64-4);
			//g.drawString("X: " + x/64, 50*23-4, 64-4);
			//g.drawString("Y: " + y/64, 50*26-4, 64-4);
			g.setColor(Color.YELLOW);
			g.drawString("LIVES:", 50*5, 64);
			g.drawString("SCORES: " +  + score, 50*15, 64);
			g.drawString("NAME: " + handler.getWorld().name, 50*25, 64);
			//g.drawString("X: " + x/64, 50*23, 64);
			//g.drawString("Y: " + y/64, 50*26, 64);
			//g.drawString("xOFFSET: " + xOffset, 50*23, 150);
			//g.fillRect(x+bounds.x - xOffset, y+bounds.y+30, bounds.width, bounds.height-30); //draw hitbox
			g.drawImage(getCurrentAnimFrame(), (x-xOffset), (int)(y), width, height, null);
			for (int i=1;i<=health;i++)
				g.drawImage(Assets.player_head_right, (i*50)+(55*6), 20, 50, 50, null);
			if (over) {
				health = -1;
				dead_col--;
				if (dead_col == 0)
					dead_nof = true;
				g.setFont(new Font("Impact", Font.PLAIN, 100));
				g.setColor(Color.black);
				g.fillRect(0, 0, 1600, 900);
				g.setColor(Color.orange);
				g.drawString("VICTORY!", handler.getGame().getWidth()/2-200, (handler.getGame().getHeight()/2));
				g.drawString("SCORE: " + Integer.toString(temp_score), handler.getGame().getWidth()/2-200, (handler.getGame().getHeight()/2)+94);
				g.setColor(Color.YELLOW);
				g.drawString("VICTORY!", handler.getGame().getWidth()/2-200-4, (handler.getGame().getHeight()/2)-4);
				g.drawString("SCORE: " + Integer.toString(temp_score), handler.getGame().getWidth()/2-200-4, (handler.getGame().getHeight()/2)+94-4);
				savescore();
			}
			if (dead) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 1600, 900);
				for (int i=1;i<health;i++)
					g.drawImage(Assets.player_head_right, (i*80)+(480), 220, 80, 80, null);
				g.drawImage(Assets.player_head_right, handler.getGame().getWidth()/2-128, handler.getGame().getHeight()/2-100, 200, 200, null);
				g.setFont(new Font("Impact", Font.PLAIN, 100));
				dead_col--;
				
				
				
				if (health<=0) {
					g.setColor(Color.black);
					g.fillRect(0, 0, 1600, 900);
					g.setColor(Color.orange);
					g.drawString("GAME OVER!", handler.getGame().getWidth()/2-200, (handler.getGame().getHeight()/2));
					g.drawString("SCORE: " + Integer.toString(temp_score), handler.getGame().getWidth()/2-200, (handler.getGame().getHeight()/2)+94);
					g.setColor(Color.YELLOW);
					g.drawString("GAME OVER!", handler.getGame().getWidth()/2-200-4, (handler.getGame().getHeight()/2)-4);
					g.drawString("SCORE: " + Integer.toString(temp_score), handler.getGame().getWidth()/2-200-4, (handler.getGame().getHeight()/2)+94-4);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					savescore();
					
				}
				if (dead_col==0) 
					dead_nof = true;
				
			}
			
		}
		
	}
	
	private void savescore() {
		try {
	        BufferedReader br = new BufferedReader(new FileReader("score.txt"));
	        String line;
	        String temp_line = null;
	        StringBuffer inputBuffer = new StringBuffer();
	        boolean shiftit = false;
	        while ((line = br.readLine()) != null) {
	        	String[] part = line.split(" ");
	        	
		        if (!shiftit && (score> Integer.parseInt(part[1]))) {
		        	temp_line = line;
		        	shiftit = true;
		        	inputBuffer.append(handler.getWorld().name + " " + Integer.toString(score));
		        	inputBuffer.append('\n');		        	
		        	if (temp_score == -2)
		        		temp_score = score;
		        	score = -1;
		        	continue;
		        }
		        if (shiftit) {
		        	inputBuffer.append(temp_line);
		        	temp_line = line;
		        } else {inputBuffer.append(line);}	
		        
	            inputBuffer.append('\n');
	        }
	        String inputStr = inputBuffer.toString();
	        br.close();

	        FileOutputStream fileOut = new FileOutputStream("score.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	}

	private BufferedImage getCurrentAnimFrame() {
		if (yMove < 0 && head_right)
			return Assets.player_jump_right;
		else if (yMove < 0 && !head_right)
			return Assets.player_jump_left;
		else if (xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animRight.getCurrentFrame();
		}else {
			if (hav_gun) {
				if (head_right)
					return Assets.player_head_right_gun;
				else if (!head_right)
					return Assets.player_head_left_gun;
				} else {
					if (head_right)
						return Assets.player_head_right;
					else if (!head_right)
						return Assets.player_head_left;
				}
		}
		return Assets.player_head_right;
	}
	

}
