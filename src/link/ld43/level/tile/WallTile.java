package link.ld43.level.tile;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.level.Level;
import link.ld43.res.Art;

public class WallTile extends Tile {
	
	public WallTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
		Bitmap sprite;
		sprite = Art.getSprite(level.game.levelIndex, 1);
		screen.render(sprite, xt * 20, yt * 20);
	}
	
	public boolean mayPass(Level level, int xt, int yt) {
		return false;
	}
	
}