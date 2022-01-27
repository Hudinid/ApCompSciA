package breakout;
import java.util.ArrayList;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.awt.Rectangle;
public class Breakout extends GDV5 {
	static int maxX = GDV5.getMaxWindowX();
	static int maxY = GDV5.getMaxWindowY();
	
//	public static Brick[] bricks;
	Paddle paddle = new Paddle(1, 740 , 100, 15);
	Ball ball = new Ball(3, 3, 300, 350, 10, 10);
	Scoreboard sb = new Scoreboard();
	boolean temp = true;
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);	
	
	public static void main(String[] args) {
		
		
//		Brick.makeBricks(30, 6);
		Breakout b = new Breakout();
		b.start();
	}

	public void update() {
		ball.move(paddle, sb);
		Brick.collideWithBall(ball);
		paddle.movePaddle(KeysPressed);
		PowerUp.collideWithPaddle();
		if(KeysPressed[KeyEvent.VK_B]) {
			if(temp) {
				Brick.destroyBricks();
				temp = false;
			}
		}
		if(KeysPressed[KeyEvent.VK_R]) {
			temp = true;
		}
	}

	public void draw(Graphics2D win) {
		sb.choiceSelector(win, KeysPressed);
		if(sb.Game == true) {
			int totalBricks = Brick.currLevel*9;
			Brick.makeBricks(totalBricks, 9);
			Brick.drawBricks(win, ball);
			Brick.drawParticles(win);
			sb.drawScore(win);
			win.setColor(VERY_LIGHT_BLUE);
			win.fill(paddle);
			win.fill(ball);
		}
		else if(sb.helpScreen) {
			sb.drawHelpScreen(win);
		}
		else if(sb.lost) {
			sb.drawLoseScreen(win);
		}
		else {
			sb.startScreen(win);
		}
//		win.setColor(VERY_LIGHT_BLUE);
//		win.fill(paddle);
//		win.fill(ball);
//		Brick.drawBricks(win);
//		Brick.drawParticles(win);
	}	
}