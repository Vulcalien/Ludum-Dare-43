package link.ld43.menu;

import link.ld43.Game;
import link.ld43.Screen;

public abstract class Menu {
	
	public final Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void tick() {
	}
	
	public void render(Screen screen) {
	}
	
}