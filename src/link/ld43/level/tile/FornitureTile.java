package link.ld43.level.tile;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.level.Level;
import link.ld43.res.Art;

public class FornitureTile extends Tile {
	
	public FornitureTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.floor.render(screen, level, xt, yt);
		Bitmap sprite = Art.getSprite(level.getData(xt, yt), 7);
		screen.render(sprite, xt * 20, yt * 20);
	}
	
	public boolean mayPass(Level level, int xt, int yt) {
		return false;
	}
	
}