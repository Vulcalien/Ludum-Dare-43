package link.ld43.menu;

import link.bitmap.Bitmap;
import link.bitmap.Font;
import link.ld43.Game;
import link.ld43.InputHandler;
import link.ld43.Screen;
import link.ld43.res.Art;

public class StartMenu extends Menu {
	
	public final int elements = 3;
	public int focusIndex = 1;
	
	public StartMenu(Game game) {
		super(game);
	}
	
	public void tick() {
		InputHandler input = game.input;
		
		if(input.enter.pressed()) {
			if(focusIndex == 0) game.menu = new CreditsMenu(game);
			else if(focusIndex == 1) game.menu = null;
			else if(focusIndex == 2) game.menu = new InstructionsMenu(game);
			
			input.enter.toggle();
			return;
		}
		
		boolean left = false;
		if(input.left.pressed()) {
			left = true;
			input.left.toggle();
		}
		
		boolean right = false;
		if(input.right.pressed()) {
			right = true;
			input.right.toggle();
		}
		
		if(left) {
			if(focusIndex > 0) {
				focusIndex--;
			} else {
				focusIndex = elements - 1;
			}
		}
		if(right) {
			if(focusIndex < elements - 1) {
				focusIndex++;
			} else {
				focusIndex = 0;
			}
		}
	}
	
	public void render(Screen screen) {
		screen.clear(0);
		Font fontM = screen.fontMedium;
		Font fontL = screen.fontLarge;
		
		String text = "Credits";
		fontM.write(text, focusIndex == 0 ? 0xffffff : 0xaaaaaa, screen, 1, screen.height - fontM.getHeight() - 1);
		
		text = "Start Game";
		fontL.write(text, focusIndex == 1 ? 0xffffff : 0xaaaaaa, screen, (screen.width - fontL.lengthOf(text)) / 2, (screen.height - fontL.getHeight() ) / 2);
		
		text = "(Press Enter)";
		fontM.write(text, 0xaaaaaa, screen, (screen.width - fontM.lengthOf(text)) / 2, (screen.height + fontL.getHeight()) / 2 + 1);
		
		text = "Instructions";
		fontM.write(text, focusIndex == 2 ? 0xffffff : 0xaaaaaa, screen, screen.width - fontM.lengthOf(text) - 1, screen.height - fontM.getHeight() - 1);
		
		Bitmap spaghetti = Art.getSprite(16 + 2 * (game.ticks / 20 % 2), 12, 2, 2).getScaled(2);
		screen.render(spaghetti, (screen.width - spaghetti.width) / 2, (screen.height) / 2 - spaghetti.height);
	}
	
}