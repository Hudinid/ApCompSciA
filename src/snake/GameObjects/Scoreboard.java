package snake.GameObjects;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import snake.gameLookGood.*;
import utilities.GDV5;
import java.awt.image.*;


public class Scoreboard {
	public static int score;
	public Scoreboard() {
		Scoreboard.score = 0;
	}
	
	public void draw(Graphics2D win) {
		win.setFont(new Font("Courier New", Font.BOLD, 32));
		win.drawString("Score: " + score, 30, 760);
		if(actualSnake.powerUp == 0) {
			win.drawString("Powerup: None", 500, 760);
		}
		else {
			win.drawString("PowerUp: Remove Tail", 400, 760);
		}
	}
}
