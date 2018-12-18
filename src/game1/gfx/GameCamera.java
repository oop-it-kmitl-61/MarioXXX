package game1.gfx;

import game1.Game;
import game1.Handler;
import game1.entities.Entity;

public class GameCamera {
	private Game game;
	private int xOffset;
	private int limit;
	public GameCamera(Game game, int xOffset, int yOffset) {
		this.game = game;
		this.xOffset = xOffset;
		limit = 0;
	}
	
	public void camFollowPlayer(Entity e) {
		xOffset = e.getX() - game.getWidth()/2;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getxOffset() {
		//return xOffset;
		return Math.max(0, xOffset);
	}
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
}
