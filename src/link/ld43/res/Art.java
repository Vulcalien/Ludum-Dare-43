package link.ld43.res;

import java.io.IOException;

import javax.imageio.ImageIO;

import link.bitmap.Bitmap;

public abstract class Art {
	
	private static final Bitmap atlas;
	
	static {
		try {
			atlas = new Bitmap(ImageIO.read(Art.class.getResource("/atlas.png")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Bitmap getSprite(int x, int y) {
		return getSprite(x, y, 1, 1);
	}
	
	public static Bitmap getSprite(int x, int y, int w, int h) {
		return atlas.getSubimage(x * 20, y * 20, w * 20, h * 20);
	}
	
}