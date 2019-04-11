package link.ld43;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import link.ld43.entity.Player;
import link.ld43.level.Level;
import link.ld43.level.Loader;
import link.ld43.menu.Menu;
import link.ld43.menu.StartMenu;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int width = 520, height = 320;
	public static final int scale = 2;
	
	private final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private final int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	
	public final Screen screen = new Screen(this, width, height);
	public final InputHandler input = new InputHandler(this);
	
	public Menu menu = new StartMenu(this);
	public Level level;
	public Player player;
	
	public final int levels = 5;
	public int levelIndex = 0;
	public int passedLvls = 0;
	
	private Thread thread;
	private boolean running = false;
	public int ticks = 0;
	
	public void run() {
		int frames = 0;
		
		long nanosPerTick = 1_000_000_000 / 60;
		long lastTime = System.nanoTime();
		long unprocessedNanos = 0;
		while(running) {
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			
			if(passedTime < 0) passedTime = 0;
			if(passedTime >= 1_000_000_000) passedTime = 1_000_000_000;
			
			unprocessedNanos += passedTime;
			
			boolean ticked = false;
			while(unprocessedNanos >= nanosPerTick) {
				unprocessedNanos -= nanosPerTick;
				
				ticked = true;
				tick();
				ticks++;
			}
			if(ticked) {
				render();
				frames++;
				if(ticks % 60 == 0) {
					System.out.println(frames + " fps");
					frames = 0;
				}
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		requestFocus();
		requestFocus();
		requestFocus();
		
		loadLevel(0);
	}
	
	private void tick() {
		if(menu != null) {
			menu.tick();
		} else {
			level.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.render();
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, width * scale, height * scale, null);
		g.dispose();
		bs.show();
	}
	
	public void loadLevel(int id) {
		levelIndex = id;
		level = Loader.getLevel(this, id);
		player = level.player;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(width * scale, height * scale));
		game.setMinimumSize(new Dimension(width * scale, height * scale));
		game.setMaximumSize(new Dimension(width * scale, height * scale));
		
		JFrame frame = new JFrame("In the name of Spaghetti");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		try {
			frame.setIconImage(ImageIO.read(Game.class.getResource("/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		game.init();
		game.start();
	}
	
}