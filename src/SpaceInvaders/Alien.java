package SpaceInvaders;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import breakout.Particle;
import snake.gameLookGood.ImageLoader;
import snake.gameLookGood.Sound;

import java.awt.Font;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Alien extends Rectangle {
	int hp;
	char direction;
	int currTravelX, currTravelY;
	int maxXTravel, maxYTravel;
	char prevDir;
	Color color;
	boolean isAlive;
	static ImageLoader imageLoader = new ImageLoader();
	public static Particle[] particles = new Particle[20];
	static BufferedImage alien1 = imageLoader.loader("src/SpaceInvaders/enemy1.png");
	static BufferedImage alien2 = imageLoader.loader("src/SpaceInvaders/enemy2.png");
	static BufferedImage alien3 = imageLoader.loader("src/SpaceInvaders/enemy3.png");
	static BufferedImage alien4 = imageLoader.loader("src/SpaceInvaders/enemy4.png");
	boolean drawnParticles;
	public Alien(int startX, int startY, int width, int height, int hp, char dir, int maxXTravel, int maxYTravel, Color color) {
		super(startX, startY, width, height);
		this.hp = hp;
		this.maxXTravel = maxXTravel;
		this.maxYTravel = maxYTravel;
		this.direction = dir;
		this.currTravelX = 0;
		this.currTravelY = 0;
		prevDir = this.direction;
		this.color = color;
		isAlive = true;
		drawnParticles = false;
	}
	
	public void checkCollision() {
		for(Bullet bullet : Ship.firedBullet) {
			if(bullet.intersects(this) && bullet.isTangible && isAlive) {
				bullet.selfDestruct();
				Ship.totalShots++;
				this.hp -= bullet.power;
				Sound deathSound = new Sound("src/SpaceInvaders/deathSound.wav");
				deathSound.se.play();
				bullet = null;
			}
		}
	}
	
	public void checkMovement() {
		if(this.currTravelX >= this.maxXTravel) {
			this.currTravelX = 0;
			this.prevDir = this.direction;
			this.direction = 'd';
		}
		else if(this.currTravelY >= this.maxYTravel) {
			this.currTravelY = 0;
			if(this.prevDir == 'r') { 
				this.direction = 'l';
				this.prevDir = 'l';
			}
			else {
				this.direction = 'r';
				this.prevDir = 'r';
			}
		}
	}
	
	public void move(int speed) {
		if(this.direction == 'r') {
			this.x += speed;
			this.currTravelX += speed;
		}
		else if (this.direction == 'd') {
			this.y += speed;
			this.currTravelY += speed;
		}
		else {
			this.x -= speed;
			this.currTravelX += speed;
		}
	}
	
	public void draw(Graphics2D win) {
		if(this.isAlive) {
			win.setColor(color);
//			win.fill(this);
			if(this.hp == 1) win.drawImage(alien1, null, this.x, this.y);
			if(this.hp == 2) win.drawImage(alien2, null, this.x, this.y);
			if(this.hp == 3) win.drawImage(alien3, null, this.x, this.y);
			if(this.hp == 4) win.drawImage(alien4, null, this.x, this.y);
		}
	}
}