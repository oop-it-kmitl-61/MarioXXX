package game1.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import game1.Handler;
import game1.entities.creatures.Player;

public class EntityManager {
	private Handler handler;
	private Player player;
	public ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Player player) {
		this.setHandler(handler);
		this.player = player;
		entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		for(int i=0;i<entities.size();i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		player.tick();
	}
	
	public void render(Graphics g) {
		for(int i=0;i<entities.size();i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		player.render(g);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
