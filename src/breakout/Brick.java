package breakout;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.awt.Graphics2D;

public class Brick extends Rectangle {
	private int x, y, width, height, hits; 
	Color col;
	private boolean alive;
	public static Brick[] bricks;
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);	
	public Brick(int startX, int startY, int width, int height, int hits, boolean alive) {
		super(startX, startY, width - 3, height);
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.hits = hits;
		this.alive = true;
		col = VERY_LIGHT_BLUE;
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public static void makeBricks(int numOfBricks, int bricksPerRow) {
		bricks = new Brick[numOfBricks];
		int x = 1, y = 1;
		
		int count = 1;
		for(int i = 0; i < bricks.length; i ++) {
			bricks[i] = new Brick(x, y, GDV5.getMaxWindowX()/bricksPerRow, 20, 1, true);
			x += GDV5.getMaxWindowX()/bricksPerRow;
			if(count % bricksPerRow == 0) {
				x = 1;
				y += bricks[i].getHeight() + 2;
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
