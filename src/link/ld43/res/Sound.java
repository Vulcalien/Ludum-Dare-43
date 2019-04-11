package link.ld43.res;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final Sound
	caught = new Sound("/caught.wav"),
	key_grab = new Sound("/key_grab.wav"),
	teleport = new Sound("/teleport.wav");
	
	private final AudioClip clip;
	
	public Sound(String file) {
		clip = Applet.newAudioClip(Sound.class.getResource(file));
	}
	
	public void play() {
		new Thread() {
			public void run() {
				clip.play();
			}
		}.start();
	}
	
	public void loop() {
		new Thread() {
			public void run() {
				clip.loop();
			}
		}.start();
	}
	
	public void stop() {
		new Thread() {
			public void run() {
				clip.stop();
			}
		}.start();
	}
	
}