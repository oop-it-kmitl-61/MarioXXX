package game1.tiles;

import java.awt.image.BufferedImage;

import game1.gfx.Assets;

public class FloorTile extends Tile{

	public FloorTile(int id) {
		super(Assets.floor, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	
}
