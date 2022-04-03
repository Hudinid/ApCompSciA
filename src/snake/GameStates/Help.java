package snake.GameStates;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import snake.GameObjects.Grid;
import snake.GameObjects.Scoreboard;
import snake.GameObjects.actualSnake;
import snake.GameObjects.EnemySnake;
import snake.GameObjects.Food;

public class Help extends SnakeGameState {
	private SnakeGame snakeState;
	public final static Color LIGHT_GREY = new Color(204,204,204);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public Help(SnakeGame state) {
		this.snakeState = state;
	}
	
	public Help() {
		
	}
	
	public void update() {
		if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			snakeState.setState(new MenuGameState(snakeState));
		}
	}
	
	public void draw(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 32));
		win.drawString("Controls", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/4, 40);
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		win.drawString("WASD To Move", GDV5.getMaxWindowX() / 2 - GDV5.getMaxWindowX()/3, 100);
		win.setColor(VERY_LIGHT_RED);
		win.setFont(new Font("Courier New", Font.BOLD, 24));
		win.drawString("Collect as many apples as possible :D ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 180);
		win.drawString("Hit Escape to go back!", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 240);
	}
}
