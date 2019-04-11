package link.ld43.level;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import link.ld43.Game;
import link.ld43.entity.Entity;
import link.ld43.entity.Player;
import link.ld43.entity.Police;
import link.ld43.entity.Seeker;
import link.ld43.entity.Spaghetti;
import link.ld43.level.tile.Tile;
import link.ld43.menu.WinMenu;

public class Level {
	
	public final Game game;
	public final int width, height;
	public final byte[] tiles, data;
	public final boolean[] visible;
	public final List<Entity> entities = new ArrayList<Entity>();
	public final List<Entity>[] entitiesInTile;
	public final List<Seeker> seekers = new ArrayList<Seeker>();
	
	public int bgColor = 0x000000;
	
	public Player player;
	public boolean playerSeen = false;
	
	public boolean levelWon = false;
	public int winTime = 180;
	
	@SuppressWarnings("unchecked")
	public Level(Game game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		this.tiles = new byte[width * height];
		this.visible = new boolean[width * height];
		this.data = new byte[width * height];
		entitiesInTile = new ArrayList[width * height];
		for(int i = 0; i < entitiesInTile.length; i++) {
			entitiesInTile[i] = new ArrayList<Entity>();
		}
	}
	
	public Level(Game game, InputStream level, InputStream entities) throws IOException {
		this(game, level.read(), level.read());
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = (byte) level.read();
			data[i] = (byte) level.read();
		}
		
		int buffer;
		int entityId = -1;
		byte[] attributes = null;
		List<Integer> attributesList = new ArrayList<Integer>();
		boolean readingAttributes = false;
		while((buffer = entities.read()) != -1) {
			if(!readingAttributes) {
				entityId = buffer;
				readingAttributes = true;
			} else if(buffer == 255) {
				attributes = new byte[attributesList.size()];
				for(int i = 0; i < attributesList.size(); i++) {
					attributes[i] = (byte) (int) attributesList.get(i);
				}
				if(entityId == 0) add(new Player(this, game.input, attributes));
				else if(entityId == 1) add(new Police(this, attributes));
				else if(entityId == 2) add(new Spaghetti(this, attributes));
				readingAttributes = false;
				attributesList.clear();
			} else {
				attributesList.add(buffer);
			}
		}
	}
	
	public void tick() {
		if(levelWon) {
			if(winTime > 0) {
				winTime--;
			} else {
				if(game.levelIndex > game.passedLvls) game.passedLvls++;
				game.menu = new WinMenu(game);
			}
			return;
		}
		
		for(int i = 0; i < visible.length; i++) {
			visible[i] = false;
		}
		
		for(int i = 0; i < seekers.size(); i++) {
			seekers.get(i).makeVisible();
		}
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			int x0 = e.x;
			int y0 = e.y;
			
			e.tick();
			
			int x1 = e.x;
			int y1 = e.y;
			
			removeEntity(e, x0 / 20, y0 / 20);
			if(e.removed) {
				entities.remove(e);
			} else {
				insertEntity(e, x1 / 20, y1 / 20);
			}
		}
	}
	
	public void setTile(Tile tile, int xt, int yt) {
		tiles[xt + yt * width] = tile.id;
	}
	
	public Tile getTile(int xt, int yt) {
		return Tile.tiles[tiles[xt + yt * width]];
	}
	
	public void setData(byte data, int xt, int yt) {
		this.data[xt + yt * width] = data;
	}
	
	public byte getData(int xt, int yt) {
		return data[xt + yt * width];
	}
	
	public void setVisible(boolean val, int xt, int yt) {
		visible[xt + yt * width] = val;
	}
	
	public boolean getVisible(int xt, int yt) {
		return visible[xt + yt * width];
	}
	
	public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();
		int xt0 = x0 / 20 - 1;
		int yt0 = y0 / 20 - 1;
		int xt1 = x1 / 20 + 1;
		int yt1 = y1 / 20 + 1;
		for(int xt = xt0; xt <= xt1; xt++) {
			if(xt < 0 || xt >= width) continue;
			for(int yt = yt0; yt <= yt1; yt++) {
				if(yt < 0 || yt >= height) continue;
				List<Entity> inTile = entitiesInTile[xt + yt * width];
				for(int i = 0; i < inTile.size(); i++) {
					Entity e = inTile.get(i);
					if(e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		return result;
	}
	
	public List<Entity> getEntitiesInTile(int xt0, int yt0, int xt1, int yt1) {
		List<Entity> result = new ArrayList<Entity>();
		for(int yt = yt0; yt < yt1; yt++) {
			if(yt < 0 || yt >= height) continue;
			for(int xt = xt0; xt < xt1; xt++) {
				if(xt < 0 || xt >= width) continue;
				result.addAll(entitiesInTile[xt + yt * width]);
			}
		}
		return result;
	}
	
	public void add(Entity e) {
		int xt = e.x / 20;
		int yt = e.y / 20;
		entities.add(e);
		insertEntity(e, xt, yt);
		if(e instanceof Player) player = (Player) e;
		if(e instanceof Seeker) seekers.add((Seeker) e);
	}
	
	private void insertEntity(Entity e, int xt, int yt) {
		entitiesInTile[xt + yt * width].add(e);
	}
	
	private void removeEntity(Entity e, int xt, int yt) {
		entitiesInTile[xt + yt * width].remove(e);
	}
	
}