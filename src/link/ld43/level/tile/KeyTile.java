package link.ld43.level.tile;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.entity.Entity;
import link.ld43.entity.Player;
import link.ld43.item.KeyItem;
import link.ld43.level.Level;
import link.ld43.res.Art;
import link.ld43.res.Sound;

public class KeyTile extends Tile {
	
	public KeyTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.floor.render(screen, level, xt, yt);
		Bitmap sprite = Art.getSprite(level.game.levelIndex, 4);
		screen.render(sprite, xt * 20, yt * 20);
	}
	
	public void bumpedInto(Level level, int xt, int yt, Entity e) {
		if(!(e instanceof Player)) return;
		level.player.item = new KeyItem(level.game.levelIndex);
		Sound.key_grab.play();
		level.setTile(Tile.floor, xt, yt);
	}
	
}