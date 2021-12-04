package breakout;
import java.util.ArrayList;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;

import utilities.GDV5;
import java.awt.Rectangle;
public class Breakout extends GDV5 {
	static int maxX = GDV5.getMaxWindowX();
	static int maxY = GDV5.getMaxWindowY();
	
//	public static Brick[] bricks;
	Paddle paddle = new Paddle(1, 740 , 100, 15);
	Ball ball = new Ball(3, 3, 300, 300, 10, 10);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);	
	
	public static void main(String[] args) {

		Brick.makeBricks(40, 5);
		Breakout b = new Breakout();
		b.start();
	}

	public void update() {
		ball.move(paddle);
		Brick.collideWithBall(ball);
		paddle.movePaddle(KeysPressed);
	}

	public void draw(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.fill(paddle);
		win.fill(ball);
		Brick.drawBricks(win);
	}	
}