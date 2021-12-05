package breakout;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.awt.Graphics2D;

public class Brick extends Rectangle {

	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public static final Color YELLOW = new Color(255, 204, 0);
	public static final Color PURPLE = new Color(102, 0, 153);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color DARK_GREEN = new Color(0, 153, 0);
	public static final Color BLUE = new Color(0, 255, 0);
	public static final Color GREEN = new Color(0, 204, 0);
	
	private int x, y, width, height, hits; 
	Color col;
	private boolean alive;
	public static Brick[] bricks;
//	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);	
	public Brick(int startX, int startY, int width, int height, int hits, boolean alive) {
		super(startX, startY, width - 3, height);
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.hits = hits;
		this.alive = true;
		col = VERY_LIGHT_BLUE;
		this.changeBrick();
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public static void makeBricks(int numOfBricks, int bricksPerRow) {
		bricks = new Brick[numOfBricks];
		int x = 1, y = 1;
		int currLifeCount = numOfBricks / bricksPerRow;
		int count = 1;
		for(int i = 0; i < bricks.length; i ++) {
			System.out.println(currLifeCount);
			bricks[i] = new Brick(x, y, GDV5.getMaxWindowX()/bricksPerRow, 20, currLifeCount, true);
			x += GDV5.getMaxWindowX()/bricksPerRow;
			if(count % bricksPerRow == 0) {
				x = 1;
				y += bricks[i].getHeight() + 2;
				currLifeCount --;
			}
			count ++;
		}
	}
	
	public void changeBrick() {
		this.hits -= 1;
		switch(this.hits) {
			case 0:
				this.alive = false;
				break;
			case 1:
				col = VERY_LIGHT_BLUE;
				break;
			case 2:
				col = VERY_LIGHT_GREEN;
				break;
			case 3: 
				col = VERY_LIGHT_RED;
				break;
			case 4:
				col = YELLOW;
				break;
			case 5:
				col = PURPLE;
				break;
		}
	}
	
	public void collided() {
		changeBrick();
	}
	public void draw(Graphics2D pb) {
		if(this.alive == true) {
			pb.setColor(col);
			pb.fill(this);
		}
	}
	
	public static void collideWithBall(Ball ball) {
		
		for(Brick b : bricks) {
			if(b.intersects(ball) && b.alive == true) {
				int dir = GDV5.collisionDirection(b, ball, ball.getdX(), ball.getdY());
				b.collided();
				if(dir == 1 || dir == 3) {
					ball.setdY(ball.getdY()*-1);
				}
				else if(dir == 0 || dir == 2) {
					ball.setdX(ball.getdX()*-1);
				}
			}
		}
	}
	
	public static void drawBricks(Graphics2D win) {
		for(int i = 0; i < Brick.bricks.length; i ++) {
			Brick.bricks[i].draw(win);
		}
	}
}
