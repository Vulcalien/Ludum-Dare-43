package link.ld43;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	private final boolean[] keys = new boolean[65_536];
	
	public final Key
	up = new Key(KeyEvent.VK_W),
	left = new Key(KeyEvent.VK_A),
	down = new Key(KeyEvent.VK_S),
	right = new Key(KeyEvent.VK_D),
	
	interact = new Key(KeyEvent.VK_E),
	
	enter = new Key(KeyEvent.VK_ENTER);
	
	public class Key {
		public int code;
		
		private Key(int code) {
			this.code = code;
		}
		
		public boolean pressed() {
			return keys[code];
		}
		
		public void toggle() {
			keys[code] = false;
		}
	}
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
}