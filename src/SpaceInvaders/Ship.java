package SpaceInvaders;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import utilities.GDV5;
import java.util.*;

import snake.gameLookGood.ImageLoader;
import snake.gameLookGood.Sound;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ship extends Rectangle {

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
	
	int posX, posY, width, height;
	public static int speed;
	public static int totalShots = 1;
	public static int bulletSpeed, bulletPower;
	static int aliensKilled = 0;
	public static ArrayList<Bullet> firedBullet = new ArrayList<Bullet>();
	public static boolean gotPowered = false;
	static ImageLoader imageLoader = new ImageLoader();
	
	static BufferedImage ship = imageLoader.loader("src/SpaceInvaders/spaceship.png");

	
	
	
	public Ship(int startX, int startY, int width, int height) {
		super(startX, startY, width, height);
		this.posX = startX;
		this.posY = startY;
		this.width = width;
		this.height = height;
		Ship.bulletSpeed = 5;
		Ship.bulletPower = 1;
		Ship.speed = 3;
	}
	
	public void moveShip(boolean[] KeysPressed) {
		if(this.x + this.width > 800) {
			this.x = 800;
		}
		if(KeysPressed[KeyEvent.VK_LEFT] && this.x > 0) {
			this.x -= Ship.speed;
		}
		if(KeysPressed[KeyEvent.VK_RIGHT] && this.x+this.width < 800) {
			this.x += Ship.speed;
		}
		if(KeysPressed[KeyEvent.VK_A]  && this.x > 0) {
			this.x -= Ship.speed;
		}
		if(KeysPressed[KeyEvent.VK_D]  && this.x+this.width < 800) {
			this.x += Ship.speed;
		}
	}
	
	public boolean shootAndCollision(boolean[] KeysPressed) {
		boolean fired = false;
		if(totalShots > 0 && KeysPressed[KeyEvent.VK_SPACE]) {
			if(!fired) {
				fired = true;
				Bullet bullet = new Bullet(bulletSpeed, bulletPower, 5, 20, this.x+this.width/2, this.y);
				firedBullet.add(bullet);
				Sound fireSound = new Sound("src/SpaceInvaders/shipFire.wav");
				fireSound.se.play();
				Ship.totalShots --;
			}
		}
		
		for(Alien alien : AlienSpawner.allAliens) {
			if(alien.intersects(this) && alien.isAlive) {
				return false;
			}
		}
		return true;
	}
	
	public void drawShip(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.drawImage(ship, null, this.x, this.y);
//		win.fill(this);
	}
	
	public void powerUp() {
		if(aliensKilled%10 == 0 && aliensKilled != 0) {
			if(!gotPowered) {
				gotPowered = true;
				Ship.speed++;
				Ship.totalShots++;
				SpaceInvaders.alienHp++;
			}
		}
	}
}
