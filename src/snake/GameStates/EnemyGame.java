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
import snake.gameLookGood.*;
public class EnemyGame extends SnakeGameState {
	private SnakeGame snakeState;
	static int maxX = GDV5.getMaxWindowX();
	static int maxY = GDV5.getMaxWindowY();
	public static int rows = 20;
	public static int cols = 20;
	Grid grid = new Grid(maxX, maxY-100, rows, cols);
	actualSnake snake = new actualSnake(4, 4, true, 'd', rows, cols); 
	EnemySnake enemySnake = new EnemySnake(4, 15, 'd', rows, cols);
	Food foodSpawner = new Food("Food");
	int localTimer = 0;
	Scoreboard scoreboard = new Scoreboard();
	Sound music = new Sound("src/snakeSounds/enemysnakebg.wav");
	
	public EnemyGame (SnakeGame snake) {
		this.snakeState = snake;
		music.se.play();
//		actualSnake.addTail();
//		actualSnake.addTail();
//		enemySnake.moveEnemy();
	}
	
	public void update() {
		
		snake.chooseDir();
		if(localTimer%10 == 0) {
			if(!snake.snakeMoveHead()) { music.se.stop(); snakeState.setState(new EndGame(snakeState));}
			enemySnake.moveEnemy();
			enemySnake.enemyMoveTail();
		}
		localTimer ++;
		if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			snakeState.setState(new MenuGameState(snakeState));
			music.se.stop();
			actualSnake.allSnakeParts.clear();
			EnemySnake.enemySnakeParts.clear();
		}
	}
	
	
	
	public void draw(Graphics2D win) {
		grid.draw(win);
		scoreboard.draw(win);
		foodSpawner.spawnFood();
		Grid.updateGrid(win, rows, cols);
	}
}
