package game1.worlds;

import java.awt.Graphics;

import game1.Handler;
import game1.entities.EntityManager;
import game1.entities.creatures.Nisit;
import game1.entities.creatures.Player;
import game1.gfx.Assets;
import game1.tiles.Tile;
import game1.utils.Utils;

public class World1 extends World{
	private Handler handler;
	private int width, height, spawnX, spawnY;
	public int chapter, xOffset;
	private int[][] tiles;
	public EntityManager entityManager;
	
	
	public World1(String name, Handler handler,String path, int player_score, int player_health) {
		super(name, handler, path, player_score, player_health, 1);
		entityManager = super.entityManager;
		this.handler = handler;
		loadWorld(path);
		pass = false;
		entityManager.getPlayer().setX(spawnX*64);
		entityManager.getPlayer().setY(spawnY*64);
		entityManager.addEntity(new Nisit(handler, 20*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 21*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 29*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 39*64, 9*64));
		entityManager.addEntity(new Nisit(handler, 57*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 60*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 80*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 87*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 93*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 135*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 136*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 137*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 148*64, 5*64));
		entityManager.addEntity(new Nisit(handler, 152*64, 5*64));
		entityManager.addEntity(new Nisit(handler, 177*64, 11*64));
		entityManager.addEntity(new Nisit(handler, 178*64, 11*64));
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
		else if (xOffset>=10560)
			xOffset = 10560;
		
	}
	
	private void player_pass() {
		// TODO Auto-generated method stub
		if (handler.getPlayer().getX()-xOffset/64 >= 185*64)
			pass = true;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.back1, -xOffset, 0, 12160, 960, null);
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
