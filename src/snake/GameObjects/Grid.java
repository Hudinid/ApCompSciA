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
	public static final Color LIGHT_RED = new Color(255, 80, 80);
	public static final Color DARK_BLUE = new Color(41, 127, 193);
	public static final Color GRAY = new Color(220, 220, 220);
	public static final Color WALL = new Color(45, 53, 69);
	static ImageLoader imageLoader = new ImageLoader();
	static BufferedImage apple = imageLoader.loader("src/images/apple.png");
	static BufferedImage snakeUp = imageLoader.loader("src/images/SnakeUp.png");
	static BufferedImage snakeRight = imageLoader.loader("src/images/snakeRight.png");
	static BufferedImage snakeLeft = imageLoader.loader("src/images/snakeLeft.png");
	static BufferedImage snakeDown = imageLoader.loader("src/images/snakeDown.png");
	static BufferedImage enemySnakeRight = imageLoader.loader("src/images/EnemySnakeRight.png");
	static BufferedImage enemySnakeLeft = imageLoader.loader("src/images/EnemySnakeLeft.png");
	static BufferedImage enemySnakeUp = imageLoader.loader("src/images/enemySnakeUp.png");
	static BufferedImage enemySnakeDown = imageLoader.loader("src/images/enemySnakeDown.png");
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
				int random = (int) (Math.random() * 20);
				
				Tile tile;
				if(random == 5) {tile = new Tile(i*xSep, j * ySep, xSep, ySep, "Wall"); }
				else { tile = new Tile(i*xSep, j * ySep, xSep, ySep, "Blank");}
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
				if(map[i][j].type.equals("otherSnake")) {
					map[i][j].fillTile(Color.GREEN, win);
					if(otherSnake.direction == 'u') win.drawImage(enemySnakeUp, null, map[i][j].x, map[i][j].y);
					else if(otherSnake.direction == 'r') win.drawImage(enemySnakeRight, null, map[i][j].x, map[i][j].y);
					else if(otherSnake.direction == 'd') win.drawImage(enemySnakeDown, null, map[i][j].x, map[i][j].y);
					else if(otherSnake.direction == 'l') win.drawImage(enemySnakeLeft, null, map[i][j].x, map[i][j].y);
				}
				if(map[i][j].type.equals("Snake")) {
					
					map[i][j].fillTile(Color.GREEN, win);
					if(actualSnake.direction == 'u') win.drawImage(snakeUp, null, map[i][j].x, map[i][j].y);
					else if(actualSnake.direction == 'r') win.drawImage(snakeRight, null, map[i][j].x, map[i][j].y);
					else if(actualSnake.direction == 'd') win.drawImage(snakeDown, null, map[i][j].x, map[i][j].y);
					else if(actualSnake.direction == 'l') win.drawImage(snakeLeft, null, map[i][j].x, map[i][j].y);
				}
				if(map[i][j].type.equals("RemoveTail")) {
					map[i][j].fillTile(GRAY, win);
				}
				if(map[i][j].type.equals("Tail")) {
					map[i][j].fillTile(LIGHT_RED, win);
				}
				if(map[i][j].type.equals("EnemyTail")) {
					map[i][j].fillTile(DARK_BLUE, win);
				}
				if(map[i][j].type.equals("EnemySnake")) {
//					map[i][j].fillTile(Color.BLUE, win);
					map[i][j].fillTile(Color.GREEN, win);
					if(EnemySnake.direction == 'u') win.drawImage(enemySnakeDown, null, map[i][j].x, map[i][j].y);
					else if(EnemySnake.direction == 'r') win.drawImage(enemySnakeRight, null, map[i][j].x, map[i][j].y);
					else if(EnemySnake.direction == 'd') win.drawImage(enemySnakeUp, null, map[i][j].x, map[i][j].y);
					else if(EnemySnake.direction == 'l') win.drawImage(enemySnakeLeft, null, map[i][j].x, map[i][j].y);
					
				}
				if(map[i][j].type.equals("Blank")) {
					map[i][j].fillTile(Color.GREEN, win);
				}
				if(map[i][j].type.equals("Food")) {
					map[i][j].fillTile(Color.GREEN, win);
					win.drawImage(apple, null, map[i][j].x, map[i][j].y);
				}
				
				if(map[i][j].type.equals("Star")) {
					map[i][j].fillTile(Color.YELLOW, win);
				}
				if(map[i][j].type.equals("RemoveTail")) {
					map[i][j].fillTile(GRAY, win);
				}
				if(map[i][j].type.equals("Wall")) {
					map[i][j].fillTile(WALL, win);
				}
			}
		}
	}
}
