package snake;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.util.*;
import java.awt.image.*;

public class actualSnake extends Rectangle {
	
	int lastVisitedX, lastVisitedY;
	int size;
	static char direction; 
	static int speed;
	static ArrayList<actualSnake> allSnakeParts = new ArrayList<>();
	public BufferedImage sprite;
	public actualSnake(int size, int startX, int startY, BufferedImage sprite) {
		super(startX, startY, size, size);
		this.sprite = sprite;
	}
	
	public void chooseDir(boolean[] KeysPressed) {
		if(KeysPressed[KeyEvent.VK_W]) {
			direction = 'u';
		}
		else if(KeysPressed[KeyEvent.VK_S]) {
			direction = 'd';
		}
		else if(KeysPressed[KeyEvent.VK_A]) {
			direction = 'l';
		}
		else if(KeysPressed[KeyEvent.VK_D]) {
			direction = 'r';
		}
	}
	
	public void move() {
		
	}
}
