package breakout;
import java.awt.Rectangle;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
	public static final Color ORANGE = new Color(255, 102, 0);
	public static final Color DARK_YELLOW = new Color(255,204,0);
	public static final Color LIGHT_GREEN = new Color(0, 255, 51);
	
	private int x, y, width, height, hits; 
	
	public static int currLevel = 1;
	Color col;
	private boolean alive;
	public static Brick[] bricks = new Brick[0];
	public static Particle[] particles = new Particle[20];
	public static int points = 0;
	public static boolean inProgress = false;
	public static int lives = 3;
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
		if(!Brick.inProgress) {
			Brick.inProgress = true;
			bricks = new Brick[numOfBricks];
			int x = 1, y = 1;
			int currLifeCount = numOfBricks / bricksPerRow + 1;
			int count = 1;
			for(int i = 0; i < bricks.length; i ++) {
				System.out.println("Brick Made");
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
			case 6: 
				col = WHITE;
				break;
			case 7:
				col = BLUE;
				break;
			case 8:
				col = ORANGE;
				break;
			case 9:
				col = DARK_YELLOW;
				break;
			case 10:
				col = LIGHT_GREEN;
				break;
		}
	}
	
	public void collided() {
		changeBrick();
		points += 100;
	}
	public void draw(Graphics2D pb) {
		if(this.alive == true) {
			pb.setColor(col);
			pb.fill(this);
		}
	}
	
	public void makeParticles() {
		int count = 0;
		for(int i = 0; i < this.particles.length; i ++) {
			particles[i] = new Particle(this.x+count, this.y, 5, 5, this.col);
			count += this.width/10;
			if(i == 9) {
				count = 0;
				y += this.height/2;
			}
//			System.out.println("particle made");
		}
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    	final Runnable runnable = new Runnable() {
    		int countDownStarter = 2;
                public void run() {
                    countDownStarter--;
                    if (countDownStarter == 0) {
                    	makeParticlesNull();
                        scheduler.shutdown();
                    }
                }
            };
            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
	}
	public void makeParticlesNull() {
		for(int i = 0; i < this.particles.length; i ++) {
			this.particles[i] = null;
		}
	}
	public static void collideWithBall(Ball ball) {
		
		for(Brick b : bricks) {
			if(b.intersects(ball) && b.alive == true) {
				if(Math.random() > 0.25) {
					PowerUp powerUp = new PowerUp(b.x, b.y);
				}
				Scoreboard.addScore();
				int dir = GDV5.collisionDirection(b, ball, ball.getdX(), ball.getdY());
				b.makeParticles();
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
	
	
	
	public static void destroyBricks() {
		for(Brick b : Brick.bricks) {
			b.collided();
		}
	}
	
	public static void changeLevel() {
		Brick.currLevel ++;
		Brick.inProgress = false;
		Brick.lives = 3;
		Paddle.speed = 3;
	}
	
	public static void drawBricks(Graphics2D win, Ball ball) {
		if(!stillBricks()) {
			ball.resetBall();
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    	final Runnable runnable = new Runnable() {
	    		int countDownStarter = 3;
	                public void run() {
	                    countDownStarter--;
	                    if (countDownStarter == 0) {
	                    	ball.setdX(3);
	                    	ball.setdY(3);
	                        scheduler.shutdown();
	                    }
	                }
	            };
	            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
			changeLevel();
			
		}
		for(int i = 0; i < Brick.bricks.length; i ++) {
			Brick.bricks[i].draw(win);
		}
	}
	
	public static boolean stillBricks() {

		for(int i = 0; i < Brick.bricks.length; i ++) {
			if(Brick.bricks[i].alive == true) {
				return true;
			}
		}
		return false;
	}
	
	public static void drawParticles(Graphics2D win) {
		for(int i = 0; i < Brick.particles.length; i ++) {
			if(Brick.particles[i] != null) {
				win.setColor(Brick.particles[i].col);
				win.fill(Brick.particles[i]);
				Brick.particles[i].move();
			}
		}
	}
}

