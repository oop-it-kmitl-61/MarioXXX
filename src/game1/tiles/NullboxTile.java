package game1.tiles;

import java.awt.image.BufferedImage;

import game1.gfx.Assets;

public class NullboxTile extends Tile{

	public NullboxTile(int id) {
		super(Assets.nullbox, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	
}
