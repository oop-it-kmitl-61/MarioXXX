package game1.worlds;

import java.awt.Graphics;

import game1.Handler;
import game1.entities.EntityManager;
import game1.entities.creatures.Guard;
import game1.entities.creatures.Guard2;
import game1.entities.creatures.Nisit;
import game1.entities.creatures.Player;
import game1.gfx.Assets;
import game1.tiles.Tile;
import game1.utils.Utils;

public class World2 extends World{
	private Handler handler;
	private int width, height, spawnX, spawnY;
	public int chapter;
	private int[][] tiles;
	public EntityManager entityManager;
	int xOffset;
	
	
	public World2(String name, Handler handler,String path, int player_score, int player_health) {
		super(name, handler, path, player_score, player_health, 2);
		entityManager = super.entityManager;
		handler.getGameCamera().setLimit(0);
		this.handler = handler;
		loadWorld(path);
		entityManager.getPlayer().setX(spawnX*64);
		entityManager.getPlayer().setY(spawnY*64);
		pass = false;
		entityManager.addEntity(new Guard(handler, 16*64, 9*64));
		entityManager.addEntity(new Guard(handler, 17*64, 9*64));
		entityManager.addEntity(new Guard(handler, 18*64, 9*64));
		entityManager.addEntity(new Guard(handler, 25*64, 9*64));
		entityManager.addEntity(new Guard(handler, 40*64, 11*64));
		entityManager.addEntity(new Guard(handler, 42*64, 11*64));
		entityManager.addEntity(new Guard(handler, 44*64, 11*64));
		entityManager.addEntity(new Guard(handler, 44*64, 9*64));
		entityManager.addEntity(new Guard(handler, 57*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 62*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 66*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 70*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 77*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 79*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 81*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 83*64, 11*64));
		entityManager.addEntity(new Guard2(handler, 85*64, 11*64));
		
		entityManager.addEntity(new Nisit(handler, 118*64, 5*64));
		entityManager.addEntity(new Nisit(handler, 124*64, 4*64));
		entityManager.addEntity(new Nisit(handler, 130*64, 5*64));
		entityManager.addEntity(new Guard2(handler, 136*64, 4*64));
		entityManager.addEntity(new Guard2(handler, 142*64, 6*64));
		
	}
	
	public Player getPlayer() {
		return entityManager.getPlayer();
	}
	
	public void tick() {
		entityManager.tick();
		player_pass();
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

	private void player_pass() {
		// TODO Auto-generated method stub
		if (handler.getPlayer().getX()-xOffset/64 >= 145*64)
			pass = true;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.back2, -xOffset, 0, 9600, 960, null);
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if (getTile(x, y).getID()==0)
					continue;
				getTile(x, y).render(g, (int) (x*Tile.TILEWIDTH - xOffset), (int) (y*Tile.TILEHEIGHT));
			}
		}
		for (int i=0;i<entityManager.getPlayer().crashed.size();i++)
			getTile(0, 0).render(g , (int) (entityManager.getPlayer().crashed.get(i)[0]*Tile.TILEWIDTH - xOffset), entityManager.getPlayer().crashed.get(i)[1]*Tile.TILEHEIGHT);
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
