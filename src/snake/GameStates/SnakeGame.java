package snake.GameStates;
import java.awt.Graphics2D;

import utilities.GDV5;
public class SnakeGame extends GDV5 {
	
	private SnakeGameState state = new MenuGameState(this);
	
	public void setState(SnakeGameState value) {
		this.state = value;
	}
	
	public static void main(String[] args) {
		SnakeGame game = new SnakeGame();
		game.start();
	}
	
	public void update() {
		state.update();
	}
	
	public void draw(Graphics2D win) {
		state.draw(win);
	}
}
