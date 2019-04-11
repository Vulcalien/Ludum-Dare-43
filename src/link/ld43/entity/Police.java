package link.ld43.entity;

import link.bitmap.Bitmap;
import link.ld43.Screen;
import link.ld43.level.Level;
import link.ld43.res.Art;

public class Police extends Mob implements Seeker {
	
	public final int range = 8;
	public int ticks = 0;
	public int lastMove;
	public int stunned = 120;
	
	public int lastRotate;
	public int rotateDelay = -1;
	public final boolean[] directions = new boolean[4];
	public boolean moves;
	
	public Police(Level level, byte[] attributes) {
		super(level);
		xr = 8;
		yr = 8;
		
		if(attributes == null) return;
		int readIndex = 0;
		while(readIndex < attributes.length) {
			int attributeId = attributes[readIndex++];
			if(attributeId == 0) {//position + first dir
				x = attributes[readIndex++] * 20 + 10;
				y = attributes[readIndex++] * 20 + 10;
				dir = attributes[readIndex++];
			} else if(attributeId == 1) {//direction changer
				rotateDelay = attributes[readIndex++] * 20;//data value is 1/3 of a second
				directions[0] = attributes[readIndex++] != 0;
				directions[1] = attributes[readIndex++] != 0;
				directions[2] = attributes[readIndex++] != 0;
				directions[3] = attributes[readIndex++] != 0;
			}
		}
		moves = directions[0] || directions[1] || directions[2] || directions[3];
	}
	
	public void tick() {
		ticks++;
		if(!level.playerSeen) {
			if(moves && ticks - lastRotate >= rotateDelay) {//rotate
				lastRotate = ticks;
				boolean rotated = false;
				while(!rotated) {
					dir += 1;
					if(dir == 4) dir = 0;
					if(directions[dir]) {
						rotated = true;
					}
				}
			}
			return;
		}
		
		if(stunned > 0) {
			stunned--;
			return;
		}
		
		int xm = 0, ym = 0;
		Player player = level.player;
		int xdiff = x - player.x;
		int ydiff = y - player.y;
		
		if(xdiff > 0) xm = -1;
		else if(xdiff < 0) xm = 1;
		
		if(ydiff > 0) ym = -1;
		else if(ydiff < 0) ym = 1;
		
		move(xm, ym);
	}
	
	public void render(Screen screen) {
		Bitmap sprite = Art.getSprite(16 + dir, 6 + 2 * (moveCount / 10 % 3), 1, 2);
		screen.render(sprite, x - sprite.width / 2, y - sprite.height / 2 - 10);
	}
	
	public void touchedBy(Entity e) {
		if(e instanceof Player) {
			e.touchedBy(this);
		}
	}
	
	public void makeVisible() {
		if(level.playerSeen) return;
		int xm = 0, ym = 0;
		if(dir == 0) ym = -1;
		else if(dir == 1) xm = -1;
		else if(dir == 2) ym = 1;
		else if(dir == 3) xm = 1;
		
		int x0 = x / 20 - (ym != 0 ? 2 : 0);
		int y0 = y / 20 - (xm != 0 ? 2 : 0);
		int x1 = x / 20 + (ym != 0 ? 2 : 0);
		int y1 = y / 20 + (xm != 0 ? 2 : 0);
		
		for(int xi = x0; xi <= x1; xi++) {
			yi: for(int yi = y0; yi <= y1; yi++) {
				for(int i = 0; i < range; i++) {
					int xt = xi + i * xm;
					int yt = yi + i * ym;
					if(xt < 0 || xt >= level.width || yt < 0 || yt >= level.height) continue;
					if(level.getTile(xt, yt).mayPass(level, xt, yt)) level.setVisible(true, xt, yt);
					else continue yi;
				}
			}
		}
	}
	
}