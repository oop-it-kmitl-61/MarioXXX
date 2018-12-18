package game1;

import game1.entities.creatures.Player;
import game1.gfx.GameCamera;
import game1.input.KeyManager;
import game1.utils.SoundPlayer;
import game1.worlds.World;

public class Handler {
	private Game game;
	private World world;
	
	public Player getPlayer() {
		return this.getWorld().getPlayer();
	}
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public SoundPlayer getSoundPlayer() {
		return game.soundPlayer;
	}

	
}
