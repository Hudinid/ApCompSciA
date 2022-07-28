package SpaceInvaders;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Rectangle {

	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public static final Color YELLOW = new Color(255, 204, 0);
	public static final Color PURPLE = new Color(102, 0, 153);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color DARK_GREEN = new Color(0, 153, 0);
	public static final Color BLUE = new Color(0, 255, 0);
	public static final Color GREEN = new Color(0, 204, 0);
	public static final Color ORANGE = new Color(255, 102, 0);
	public static final Color DARK_YELLOW = new Color(255,204,0);
	public static final Color LIGHT_GREEN = new Color(0, 255, 51);
	
	int speed, power;
	boolean isTangible;
	public Bullet(int speed, int power, int sizeX, int sizeY, int startingX, int startingY) {
		super(startingX, startingY, sizeX, sizeY);
		this.speed = speed;
		this.power = power;
		this.isTangible = true;
	}
	
	public static void moveBullet() {
		for(Bullet bullet : Ship.firedBullet) {
			bullet.y -= bullet.speed;
			if(bullet.y <= 0 && bullet.isTangible) {
				bullet.selfDestruct();
				Ship.totalShots++;
			}
		}

	}
	
	public void selfDestruct() {
		this.isTangible = false;
	}
	
	public static void drawBullet(Graphics2D win) {

		win.setColor(VERY_LIGHT_RED);
		for(Bullet bullet : Ship.firedBullet) {
			if(bullet.isTangible) win.fill(bullet);
		}
	}
}
