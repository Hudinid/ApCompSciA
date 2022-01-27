package breakout;

import java.util.*;
import java.awt.Rectangle;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import utilities.GDV5;
import java.awt.Graphics2D;

public class PowerUp extends Rectangle {
	
	private int type;
	
	public static ArrayList<PowerUp> powers = new ArrayList<PowerUp>();
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public PowerUp(int startX, int startY) {
		super(startX, startY, 20, 40);
		powers.add(this);
	}
	
	public void move(Graphics2D win) {
		type = (int) (Math.random() * 1) + 1;
		if(this.type == 1) {
			win.setColor(VERY_LIGHT_RED);
			win.fill(this);
		}
		else if (this.type == 2) {
			win.setColor(VERY_LIGHT_BLUE); 
			win.fill(this);
		}
		else if(this.type == 3) {
			win.setColor(VERY_LIGHT_GREEN);
			win.fill(this);
		}
		
		this.y += 2;
	}
	
	public static void collideWithPaddle() {
		for(Brick b : Brick.bricks) {
			for(int i = 0; i < powers.size(); i ++) {
				if(powers.get(i).intersects(b)) {
					if(powers.get(i).type == 1) {
						Brick.lives += 1;
					}
					else if (powers.get(i).type == 2) {
						Paddle.speed += 1;
					}
				}
			}
		}
	}
}
