package link.ld43;

import java.util.Comparator;
import java.util.List;

import link.bitmap.Bitmap;
import link.bitmap.Font;
import link.ld43.entity.Entity;
import link.ld43.entity.Player;
import link.ld43.level.Level;

public class Screen extends Bitmap {
	
	private final Game game;
	public final Font fontLarge;
	public final Font fontMedium;
	
	public int xOffset, yOffset;
	
	public final Comparator<Entity> sorter = new Comparator<Entity>() {
		public int compare(Entity e1, Entity e2) {
			if(e1.y < e2.y) return -1;
			else if(e1.y > e2.y) return 1;
			else return 0;
		}
	};
	
	public Screen(Game game, int width, int height) {
		super(width, height);
		this.game = game;
		this.fontMedium = new Font(Screen.class.getResourceAsStream("/LinkWriter.lwfont"));
		this.fontLarge = fontMedium.getScaled(2);
		setBackground(0x204080, 0x804020);
	}
	
	public void render() {
		clear(game.level.bgColor);
		if(game.menu != null) {
			game.menu.render(this);
			return;
		}
		Level level = game.level;
		Player play = game.player;
		
		int xp = play.x;
		int yp = play.y;
		
		xOffset = xp - 240;
		yOffset = yp - 160;
		
		int xt0 = xOffset / 20 - 2;// 2 tiles tall players and mobs
		int yt0 = yOffset / 20 - 2;
		int xt1 = xOffset / 20 + 24 + 2;
		int yt1 = yOffset / 20 + 16 + 2;
		
		for(int xt = xt0; xt < xt1; xt++) {
			if(xt < 0 || xt >= level.width) continue;
			for(int yt = yt0; yt < yt1; yt++) {
				if(yt < 0 || yt >= level.height) continue;
				level.getTile(xt, yt).render(this, level, xt, yt);
			}
		}
		List<Entity> entities = level.getEntitiesInTile(xt0, yt0, xt1, yt1);
		entities.sort(sorter);
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(this);
		}
		
		fill(width - 40, 0, width - 1, height - 1, 0x555555);
		fill(width - 31, (height - 21) / 2, width - 10, (height + 21) / 2, 0xcccccc);
		
		if(level.player.item != null) {
			level.player.item.render(this, width - 30 + xOffset, (height - 20) / 2 + yOffset);
		}
	}
	
	public void render(Bitmap sprite, int x, int y) {
		draw(sprite, x - xOffset, y - yOffset);
	}
	
}