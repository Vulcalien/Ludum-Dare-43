package link.ld43.entity;

import java.util.List;

import link.ld43.Screen;
import link.ld43.level.Level;
import link.ld43.level.tile.Tile;

public class Entity {
	
	public int x, y;
	public int xr, yr;
	
	public final Level level;
	public boolean removed = false;
	
	public Entity(Level level) {
		this.level = level;
	}
	
	public void tick() {
	}
	
	public void render(Screen screen) {
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean intersects(int x0, int y0, int x1, int y1) {
		return !(x + xr < x0 || y + yr < y0 || x - xr > x1 || y - yr > y1);
	}
	
	public boolean blocks(Entity e) {
		return false;
	}
	
	public boolean move(int xm, int ym) {
		boolean stopped = false;
		if(xm != 0 || ym != 0) {
			boolean xMoved = move2(xm, 0);
			boolean yMoved = move2(0, ym);
			stopped = !(xMoved || yMoved);
		}
		if(!stopped) {
			int xt = x / 20;
			int yt = y / 20;
			level.getTile(xt, yt).steppedOn(level, xt, yt, this);
		}
		return !stopped;
	}
	
	public boolean move2(int xm, int ym) {
		if(xm != 0 && ym != 0) throw new RuntimeException("Move2 invalid parameters");
		
		if(x + xr + xm > level.width * 20) return false;
		if(y + yr + ym > level.height * 20) return false;
		if(x - xr + xm < 0) return false;
		if(y - yr + ym < 0) return false;
		
		int xto0 = (x - xr) / 20;
		int yto0 = (y - yr) / 20;
		int xto1 = (x + xr) / 20;
		int yto1 = (y + yr) / 20;
		
		int xt0 = (x - xr + xm) / 20;
		int yt0 = (y - yr + ym) / 20;
		int xt1 = (x + xr + xm) / 20;
		int yt1 = (y + yr + ym) / 20;
		
		for(int yt = yt0; yt <= yt1; yt++) {
			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt >= xto0 && xt <= xto1 && yt >= yto0 && yt <= yto1) continue;
				if(xt < 0 || xt >= level.width || yt < 0 || yt >= level.height) continue;
				Tile tile = level.getTile(xt, yt);
				tile.bumpedInto(level, xt, yt, this);
				if(!tile.mayPass(level, xt, yt)) return false;
			}
		}
		
		boolean blocked = false;
		List<Entity> entities = level.getEntities(x - xr + xm, y - yr + ym, x + xr + xm, y + yr + ym);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e == this) continue;
			e.touchedBy(this);
			
			if(e.blocks(this)) blocked = true;
		}
		if(blocked) return false;
		
		x += xm;
		y += ym;
		return true;
	}
	
	public void touchedBy(Entity e) {
	}
	
}