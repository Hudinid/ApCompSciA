package snake.GameObjects;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import utilities.GDV5;

public class Tile extends Rectangle {
	String type; 
	int x, y;
	public Tile(int startX, int startY, int sizeX, int sizeY, String type) {
		super(startX, startY, sizeX, sizeY);
		this.type = type;
		this.x = startX;
		this.y = startY;
	}
	
	public void fillTile(Color col, Graphics2D win) {
		win.setColor(col);
		win.fill(this);
	}
	
	public void outLine(Color col, Graphics2D win) {
		win.setColor(col);
		win.draw(this);
	}
	
	
}