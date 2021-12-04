package pong;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;

import utilities.GDV5;
import java.awt.Rectangle;
public class Pong extends GDV5{
	
	int maxX = getMaxWindowX();
	int maxY = getMaxWindowY();

	Rectangle netL = new Rectangle(maxX/2  - 5, 0, 10, maxY);
	Rectangle netR = new Rectangle(maxX/2 + 5, 0, 10, maxY);
	Ball b1 = new Ball(20, 20, maxX, maxY);
	Paddle p1 = new Paddle(40, 40, 30, 150,maxX,maxY, false);
	Paddle p2 = new Paddle(1210, 400, 30, 150, maxX, maxY, false);
	Scoreboard sb = new Scoreboard(b1, maxX, maxY);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static void main(String[] args) {
		Pong p = new Pong();
		p.start();
	}


	public void update() {
		
		b1.move(b1, p1, p2, sb);
		p1.leftMove(KeysPressed);
		p2.rightMove(KeysPressed, b1);
		sb.checkVictory();
	}
	
	public void draw(Graphics2D win) {

		sb.choiceSelection(win, KeysPressed);
		if(sb.singlePlayer == true) {
			
			win.setColor(VERY_LIGHT_BLUE);
			win.fill(p1);
			win.fill(netL);
			win.setColor(Color.RED);
			win.fill(p2);
			win.fill(netR);
			win.setColor(b1.color);
			win.fill(b1);
			sb.drawSinglePlayer(win);
			p2.setAI(true);
		}
		else if(sb.multiplayer == true) {
			
			win.setColor(VERY_LIGHT_BLUE);
			win.fill(p1);
			win.fill(netL);
			win.setColor(Color.RED);
			win.fill(p2);
			win.fill(netR);
			p2.setAI(false);
			win.setColor(b1.color);
			win.fill(b1);
			sb.drawResetScore(win);
		}
		
		else if (sb.p1Victory == true || sb.p2Victory == true){
			sb.drawVictory(win);
		}
		
		else if (sb.options == true) {
			sb.drawHelp(win);
		}
		else {
			win.setColor(Color.BLACK);
			win.fill(b1);
			win.fill(p1);
			win.fill(p2);
			win.fill(netL);
			win.fill(netR);
			win.setColor(Color.WHITE);
			sb.drawStartScreen(win);
		}
	}
}
