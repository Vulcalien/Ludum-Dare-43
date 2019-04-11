package link.ld43.entity;

import link.bitmap.Bitmap;
import link.ld43.InputHandler;
import link.ld43.Screen;
import link.ld43.item.Item;
import link.ld43.level.Level;
import link.ld43.menu.LoseMenu;
import link.ld43.res.Art;
import link.ld43.res.Sound;

public class Player extends Mob {
	
	private final InputHandler input;
	
	public int ticks = 0;
	public Item item;
	
	public int lastMove;
	
	public Player(Level level, InputHandler input, byte[] position) {
		super(level);
		this.input = input;
		xr = 7;
		yr = 8;
		
		dir = 2;
		
		if(position == null) return;
		x = position[0] * 20 + 10;
		y = position[1] * 20 + 10;
	}
	
	public void tick() {
		ticks++;
		
		int xm = 0, ym = 0;
		int speed = 1;
		if(input.up.pressed()) ym -= speed;
		if(input.left.pressed()) xm -= speed;
		if(input.down.pressed()) ym += speed;
		if(input.right.pressed()) xm += speed;
		
		if(move(xm, ym) && (xm != 0 || ym != 0)) {
			lastMove = ticks;
		}
		
		if(input.interact.pressed()) {
			input.interact.toggle();
			interact();
		}
	}
	
	public void render(Screen screen) {
		if(ticks - lastMove >= 30) {
			moveCount = 0;
		}
		Bitmap sprite = Art.getSprite(16 + dir, 2 * (moveCount / 10 % 3), 1, 2);
		screen.render(sprite, x - sprite.width / 2, y - sprite.height / 2 - 10);
	}
	
	public void touchedBy(Entity e) {
		if(e instanceof Police) {
			level.game.menu = new LoseMenu(level.game);
			Sound.caught.play();
		}
	}
	
	private void interact() {
		int range = 15;
		if(dir == 0) interact(x, y - range);
		else if(dir == 1) interact(x - range, y);
		else if(dir == 2) interact(x, y + range);
		else if(dir == 3) interact(x + range, y);
	}
	
	private void interact(int x, int y) {
		int xt = x / 20;
		int yt = y / 20;
		if(xt < 0 || xt >= level.width || yt < 0 || yt >= level.height) return;
		level.getTile(xt, yt).interactOn(level, xt, yt, this, item);
	}
	
}