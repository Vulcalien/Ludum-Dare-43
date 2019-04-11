package link.ld43.level;

import java.io.IOException;

import link.ld43.Game;

public abstract class Loader {
	
	public static Level getLevel(Game game, int id) {
		try {
			Level level = new Level(game, Loader.class.getResourceAsStream("/level" + id + ".lvl"), Loader.class.getResourceAsStream("/level" + id + ".entity"));
			if(id == 0) level.bgColor = 0x98c8ff;
			return level;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}