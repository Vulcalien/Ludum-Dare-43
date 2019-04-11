package link.ld43.level.tile;

import link.ld43.Screen;
import link.ld43.entity.Entity;
import link.ld43.entity.Player;
import link.ld43.item.Item;
import link.ld43.level.Level;

public abstract class Tile {
	
	public static final Tile[] tiles = new Tile[128];
	
	public static final Tile
	floor = new FloorTile(0),
	wall = new WallTile(1),
	chest = new ChestTile(2),
	key = new KeyTile(3),
	forniture = new FornitureTile(4),
	teleport = new TeleportTile(5);
	
	public final byte id;
	
	public Tile(int id) {
		this.id = (byte) id;
		if(tiles[id] != null) throw new RuntimeException("Duplicate tile ids");
		tiles[id] = this;
	}
	
	public void tick(Level level, int xt, int yt) {
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
	}
	
	public boolean mayPass(Level level, int xt, int yt) {
		return true;
	}
	
	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
	}
	
	public void steppedOn(Level level, int xt, int yt, Entity e) {
	}
	
	public void bumpedInto(Level level, int xt, int yt, Entity e) {
	}
	
}