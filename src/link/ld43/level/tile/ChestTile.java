package link.ld43.level.tile;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.entity.Player;
import link.ld43.item.Item;
import link.ld43.item.KeyItem;
import link.ld43.level.Level;
import link.ld43.res.Art;

public class ChestTile extends Tile {
	
	public ChestTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.floor.render(screen, level, xt, yt);
		Bitmap sprite = Art.getSprite(level.game.levelIndex, 2 + (level.levelWon ? 1 : 0));
		screen.render(sprite, xt * 20, yt * 20);
		if(level.levelWon) {
			screen.render(Art.getSprite(level.game.ticks / 20 % 3, 5), xt * 20, (yt - 1) * 20);
		}
	}
	
	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		if(item instanceof KeyItem) {
			level.levelWon = true;
//			Sound.pasta_grab.play();
		}
	}
	
	public boolean mayPass(Level level, int xt, int yt) {
		return false;
	}
	
}