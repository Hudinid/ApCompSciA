package pong;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;

public class Paddle extends Rectangle{
	private int maxX, maxY, h;
	private char dir;
	private boolean AI;
	public Paddle(int startX, int startY, int w, int h, int maxX, int maxY, boolean AI) {
		super(startX, startY, w, h);
		this.maxX = maxX;
		this.maxY = maxY;
		this.h = h;
		this.AI = AI;
	}
	
	public int getH() {
		return this.h;
	}
		
	public void setAI(boolean AI) {
		this.AI = AI;
	}
	
	public boolean getAI() {
		return this.AI;
	}
	public void leftMove(boolean KeysPressed[]) {
		if(!this.AI) {
			if(KeysPressed[KeyEvent.VK_W] && this.y > 0 && this.AI == false) {
				this.y -= 10;
				this.dir = 'u';
			}
			
			else if(KeysPressed[KeyEvent.VK_S] && this.y < maxY-h && this.AI == false) {
				this.y+=10;
				this.dir = 'd';
			}
			else {
				this.dir = 'x';
			}
		}
	}
	
	public void rightMove(boolean KeysPressed[], Ball b1) {
		if(!this.AI) {
			if(KeysPressed[KeyEvent.VK_UP] && this.y > 0 && this.AI == false) {
				this.y -= 10;
				this.dir = 'u';
			}
			
			else if(KeysPressed[KeyEvent.VK_DOWN] && this.y < maxY-h && this.AI == false) {
				this.y+=10;
				this.dir = 'd';
			}
			
			else {
				this.dir = 'x';
			}
		}
		else {
			if(this.y > 0 || this.y < maxY-h) {
				this.y = b1.y-h/2;
			}
			
		}
	}
	public char getDir() {
		return this.dir;
	}
}
