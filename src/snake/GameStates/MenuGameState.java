package snake.GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet.FontAttribute;

import utilities.GDV5;

public class MenuGameState extends SnakeGameState {
	private SnakeGame snakeState;

	public final static Color LIGHT_GREY = new Color(204,204,204);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public MenuGameState(SnakeGame state) {
		this.snakeState = state;
	}
	
	public void update() {
		if(GDV5.KeysPressed[KeyEvent.VK_1]) {
			snakeState.setState(new MainGame(snakeState));
		}
		if(GDV5.KeysPressed[KeyEvent.VK_2]) {
			snakeState.setState(new EnemyGame(snakeState));
		}
		if(GDV5.KeysPressed[KeyEvent.VK_3]) {
			snakeState.setState(new TwoPlayer(snakeState));
		}
		if(GDV5.KeysPressed[KeyEvent.VK_4]) {
			snakeState.setState(new Help(snakeState));
		}
	}
	
	public void draw(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 32));
		win.drawString("Welcome to Snake!", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/4, 40);
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		win.drawString("By Brian Hu", GDV5.getMaxWindowX() / 2 - GDV5.getMaxWindowX()/6, 100);
		win.setColor(VERY_LIGHT_RED);
		win.setFont(new Font("Courier New", Font.BOLD, 24));
		win.drawString("1. Single Player (Normal) ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 180);
		win.drawString("2. Single Player (With Enemy Snake) ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 260);
		win.drawString("3. Local Multiplayer ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 340);
		win.drawString("4. Help Screen ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 420);
	}
	
}
