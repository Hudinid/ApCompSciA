package SpaceInvaders;
import java.awt.Color;
import java.util.ArrayList;

import snake.gameLookGood.Sound;

import java.awt.Font;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class SpaceInvaders extends SpaceInvadersGameState {
	private InvaderState invaderState;
	
	static int maxX = GDV5.getMaxWindowX();
	static int maxY = GDV5.getMaxWindowY();

	AlienSpawner spawner = new AlienSpawner(100, 2, 100, 50, 50);
	Ship ship = new Ship(700, 730, 100, 50);
	int totalAmount = spawner.aliensPerRow * spawner.amountOfRows;
	Sound bgMusic = new Sound("src/SpaceInvaders/bgMusic.wav");
	int count = 0;
	public static int alienHp = 1;
	public SpaceInvaders(InvaderState invaderState) {
		this.invaderState = invaderState;
//		System.out.println(totalAmount);
//		bgMusic.se.play();
	}
	
	public void update() {
		count ++;
		if(count % 90 == 0 && totalAmount > 0) {
			totalAmount --;
			spawner.createAlien(alienHp);
		}
		spawner.moveAllAliens();
		ship.moveShip(GDV5.KeysPressed);
		if(count%60 == 0) if(ship.shootAndCollision(GDV5.KeysPressed) == false) {
			invaderState.setState(new EndScreen(invaderState));
			bgMusic.se.stop();
		}
		ship.powerUp();
		Bullet.moveBullet();
		spawner.checkHitsForAliens();
	}
	
	public void draw(Graphics2D win) {
		spawner.drawAliens(win);
		ship.drawShip(win);
		Bullet.drawBullet(win);
	}
}
