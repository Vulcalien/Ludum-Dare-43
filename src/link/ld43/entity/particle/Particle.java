package link.ld43.entity.particle;

import link.ld43.entity.Entity;
import link.ld43.level.Level;

public class Particle extends Entity {
	
	public int lifeTime;
	
	public Particle(Level level) {
		super(level);
	}
	
	public void tick() {
		lifeTime--;
		if(lifeTime == 0) remove();
	}
	
}