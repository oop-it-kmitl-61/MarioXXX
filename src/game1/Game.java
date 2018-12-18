package game1;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game1.Display.*;
import game1.gfx.Assets;
import game1.gfx.GameCamera;
import game1.input.KeyManager;

import game1.states.MenuState;
import game1.states.State;
import game1.utils.SoundPlayer;

public class Game implements Runnable{
	private Display display;
	public int fps;
	int width, height;
	public String title;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	private KeyManager keyManager;
	private GameCamera gameCamera;
	public Handler handler;
	public double delta2;
	public State menuState;
	public SoundPlayer soundPlayer;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		handler = new Handler(this);
		gameCamera = new GameCamera(this, 0, 0);
		soundPlayer = new SoundPlayer(handler);
		Assets.init();
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//Drawing
		if(State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {

		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks=0;
		delta2 = delta;
		
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			delta2 = delta;
			timer += now-lastTime;
			lastTime = now;
			if(delta >=1) {
//				if (delta<2)
//					handler.getPlayer().ready = true;
//					System.out.println(delta);
				tick();
				render();
				ticks++;
				if (delta>11)
					delta-=10;
				else delta--;
				delta2 = delta;
			}
			if (timer >= 1000000000) {
//				System.out.println("Ticks and Frames: "+ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
 	
	
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
