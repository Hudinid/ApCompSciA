package snake.GameObjects;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import snake.gameLookGood.*;
import utilities.GDV5;
import java.awt.image.*;


public class Grid {
	int maxX, maxY, row, col;
	int xSep, ySep;
	static Tile[][] map;
	public static final Color PINK = new Color(255, 192, 203);
	static ImageLoader imageLoader = new ImageLoader();
	static BufferedImage apple = imageLoader.loader("src/images/apple.png");
	public Grid(int maxX, int maxY, int row, int col) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.row = row;
		this.col = col;
		map = new Tile[row][col];
		xSep = maxX/col;
		ySep = maxY/row;
		for(int i = 0; i < row; i ++) { 
			for(int j = 0; j < col; j ++) {
				Tile tile = new Tile(i*xSep, j * ySep, xSep, ySep, "Blank");
				map[i][j] = tile;
			}
		}
	}
	
	public void draw(Graphics2D win) {
		win.setColor(Color.WHITE);
		for(int i = 0; i < row; i ++) { 
			for(int j = 0; j < col; j ++) {
				map[i][j].outLine(Color.WHITE, win);
			}
		}
		
		win.draw(new Rectangle(maxX-2, 0, 0, ySep*col));
	}
	
	public static void updateGrid(Graphics2D win, int row, int col) {
		for(int i = 0; i < row; i ++) {
			for(int j = 0; j < col; j ++) {
				if(map[i][j].type.equals("Snake")) {
					
					map[i][j].fillTile(Color.RED, win);
				}
				if(map[i][j].type.equals("EnemySnake")) {
					
					map[i][j].fillTile(Color.BLUE, win);
				}
				if(map[i][j].type.equals("Blank")) {
					map[i][j].fillTile(Color.GREEN, win);
				}
				if(map[i][j].type.equals("Food")) {
					map[i][j].fillTile(PINK, win);
					win.drawImage(apple, null, map[i][j].x, map[i][j].y);
				}
				
				if(map[i][j].type.equals("Star")) {
					map[i][j].fillTile(Color.YELLOW, win);
				}
				if(map[i][j].type.equals("RemoveTail")) {
					map[i][j].fillTile(Color.GRAY, win);
				}
			}
		}
	}
}
