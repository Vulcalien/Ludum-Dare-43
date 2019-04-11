package link.ld43.menu;

import link.bitmap.Font;
import link.ld43.Game;
import link.ld43.InputHandler;
import link.ld43.Screen;

public class CreditsMenu extends Menu {
	
	public CreditsMenu(Game game) {
		super(game);
	}
	
	public void tick() {
		InputHandler input = game.input;
		if(input.enter.pressed()) {
			game.menu = new StartMenu(game);
			input.enter.toggle();
		}
	}
	
	public void render(Screen screen) {
		screen.clear(0);
		Font font = screen.fontMedium;
		int color = 0xffffff;
		
		font.write("Author and programmer: Link24122003", color, screen, 1, 1);
		font.write("Graphic Artists: Link, Zarqus, Ax3l, Haythan", color, screen, 1, 12);
		font.write("Music: Zarqus, Link", color, screen, 1, 23);
	}
	
}