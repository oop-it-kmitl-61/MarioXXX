package game1.tiles;

import java.awt.image.BufferedImage;

import game1.gfx.Assets;

public class BrickTile extends Tile{

	public BrickTile(int id) {
		super(Assets.brick, id);
	}

	public boolean isSolid() {
		return true;
	}
	
}
