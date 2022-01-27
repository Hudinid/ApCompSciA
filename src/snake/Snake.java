package snake;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;

import utilities.GDV5;
import java.awt.Rectangle;

public class Snake extends GDV5{
	static int maxX = GDV5.getMaxWindowX();
	static int maxY = GDV5.getMaxWindowY();
	Grid grid = new Grid(maxX, maxY, 20, 20);
	public static void main(String[] args) {
		
		Snake s = new Snake();
		s.start();
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D win) {
		grid.draw(win);
	}
}
