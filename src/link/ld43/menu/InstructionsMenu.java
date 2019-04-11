package link.ld43.menu;

import link.bitmap.Font;
import link.ld43.Game;
import link.ld43.InputHandler;
import link.ld43.Screen;

public class InstructionsMenu extends Menu {
	
	public InstructionsMenu(Game game) {
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
		
		font.write("Use WASD keys to move.", color, screen, 1, 1);
		font.write("Use E to interact/use objects.", color, screen, 1, 12);
		font.write("Use Enter key to skip menus.", color, screen, 1, 23);
		
		font.write("The goal is to steal spaghetti to sacrifice them to the Lord", color, screen, 1, 45);
		font.write("of spaghetti.", color, screen, 1, 56);
		font.write("They are hidden in chests or safes.", color, screen, 1, 67);
		font.write("Find a key to open them and you will pass the level.", color, screen, 1, 78);
		
		font.write("Avoid red areas: you would be visible to the cops.", screen, 1, 89);
		
		font.write("456C65..." + (char) 60 + (char) (51), 0x030303, screen, 1, screen.height - font.getHeight() - 1);
	}
	
}