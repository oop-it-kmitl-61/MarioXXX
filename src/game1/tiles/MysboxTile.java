package game1.tiles;

import java.awt.image.BufferedImage;

import game1.gfx.Animation;
import game1.gfx.Assets;

public class MysboxTile extends Tile{
	public MysboxTile(int id) {
		super(Assets.mysBox[0], id);
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public boolean isScr() {
		return true;
	}
	
}
