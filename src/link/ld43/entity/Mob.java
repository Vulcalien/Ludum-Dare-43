package link.ld43.entity;

import link.ld43.level.Level;

public class Mob extends Entity {
	
	public int dir;
	public int moveCount = 0;
	
	public Mob(Level level) {
		super(level);
	}
	
	public boolean move(int xm, int ym) {
		if(xm != 0 || ym != 0) moveCount++;
		if(ym < 0) dir = 0;
		else if(ym > 0) dir = 2;
		else if(xm < 0) dir = 1;
		else if(xm > 0) dir = 3;
		return super.move(xm, ym);
	}
	
	public boolean blocks(Entity e) {
		return true;
	}
	
}