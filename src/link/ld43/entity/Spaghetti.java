package link.ld43.entity;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.level.Level;
import link.ld43.res.Art;

public class Spaghetti extends Entity {
	
	public int ticks = 0;
	
	public Spaghetti(Level level, byte[] position) {
		super(level);
		xr = 12;
		yr = 12;
		
		if(position == null) return;
		x = position[0] * 20 + 10;
		y = position[1] * 20 + 10;
	}
	
	public void tick() {
		ticks++;
	}
	
	public void render(Screen screen) {
		Bitmap sprite = Art.getSprite(16 + 2 * (ticks / 20 % 2), 12, 2, 2);
		screen.render(sprite, x - sprite.width / 2, y - sprite.height / 2);
		
		screen.fontMedium.write("Steal pasta and", 0x000000, screen, x - screen.xOffset, y - 51 - screen.yOffset);
		screen.fontMedium.write("sacrifice it to me", 0x000000, screen, x - screen.xOffset, y - 40 - screen.yOffset);
		screen.fontMedium.write("or you will die!", 0x000000, screen, x - screen.xOffset, y - 29 - screen.yOffset);
	}
	
	public boolean blocks(Entity e) {
		return true;
	}
	
}