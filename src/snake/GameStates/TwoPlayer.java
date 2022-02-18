package snake.GameStates;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import snake.GameObjects.Scoreboard;
import snake.GameObjects.Grid;
import snake.GameObjects.actualSnake;
import snake.gameLookGood.Sound;
import snake.GameObjects.EnemySnake;
import snake.GameObjects.Food;
import snake.GameObjects.otherSnake;
public class TwoPlayer extends SnakeGameState {
	private SnakeGame snakeState;
	static int maxX = GDV5.getMaxWindowX();
	static int maxY = GDV5.getMaxWindowY();
	public static int rows = 20;
	public static int cols = 20;
	Grid grid = new Grid(maxX, maxY-100, rows, cols);
	Scoreboard scoreboard = new Scoreboard();
	actualSnake snake = new actualSnake(4, 4, true, 'd', rows, cols); 
	otherSnake otherSnakes = new otherSnake(4, 15, true, 'd', rows, cols);
	Food foodSpawner = new Food("Food");
	int localTimer = 0;
	Sound music = new Sound("src/snakeSounds/normalBG.wav");
	
	
	public TwoPlayer(SnakeGame snake) {
		this.snakeState = snake;
		music.se.play();
//		actualSnake.addTail();
//		actualSnake.addTail();
//		enemySnake.moveEnemy();
	}
	
	public void update() {
		snake.chooseDir(); otherSnakes.chooseDir();
		if(localTimer%15 == 0) {
			if(!snake.snakeMoveHead()) { music.se.stop(); snakeState.setState(new EndGame(snakeState));}
			if(!otherSnakes.snakeMoveHead()) { music.se.stop(); snakeState.setState(new EndGame(snakeState));}
		}
		localTimer ++;
		if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			music.se.stop();
			snakeState.setState(new MenuGameState(snakeState));
			actualSnake.allSnakeParts.clear();
			otherSnake.allSnakeParts.clear();
		}
	}
	
	
	
	public void draw(Graphics2D win) {
		grid.draw(win);
		foodSpawner.spawnFood();
//		scoreboard.draw(win);
		Grid.updateGrid(win, rows, cols);
	}
}
