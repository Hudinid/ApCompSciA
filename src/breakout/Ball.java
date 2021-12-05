package breakout;
import java.awt.Rectangle;
import utilities.GDV5; 
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.Color;

public class Ball extends Rectangle {
	Color col;
	private int dX, dY, width, height;
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	
	
	public Ball(int dX, int dY, int startX, int startY, int width, int height) {
		super(startX, startY, width, height);
		this.dX = dX;
		this.dY = dY;
		this.width = width;
		this.height = height;
	}
	
	public void setdX(int dX) {
		this.dX = dX;
	}
	
	public void setdY(int dY) {
		this.dY = dY;
	}
	
	public int getdY() {
		return this.dY;
	}
	
	public int getdX() {
		return this.dX;
	}
	
	public void move(Paddle p1) {
		this.x += dX;
		this.y += dY;
		if(this.x+this.width >= GDV5.getMaxWindowX() || this.x <= 0) {
			dX *= -1;
		}
		
		if(this.intersects(p1)) {
			this.dY *= -1;
		}
		
		if(this.y > GDV5.getMaxWindowY()) {
			this.x = 300;
			this.y = 300;
			dX = 0;
			dY = 0;
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    	final Runnable runnable = new Runnable() {
	    		int countDownStarter = 3;
	                public void run() {
	                    countDownStarter--;
	                    if (countDownStarter == 0) {
	                    	dX = 3;
	                    	dY = 3;
	                        scheduler.shutdown();
	                    }
	                }
	            };
	            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
		}
		if(this.y <= 0) {
			this.dY *= -1;
		}
	}
}
