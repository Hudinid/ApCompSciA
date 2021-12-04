package pong;

import java.awt.Rectangle;
import utilities.GDV5; 

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.Color;




public class Ball extends Rectangle {
	int dX, dY, maxX, maxY, w, h, countDownStarter;
	boolean hitP2 = false;
	boolean hitP1 = false;
	
	Color color;
	char dir;
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public static final Color YELLOW = new Color(255, 204, 0);
	public static final Color PURPLE = new Color(102, 0, 153);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color DARK_GREEN = new Color(0, 153, 0);
	public static final Color BLUE = new Color(0, 255, 0);
	public static final Color GREEN = new Color(0, 204, 0);
	Color[] colors = {VERY_LIGHT_BLUE, VERY_LIGHT_RED, VERY_LIGHT_GREEN, YELLOW, PURPLE, WHITE, DARK_GREEN, BLUE, GREEN};
	public Ball(int w, int h, int maxX, int maxY) {
		super(0,0, w, h);
		dX = 5;
		dY = 5;
		this.maxX = maxX;
		this.maxY = maxY;
		this.w = w;
		this.h = h;
		countDownStarter = 0;
		this.x = maxX/2;
		this.y = maxY/2;
		this.dir = 'd'; // ball always start down
		
	}
	
	
	//swap ball direction
	public char swapDir() {
		if(this.dir == 'd') {
			return 'u';
		}
		else {
			return 'd';
		}
	}
	
	//check if ball is smashball or no
	public boolean validSmashBall(Paddle paddle) {
		if(this.dir == 'u' && paddle.getDir() == 'd') {
			return true;
		}
		else if(this.dir == 'd' && paddle.getDir() == 'u') {
			return true;
		}
		else { 
			return false;
		}
	}
	
	public int smashBallValue(int num) { //dX
		if(num > 0) {
			return num + 2;
		}
		else {
			return (Math.abs(num) + 2) * -1; 
		}
	}
	
	public int smashBallYValue(int num) { //dY
		if(num > 0) {
			return num + 1;
		}
		else {
			return (Math.abs(num) + 1) * -1;
		}
	}
	
	public void newColor() {
		int random = (int)(Math.random()*9);
		System.out.println(random);
		Color tempColor = this.colors[random];
		System.out.println("New color");
		this.color = tempColor;
	}
	
	public void move(Ball b1, Paddle p1, Paddle p2, Scoreboard sb) {
		
		if(sb.selectedGame == true) {
			this.translate(dX, dY);
		}
		if(this.getX() >= maxX-w) {
			resetBall(p1, p2);
			sb.p1Score ++;
		}
		if(this.getX() <= 0 && p2.getAI() == false) {
			resetBall(p1, p2);
			sb.p2Score++;
		}
		else if(this.getX() <= 0 && p2.getAI() == true) {
			sb.prevRallyScore = sb.p1Score;
			sb.p1Score = 0;
			resetBall(p1,p2);
		}
		
		//if it intersects check stuff
		if(b1.intersects(p1) && hitP1 == false) {
			
			newColor();
			
			if(p2.getAI() == true) {
				sb.p1Score++;
			}
			hitP1 = true;
			hitP2 = false;	
			boolean temp = validSmashBall(p1); //set a temporary boolean to see if it is smash ball
			if(b1.y > p1.y && b1.y < p1.y+p1.getH()) {
				int dir = GDV5.collisionDirection(p1, b1, 10, 10); //idk why this is here
				if(dir == 1) { 
					this.dY *= -1;
					swapDir(); // change direction
					if(temp) {
						this.dX = smashBallValue(this.dX);
						this.dY = smashBallYValue(this.dY);
					}
				}
				else if(dir == 3) {
					this.dY *= -1;
					swapDir(); // change direction
					if(temp) {
						this.dX = smashBallValue(this.dX);
						this.dY = smashBallYValue(this.dY);
					}
				}
				else {
					this.dX *= -1;
					if(temp) {
						this.dX = smashBallValue(this.dX);
						this.dY = smashBallYValue(this.dY);
					}
				}
			}
			else {
				b1.dY*=-1;
				swapDir(); // change direction
				if(temp) {
					this.dX = smashBallValue(this.dX);
					this.dY = smashBallYValue(this.dY);
				}
			}
		}
		if(b1.intersects(p2) && hitP2 == false) {
			newColor();
			hitP2 = true;
			hitP1 = false;
			boolean temp = validSmashBall(p2);
			// buffer for prev position
			if(b1.y >= p2.y-5 && b1.y <= p2.y+p2.getH()+5) {
				int dir = GDV5.collisionDirection(p1, b1, 10, 10);
//				System.out.println(dir);
				if(dir == 1) { 
					
					this.dY *= -1;
					swapDir(); // change direction
					if(temp) {
						this.dX = smashBallValue(this.dX);
						this.dY = smashBallYValue(this.dY);
					}
				}
				else if(dir == 3) {
					this.dY *= -1;
					swapDir();
					if(temp) {
						this.dX = smashBallValue(this.dX);
						this.dY = smashBallYValue(this.dY);
					}
				}
				else {
					
					this.dX *= -1;
					if(temp) {
						this.dX = smashBallValue(this.dX);
						this.dY = smashBallYValue(this.dY);
					}
				}
			}
			else {
				b1.dY *= -1;
				swapDir();
				if(temp) {
					this.dX = smashBallValue(this.dX);
					this.dY = smashBallYValue(this.dY);
				}
			}
		}

		
		if(this.getY() >= maxY-h || this.getY() <= 0) {
			this.dY *= -1;
			swapDir();
		}
	}
	
	
	public void resetBall(Paddle p1, Paddle p2) {
		hitP1 = false;
		hitP2 = false;
		this.x = this.maxX/2 - this.w/2;
		this.y = this.maxY/2;
		dX = 0;
		dY = 0;
    	countDownStarter = 4;
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    	final Runnable runnable = new Runnable() {
                public void run() {
                    countDownStarter--;
                    if (countDownStarter == 0) {
                    	dX = 5;
                    	dY = 5;
                        scheduler.shutdown();
                    }
                }
            };
            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
	}
	


}

