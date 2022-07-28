package SpaceInvaders;

import java.awt.Rectangle;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.awt.event.KeyEvent;

import utilities.GDV5;
import java.awt.Graphics2D;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import breakout.Brick;
import snake.gameLookGood.Sound;

public class AlienSpawner {
	int aliensPerRow, speedOfAliens, amountOfRows, spawnX, spawnY;
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
	
	public static ArrayList<Alien> allAliens = new ArrayList<>();
	public AlienSpawner(int aliensPerRow, int speedOfAliens, int amountOfRows, int spawnX, int spawnY) {
		this.aliensPerRow = aliensPerRow;
		this.speedOfAliens = speedOfAliens;
		this.amountOfRows = amountOfRows;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}
	
	public void checkHitsForAliens() {
		for (Alien alien : allAliens) {
			alien.checkCollision();
			if(alien.hp <= 0 && alien.isAlive) {
				alien.isAlive = false;	
				Ship.aliensKilled ++;
				Ship.gotPowered = false;
			}
			
		}
	}
	
	public void createAlien(int life) {
		int maxXForAlien = GDV5.getMaxWindowX() - 200;
		int maxYForAlien = GDV5.getMaxWindowY() / 15;
		Alien newAlien = new Alien(spawnX, spawnY, 64, 64, life, 'r', maxXForAlien, maxYForAlien, GREEN);
		allAliens.add(newAlien);
	}
	
	public void moveAllAliens() {
		for(int i = 0; i < allAliens.size(); i ++) {
			allAliens.get(i).checkMovement();
			allAliens.get(i).move(speedOfAliens);
		}
	}
	
	public void drawAliens(Graphics2D win) {
		for(int i = 0; i < allAliens.size(); i ++) {
			if(allAliens.get(i).isAlive) allAliens.get(i).draw(win);
		}
	}
	
}
