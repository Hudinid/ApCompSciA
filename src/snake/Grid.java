package snake;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import utilities.GDV5;

public class Grid {
	int maxX, maxY, row, col;
	int[][] map;
	public Grid(int maxX, int maxY, int row, int col) {
		this.maxX = maxX;
		this.maxY = maxY-100;
		this.row = row;
		this.col = col;
		map = new int[row][col];
	}
	
	public void draw(Graphics2D win) {
		win.setColor(Color.WHITE);
		int startX = 0;
		int w = 0;
		int h = GDV5.getMaxWindowY()-100;
		for(int i = 0; i < maxX/col; i++) { // Do lines vertically across the entire grid 
			Rectangle gridLine = new Rectangle(startX, 0, w, h); //since its vertical lines, startY is 0
			win.draw(gridLine);
			startX += maxX/row;
			
		}
		
		int startY = 0;
		w = GDV5.getMaxWindowX();
		h = 0;
		for(int i = 0; i < maxY/row; i ++) {
			Rectangle gridLine = new Rectangle(0, startY, w, h); //since its a horz line, startX is 0s
			win.draw(gridLine);
			startY += maxY/col;
//			System.out.println(maxY/row);
			System.out.println(startY);
		}
//		win.draw(new Rectangle(0, maxY-1, GDV5.getMaxWindowX(), h));
		win.draw(new Rectangle(maxX-2, 0, w, GDV5.getMaxWindowY()-100));
	}
}
