package link.ld43.menu;

import link.bitmap.Font;
import link.ld43.Game;
import link.ld43.InputHandler;
import link.ld43.Screen;

public class WinMenu extends Menu {
	
	public final boolean wonGame;
	
	public WinMenu(Game game) {
		super(game);
		wonGame = game.passedLvls == game.levels;
	}
	
	public void tick() {
		InputHandler input = game.input;
		if(wonGame) return;
		if(input.enter.pressed()) {
			input.enter.toggle();
			game.loadLevel(0);
			game.menu = null;
		}
	}
	
	public void render(Screen screen) {
		screen.clear(0);
		Font fontM = screen.fontMedium;
		Font fontL = screen.fontLarge;
		String text;
		
		if(wonGame) {
			text = "You Won!";
			fontL.write(text, 0xffffff, screen, (screen.width - fontL.lengthOf(text)) / 2, (screen.height - fontL.getHeight()) / 2);
		} else {
			text = "You passed the Level!";
			fontL.write(text, 0xffffff, screen, (screen.width - fontL.lengthOf(text)) / 2, (screen.height - fontL.getHeight()) / 2);
			
			text = "Passed levels: " + game.passedLvls + "/" + game.levels;
			fontM.write(text, 0xcccccc, screen, (screen.width - fontM.lengthOf(text)) / 2, (screen.height + fontL.getHeight()) / 2 + 1);
		}
	}
	
}