package game1.worlds;

import java.awt.Graphics;


import game1.Handler;
import game1.entities.EntityManager;
import game1.entities.creatures.OverBoss;
import game1.entities.creatures.Player;
import game1.tiles.Tile;
import game1.utils.Utils;

public abstract class World {
	@SuppressWarnings("unused")
	private Handler handler;
	public int width, height, spawnX, spawnY;
	public int chapter;
	private int[][] tiles;
	public EntityManager entityManager;
	public boolean pass;
	public OverBoss boss;
	public String name;
	
	
	public World(String name, Handler handler,String path, int player_score, int player_health, int chapter) {
		this.name = name;
		pass = false;
		this.handler = handler;
		this.chapter = chapter;
		entityManager = new EntityManager(handler, new Player(handler, 0, 0, player_score, player_health));
		loadWorld(path);
		entityManager.getPlayer().setX(spawnX*64);
		entityManager.getPlayer().setY(spawnY*64);	
	}
	
	public Player getPlayer() {
		return entityManager.getPlayer();
	}
	
	public void tick() {
		entityManager.tick();
		player_pass();
	}
	
	private void player_pass() {
		// TODO Auto-generated method stub
	}

	public abstract void render(Graphics g);

	public Tile getTile(int x, int y) {
		if (x<0 || y<0 || x>=width || y>=height)
			return Tile.not;
		Tile t = Tile.tiles[tiles[x][y]];
		if (t==null) {
			System.out.println("err");
			return Tile.nullboxTile;
		}
			
		return t;
	}
	
	private void loadWorld(String path) {

		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		tiles = new int[width][height];
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}	
	}
}
