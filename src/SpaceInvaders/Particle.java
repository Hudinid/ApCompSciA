package SpaceInvaders;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.awt.Graphics2D;


public class Particle extends Rectangle {
	Color col;

	private double speed; 
	private int counter;
//	Graphics2D win;
	public Particle(int startX, int startY, int x, int y, Color col) {
		super(startX, startY, x, y);
		this.col = col;
		this.speed = 1;
		this.counter = 0;
//		
	}
	
	public void move() {
		
		if(counter % 150 == 0) {
			int direction = (int) (Math.random() * 360);
			
			double angle = direction;
//			System.out.println(Math.cos(angle));
//			System.out.println(Math.sin(angle));
			this.x += (this.speed * Math.cos(angle));
			this.y += (this.speed * Math.sin(angle));
		}
		this.counter ++;
	}
	
	public void draw(Graphics2D win) {
		for(int i = 0; i < AlienSpawner.allAliens.size() ; i ++) {
			if(!AlienSpawner.allAliens.get(i).isAlive) {
				win.setColor(Color.GREEN);
				win.fill(Alien.particles[i]);
			}
		}
	}
}
