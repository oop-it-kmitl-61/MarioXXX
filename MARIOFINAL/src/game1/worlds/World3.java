package game1.worlds;

import java.awt.Graphics;

import game1.Game;
import game1.Handler;
import game1.entities.Entity;
import game1.entities.EntityManager;
import game1.entities.bullet;
import game1.entities.creatures.Guard;
import game1.entities.creatures.Guard2;
import game1.entities.creatures.Nisit;
import game1.entities.creatures.OverBoss;
import game1.entities.creatures.Player;
import game1.gfx.Animation;
import game1.gfx.Assets;
import game1.tiles.Tile;
import game1.utils.Utils;

public class World3 extends World{
	private Handler handler;
	private int width, height, spawnX, spawnY;
	public int chapter;
	private int[][] tiles;
	public EntityManager entityManager;
	private int xOffset;
	
	
	public World3(String name, Handler handler,String path, int player_score, int player_health) {
		super(name, handler, path, player_score, player_health, 3);
		entityManager = super.entityManager;
		handler.getGameCamera().setLimit(2);
		this.handler = handler;
		loadWorld(path);
		entityManager.getPlayer().setX(spawnX*64);
		entityManager.getPlayer().setY(spawnY*64);
		pass = false;
		chapter = 4;
		entityManager.addEntity(new Guard2(handler, 19*64, 7*64));
		entityManager.addEntity(new Guard2(handler, 64, -1));
		entityManager.addEntity(new Guard2(handler, 64*64, 6*64));
		entityManager.addEntity(new Guard2(handler, 70*64, 4*64));
		entityManager.addEntity(new Guard2(handler, 64*64, 4*64));
		entityManager.addEntity(new Guard2(handler, 73*64, 6*64));
		entityManager.addEntity(new Guard2(handler, 78*64, 7*64));
		entityManager.addEntity(new Guard2(handler, 85*64, 7*64));
		entityManager.addEntity(new Guard2(handler, 86*64, 7*64));
		
		entityManager.addEntity(new OverBoss(handler, 146*64, 10*64));
		entityManager.addEntity(new Guard2(handler, 144*64, 10*64));
		entityManager.addEntity(new Guard2(handler, 142*64, 10*64));
		entityManager.addEntity(new Guard2(handler, 140*64, 10*64));
		entityManager.addEntity(new Guard2(handler, 138*64, 10*64));
		entityManager.addEntity(new Nisit(handler, 136*64, 10*64));
		entityManager.addEntity(new Nisit(handler, 134*64, 10*64));
	}
	
	public Player getPlayer() {
		return entityManager.getPlayer();
	}
	
	public void tick() {
		entityManager.tick();
		getxOffset();
	}
	
	private void getxOffset() {
		// TODO Auto-generated method stub
		xOffset = handler.getGameCamera().getxOffset();
		if (xOffset<=0)
			xOffset = 0;
		else if (xOffset>=8000)
			xOffset = 8000;
		
	}
	

	public void render(Graphics g) {
		g.drawImage(Assets.back3, (int) - xOffset, 0, 9600, 960, null);
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if (getTile(x, y).getID()==0)
					continue;
				getTile(x, y).render(g, (int) (x*Tile.TILEWIDTH - xOffset), (int) (y*Tile.TILEHEIGHT));
			}
		}
		for (int i=0;i<entityManager.getPlayer().crashed.size();i++)
			getTile(0, 0).render(g , (int) (entityManager.getPlayer().crashed.get(i)[0]*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), entityManager.getPlayer().crashed.get(i)[1]*Tile.TILEHEIGHT);
		entityManager.getPlayer().render(g);
		entityManager.render(g);
	}

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
