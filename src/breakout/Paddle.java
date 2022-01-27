package breakout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;

public class Paddle extends Rectangle {
	int posX, posY, width, height;
	public static int speed;
	public Paddle(int startX, int startY, int width, int height) {
		super(startX, startY, width, height);
		this.posX = startX;
		this.posY = startY;
		this.width = width;
		this.height = height;
		Paddle.speed = 3;
	}
	
	public void movePaddle(boolean[] KeysPressed) {
		if(this.x + this.width > 800) {
			this.x = 800;
		}
		if(KeysPressed[KeyEvent.VK_LEFT] && this.x > 0) {
			this.x -= Paddle.speed;
		}
		if(KeysPressed[KeyEvent.VK_RIGHT] && this.x+this.width < 600) {
			this.x += Paddle.speed;
		}
		if(KeysPressed[KeyEvent.VK_A]  && this.x > 0) {
			this.x -= Paddle.speed;
		}
		if(KeysPressed[KeyEvent.VK_D]  && this.x+this.width < 600) {
			this.x += Paddle.speed;
		}
	}
}
