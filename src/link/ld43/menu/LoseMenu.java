package link.ld43.menu;

import link.bitmap.Font;
import link.ld43.Game;
import link.ld43.InputHandler;
import link.ld43.Screen;

public class LoseMenu extends Menu {
	
	public LoseMenu(Game game) {
		super(game);
	}
	
	public void tick() {
		InputHandler input = game.input;
		if(input.enter.pressed()) {
			input.enter.toggle();
			game.loadLevel(0);
			game.menu = null;
		}
	}
	
	public void render(Screen screen) {
		screen.clear(0);
		Font font = screen.fontLarge;
		
		String text = "You were caught...";
		font.write(text, 0xffffff, screen, (screen.width - font.lengthOf(text)) / 2, (screen.height - font.getHeight()) / 2);
	}
	
}