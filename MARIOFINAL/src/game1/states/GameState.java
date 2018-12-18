package game1.states;

import java.awt.Graphics;



import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import game1.Handler;

import game1.worlds.World;
import game1.worlds.World1;
import game1.worlds.World2;
import game1.worlds.World3;

public class GameState extends State{
	
	public World world;
	private Handler handler;
	private Clip clip;
	private String name;
	
	public GameState(String player_name, Handler handler) {
		super(handler);
		name = player_name;
		this.handler = handler;
		world = new World1(name, handler, "world1.txt", 0, 5);
		handler.setWorld(world);
		play_back_sound();
	}
	private void play_back_sound() {
		// TODO Auto-generated method stub
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/sounds/back.wav")));
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		world.tick();
		player_check(handler.getPlayer().score, handler.getPlayer().health);
	}
	
	private void player_check(int player_score, int player_health) {
		// TODO Auto-generated method stub
		if (world.chapter==1) {
			if (handler.getPlayer().dead_nof) {
				player_dead();
				world = new World1(name, handler, "world1.txt", player_score, player_health-1);
				handler.setWorld(world);
			} else {
				if (world.pass) {
					world = new World2(name, handler, "world2.txt", player_score, player_health);
					handler.setWorld(world);
				}	
			}
		} else if (world.chapter==2) {
			if (handler.getPlayer().dead_nof) {
				player_dead();
				world = new World2(name, handler, "world2.txt", player_score, player_health-1);
				handler.setWorld(world);
			} else {
				if (world.pass) {
					world = new World3(name, handler, "world3.txt", player_score, player_health);
					handler.setWorld(world);
					
				}
			}
		} else if (world.chapter==3) {
			if (handler.getPlayer().dead_nof) {
				player_dead();
				world = new World3(name, handler, "world3.txt", player_score, player_health-1);
				handler.setWorld(world);
			} else {
				if (world.pass) {
					world = new World3(name, handler, "world3.txt", player_score, player_health);
					handler.setWorld(world);
				}	
			}
		}
		
		
	}

	private void player_dead() {
		// TODO Auto-generated method stub
		clip.stop();
		handler.getSoundPlayer().player("die.wav");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (handler.getPlayer().health<=0) {
			clip.stop();
			State.setState(new MenuState(handler));
			//handler.getGame().gameState.setState(handler.getGame().menuState);
		}
			
		else play_back_sound(); 
	}
	@Override
	public void render(Graphics g) {
		world.render(g);
		// TODO Auto-generated method stub
	}

}
