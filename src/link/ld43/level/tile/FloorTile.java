package link.ld43.level.tile;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.entity.Entity;
import link.ld43.entity.Player;
import link.ld43.level.Level;
import link.ld43.res.Art;

public class FloorTile extends Tile {
	
	public FloorTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
		Bitmap sprite;
		sprite = Art.getSprite(level.game.levelIndex, 0);
		if(level.getVisible(xt, yt)) {
			sprite.fill(1, 1, 18, 1, 0xff0000);
			sprite.fill(1, 1, 1, 18, 0xff0000);
			sprite.fill(1, 18, 18, 18, 0xff0000);
			sprite.fill(18, 1, 18, 18, 0xff0000);
		}
		screen.render(sprite, xt * 20, yt * 20);
	}
	
	public void steppedOn(Level level, int xt, int yt, Entity e) {
		if(e instanceof Player) {
			if(level.getVisible(xt, yt)) {
//				System.out.println("seen");
				level.playerSeen = true;
			}
		}
	}
	
}