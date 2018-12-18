package game1.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	public static Tile[] tiles = new Tile[256];
	public static Tile not = new not(0);
	public static Tile floorTile = new FloorTile(1);
	public static Tile nullboxTile = new NullboxTile(2);
	public static Tile mysboxTile = new MysboxTile(3);
	public static Tile brickTile = new BrickTile(4);
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public boolean isScr() {
		return false;
	}
	
	public int getID() {
		return id;
	}
}
