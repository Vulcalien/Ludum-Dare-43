package link.ld43.level.tile;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.entity.Player;
import link.ld43.item.Item;
import link.ld43.level.Level;
import link.ld43.res.Art;
import link.ld43.res.Sound;

public class TeleportTile extends Tile {
	
	public TeleportTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.floor.render(screen, level, xt, yt);
		Bitmap sprite = Art.getSprite(level.getData(xt, yt) - 1, 6);
		screen.render(sprite, xt * 20, yt * 20);
		
		boolean done = level.getData(xt, yt) <= level.game.passedLvls;
		if(done) {
			screen.render(Art.getSprite(5, 6), xt * 20, yt * 20);
		}
	}
	
	public boolean mayPass(Level level, int xt, int yt) {
		return false;
	}
	
	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		boolean mayPress = level.getData(xt, yt) - 1 <= level.game.passedLvls;
		if(!mayPress) return;
		level.game.loadLevel(level.getData(xt, yt));
		Sound.teleport.play();
	}
	
}