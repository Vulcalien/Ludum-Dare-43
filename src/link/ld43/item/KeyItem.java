package link.ld43.item;

import link.ld43.Screen;
import link.ld43.res.Art;

public class KeyItem extends Item {
	
	public final int sprite;
	
	public KeyItem(int sprite) {
		this.sprite = sprite;
	}
	
	public void render(Screen screen, int x, int y) {
		screen.render(Art.getSprite(sprite, 4), x, y);
	}
	
}