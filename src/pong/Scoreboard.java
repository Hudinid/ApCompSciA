package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet.FontAttribute;

import utilities.GDV5;

public class Scoreboard {
	int p1Score, p2Score, maxX, maxY, prevRallyScore;
	Ball b1;
	boolean singlePlayer;
	boolean multiplayer;
	boolean RPG;
	boolean selectedGame;
	boolean lockedChoiceSelection;
	boolean options;
	boolean p1Victory, p2Victory; 
	
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public Scoreboard(Ball b1, int maxX, int maxY) {
		this.b1 = b1;
		this.p1Score = 0;
		this.p2Score = 0;
		this.maxX = maxX;
		this.maxY = maxY;
		this.singlePlayer = false;
		this.multiplayer = false;
		this.RPG = false;
		this.selectedGame = false;
		this.lockedChoiceSelection = false;
		this.options = false;
		this.prevRallyScore = 0;
	}
	public void checkVictory() {
		if(this.p1Score >= 10) {
			this.p1Victory = true;
			this.p2Victory = false;
			this.singlePlayer = false;
			this.multiplayer = false;
			this.RPG = false;
			this.selectedGame = false;
			this.lockedChoiceSelection = true;
			this.options = false;
		}
		else if (this.p2Score >= 10) {
			this.p1Victory = false;
			this.p2Victory = true;
			this.singlePlayer = false;
			this.multiplayer = false;
			this.RPG = false;
			this.selectedGame = false;
			this.lockedChoiceSelection = true;
			this.options = false;
		}
	}
	
	public void drawVictory(Graphics2D win) {
		win.setFont(new Font("Courier New", Font.BOLD, 36));
		win.setColor(Color.PINK);
		int xAdj = 200;
		if(this.p1Victory == true) {
			win.drawString("Player 1 has won!", maxX/2 - xAdj, maxY / 2);
			win.setColor(Color.WHITE);
			win.drawString("Press Escape to go Back", (maxX/2) - xAdj - 40, (maxY/2) + 30);
		}
		else {
			win.drawString("Player 2 has won!", maxX/2 - 220, maxY / 2);
			win.drawString("Press Escape to go Back", (maxX/2) - xAdj - 40, (maxY/2) + 30);
		}
	}
	
	public void drawResetScore(Graphics2D win) {
		
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		
		win.setColor(Color.WHITE);
		if(b1.countDownStarter != 0) {
			win.drawString(String.valueOf(b1.countDownStarter), maxX/2 - b1.w/2, 40);
		}
		win.setFont(new Font("Papyrus MS", Font.BOLD, 32));
		win.setColor(VERY_LIGHT_BLUE);
		win.drawString("Player 1 Score: " + String.valueOf(this.p1Score), maxX/4 - maxX/8, 40);
		win.setColor(Color.RED);
		win.drawString("Player 2 Score: " + String.valueOf(this.p2Score), (int) (maxX*0.75) - maxX/8, 40);
	}
	
	public void choiceSelection(Graphics2D win, boolean[] KeysPressed) {
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		if(KeysPressed[KeyEvent.VK_P]) {
			this.p1Score = 10;
		}
		if(KeysPressed[KeyEvent.VK_1] && !this.lockedChoiceSelection) {
			this.singlePlayer = true;
			this.multiplayer = false;
			this.RPG = false;
			this.options = false;
			this.selectedGame = true;
			this.p1Victory = false;
			this.p2Victory = false;
			this.lockedChoiceSelection = true;
		}
		if(KeysPressed[KeyEvent.VK_2] && !this.lockedChoiceSelection) {
			this.multiplayer = true;
			this.singlePlayer = false;
			this.RPG = false;
			this.options = false;
			this.selectedGame = true;
			this.p1Victory = false;
			this.p2Victory = false;
			this.lockedChoiceSelection = true;
		}
		if(KeysPressed[KeyEvent.VK_3] && !this.lockedChoiceSelection) {
			this.RPG = false;
			this.singlePlayer = false;
			this.multiplayer = false;
			this.selectedGame = true;
			this.options = true;
			this.p1Victory = false;
			this.p2Victory = false;
			this.lockedChoiceSelection = true;
		}
		
		if(KeysPressed[KeyEvent.VK_4] && !this.lockedChoiceSelection) {
			this.RPG = true;
			this.singlePlayer = false;
			this.multiplayer = false;
			this.options = false;
			this.selectedGame = true;
			this.p1Victory = false;
			this.p2Victory = false;
			this.lockedChoiceSelection = true;
		}
		
		if(KeysPressed[KeyEvent.VK_ESCAPE]) {
			b1.x = b1.maxX/2;
			b1.y = b1.maxY/2;
			this.RPG = false;
			this.p1Victory = false;
			this.p2Victory = false;
			this.singlePlayer = false;
			this.selectedGame = false;
			this.multiplayer = false;
			this.options = false;
			this.lockedChoiceSelection = false;
			this.p1Score = 0;
			this.p2Score = 0;
		}		
	}
	public void drawStartScreen(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		win.drawString("Welcome to Pong!", maxX/2 - maxX/6, 40);
		win.setFont(new Font("Courier New", Font.BOLD, 36));
		win.drawString("By Brian Hu", maxX / 2 - maxX/10, 100);
		win.setColor(VERY_LIGHT_RED);
		win.setFont(new Font("Courier New", Font.BOLD, 36));
		win.drawString("1. Singleplayer (Normal) ", maxX/2 - maxX/4, 180);
		win.drawString("2. Multiplayer (Two People Vs.) ", maxX/2 - maxX/4, 260);
		win.drawString("3. Help (Controls / Instructions) ", maxX/2 - maxX/4, 340);

	}
	
	public void drawSinglePlayer(Graphics2D win) {
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		if(b1.countDownStarter != 0) {
			win.drawString(String.valueOf(b1.countDownStarter), maxX/2, 30);
		}
		win.setFont(new Font("Papyrus MS", Font.BOLD, 32));
		win.drawString("Rally Score: " + String.valueOf(this.p1Score), maxX/4 - maxX/8, 40);
		win.setColor(VERY_LIGHT_BLUE);
		win.drawString("Highest Rally Score: " + String.valueOf(this.prevRallyScore), maxX/4 - maxX/6, 80);
	}
	
	public void drawHelp(Graphics2D win) {
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		win.setColor(VERY_LIGHT_GREEN);
		win.drawString("Singeplayer - ", 0, 40);
		win.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		win.setColor(VERY_LIGHT_RED);
		win.drawString("In the Singleplayer game, you are rallying against an AI, trying to best your own high score. ", 0, 100);
		win.drawString("You gain a point whenever the ball hits your paddle. ", 0, 160);
		win.drawString("Try and last for as long as possible! ", 0, 220);
		win.setColor(VERY_LIGHT_GREEN);
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		win.drawString("Multiplayer - ", 0, 270);
		win.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		win.setColor(VERY_LIGHT_RED);
		win.drawString("In the Multiplayer game, the first to 10 points wins.", 0, 330);
		win.drawString("Whenever someone scores a point, the ball will go back to the center", 0, 390);
		win.drawString("A timer will count before the ball starts to move again", 0, 450);
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		win.setColor(VERY_LIGHT_GREEN);
		win.drawString("Controls", 0, 510);
		win.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		win.setColor(VERY_LIGHT_RED);
		win.drawString("Player 1: W and S | Player 2: Up Arrow and Down Arrow", 0, 570);
		win.setColor(VERY_LIGHT_GREEN);
		win.setFont(new Font("Courier New", Font.BOLD, 42));
		win.drawString("Press Escape to go back", 0, 620);
		
	}
}

