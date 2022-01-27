package breakout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet.FontAttribute;

import utilities.GDV5;

public class Scoreboard {
	public static int score = 0;
	public final static Color LIGHT_GREY = new Color(204,204,204);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	int currChoice = 0;
	int maxX;
	int maxY;
	boolean lockedChoiceSelection, helpScreen, Game, lost;
	public Scoreboard() {
		this.maxX = GDV5.getMaxWindowX();
		this.maxY = GDV5.getMaxWindowY();
	}
	
	public void drawScore(Graphics2D win) {
		win.setFont(new Font("Courier New", Font.BOLD, 24));
		win.setColor(VERY_LIGHT_GREEN);
		win.drawString("Points: " + Scoreboard.score, 0, 350);
		win.drawString("Lives: " + Brick.lives, 0, 425);
	}
	
	public static void addScore() {
		Scoreboard.score += 100;
	}
	
	public void drawHelpScreen(Graphics2D win) {
		win.setFont(new Font("Courier New", Font.BOLD, 30));
		win.setColor(VERY_LIGHT_GREEN);
		win.drawString("Game - ", 0, 40);
		win.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		win.setColor(VERY_LIGHT_RED);
		win.drawString("In the Breakout game, you are trying to destroy blocks", 0, 100);
		win.drawString("You gain a point whenever a brick is destroyed ", 0, 160);
		win.drawString("Try and get through 10 levels! ", 0, 220);
		
		win.setColor(VERY_LIGHT_GREEN);
		win.drawString("Controls", 0, 300);
		win.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		win.setColor(VERY_LIGHT_RED);
		win.drawString("To Move use A and D or Arrow keys", 0, 400);
		win.setColor(VERY_LIGHT_GREEN);
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		win.drawString("Press Escape to go back", 0, 500);
	}
	
	public void drawLoseScreen(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 32));
		win.drawString("You Lost on Level " + Brick.currLevel + "!", maxX/2 - maxX/3, 40);
		win.drawString("Hit Escape to go back!", maxX/2 - maxX/3, 100);
	}
	
	public void choiceSelector(Graphics2D win, boolean[] KeysPressed) {
		if(KeysPressed[KeyEvent.VK_1] && !this.lockedChoiceSelection) {
			System.out.println("Selected Game");
			this.Game = true;
			this.helpScreen = false;
			this.lockedChoiceSelection = true;
		}
		if(KeysPressed[KeyEvent.VK_2] && !this.lockedChoiceSelection) {
			this.helpScreen = true;
			this.Game = false;
			this.lockedChoiceSelection = true;
		}
		if(Brick.lives == 0) {
			this.helpScreen = false;
			this.Game = false;
			this.lockedChoiceSelection = false;
			this.lost = true;
		}
		if(KeysPressed[KeyEvent.VK_ESCAPE]) {
			this.helpScreen = false;
			this.Game = false;
			this.lockedChoiceSelection = false;
			this.lost = false;
		}
		
	}
	
	public void startScreen(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 32));
		win.drawString("Welcome to Breakout!", maxX/2 - maxX/3, 40);
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		win.drawString("By Brian Hu", maxX / 2 - maxX/6, 100);
		win.setColor(VERY_LIGHT_RED);
		win.setFont(new Font("Courier New", Font.BOLD, 24));
		win.drawString("1. Singleplayer (Normal) ", maxX/2 - maxX/3, 180);
		win.drawString("2. Help (Controls / Instructions) ", maxX/2 - maxX/3, 260);
	}
}
