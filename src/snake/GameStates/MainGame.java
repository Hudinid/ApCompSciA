package snake.GameStates;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;

import snake.GameObjects.Grid;
import snake.GameObjects.actualSnake;
import snake.GameObjects.EnemySnake;
import snake.GameObjects.Food;

public class MainGame extends SnakeGameState {
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

	
	public MainGame(SnakeGame snake) {
		this.snakeState = snake;
//		actualSnake.addTail();
//		actualSnake.addTail();
//		enemySnake.moveEnemy();
	}
	
	public void update() {
		
		snake.chooseDir();
		if(localTimer%2 == 0) {

			snake.snakeMoveHead();
			snake.moveTail();
			
			enemySnake.moveEnemy();
			enemySnake.enemyMoveTail();
			
			
		}
		
		localTimer ++;
	}
	
	public void draw(Graphics2D win) {
		grid.draw(win);
		foodSpawner.spawnFood();
		Grid.updateGrid(win, rows, cols);
	}
}
