package snake.GameObjects;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.util.*;

import snake.GameStates.MenuGameState;
import snake.GameStates.SnakeGame;
import snake.gameLookGood.Sound;

import java.awt.image.*;

public class otherSnake {
	
	int lastVisitedX, lastVisitedY;
	int size;
	static char direction; 
	static int speed;
	boolean isHead;
	public static ArrayList<otherSnake> allSnakeParts = new ArrayList<>();
	public BufferedImage sprite;
	public int currX, currY;
	static int maxRow, maxCol;
	private SnakeGame snakeState;
	static int powerUp = 0;
	
	public otherSnake(int currX, int currY, boolean isHead, char direction, int maxRow, int maxCol) {
		this.currX = currX;
		this.currY = currY;
//		this.sprite = sprite;
		this.isHead = isHead;
		actualSnake.maxRow = maxRow;
		actualSnake.maxCol = maxCol;
		Grid.map[currX][currY].type = "otherSnake";
		allSnakeParts.add(this);
	}
	
	public otherSnake(int currX, int currY) {
		this.currX = currX;
		this.currY = currY;
		Grid.map[currX][currY].type = "otherSnake";
		isHead = false;
		allSnakeParts.add(this);
	}
	
	public void chooseDir() {
		if(GDV5.KeysPressed[KeyEvent.VK_UP] && direction != 'd') {
			direction = 'u';
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_DOWN] && direction != 'u') {
			direction = 'd';
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_LEFT] && direction != 'r') {
			direction = 'l';
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_RIGHT] && direction != 'l') {
			direction = 'r';
		}
	}
	
	public void usePowerUp() {
		if(GDV5.KeysPressed[KeyEvent.VK_SPACE]) {
			if(powerUp == 1) {
				powerUp = 0;
				allSnakeParts.remove(allSnakeParts.size()-1);
			}
		}
	}
	
	public boolean snakeMoveHead() { //i made a for loop that goes once because i was too lazy to change it after i chagned my movement for the snake
		for(int i = 0; i < 1; i ++) {
			int x = allSnakeParts.get(0).currX;
			int y = allSnakeParts.get(0).currY;
			switch(otherSnake.direction) {
				case('u'): //if it is up
					if(y == 0 && this.isHead == true) {
						cleanUp(); 
						return false;
					}
					Grid.map[x][y].type = "Blank";
					lastVisitedX = x;
					lastVisitedY = y;
					allSnakeParts.get(0).currY --;
					y--;
					if(Grid.map[x][y].type.equals("RemoveTail")) { Food.spawn = 0; Scoreboard.score++; actualSnake.powerUp = 1;};
					if(Grid.map[x][y].type.equals("Food")) { addTail(); Food.spawn = 0; Scoreboard.score++;
//					Sound.main(null);
					}
					if(Grid.map[x][y].type.equals("Tail") || Grid.map[x][y].type.equals("EnemyTail")) { cleanUp(); return false; }
					Grid.map[x][y].type = "otherSnake";
					break;
				case('d'): // down
					if(y == maxCol-1 && this.isHead == true) {
						cleanUp(); 
						return false;
					}
					Grid.map[x][y].type = "Blank";
					lastVisitedX = x;
					lastVisitedY = y;
					allSnakeParts.get(0).currY ++;
					y ++;
					if(Grid.map[x][y].type.equals("RemoveTail")) { Food.spawn = 0; Scoreboard.score++; actualSnake.powerUp = 1;};
					if(Grid.map[x][y].type.equals("Food")) { addTail(); Food.spawn = 0;Scoreboard.score++; }
					if(Grid.map[x][y].type.equals("Tail") || Grid.map[x][y].type.equals("EnemyTail")) {cleanUp(); return false; }
					Grid.map[x][y].type = "otherSnake";
					break;
				case('l'): //left
					if(x == 0 && this.isHead == true) {
						cleanUp(); 
						return false;
					}
					Grid.map[x][y].type = "Blank";
					lastVisitedX = x;
					lastVisitedY = y;
					allSnakeParts.get(0).currX --;
					x --;
					if(Grid.map[x][y].type.equals("RemoveTail")) { Food.spawn = 0; Scoreboard.score++; actualSnake.powerUp = 1;};
					if(Grid.map[x][y].type.equals("Food")) { addTail(); Food.spawn = 0;Scoreboard.score++; }
					if(Grid.map[x][y].type.equals("Tail") || Grid.map[x][y].type.equals("EnemyTail")) { cleanUp(); return false; }
					Grid.map[x][y].type = "otherSnake";
					break;
				case('r'): // right
					
					if(x == maxRow-1 && this.isHead == true) {
						cleanUp(); 
						return false;
					}
					Grid.map[x][y].type = "Blank";
					lastVisitedX = x;
					lastVisitedY = y;
					allSnakeParts.get(i).currX ++;
					x ++;
					if(Grid.map[x][y].type.equals("RemoveTail")) { Food.spawn = 0; Scoreboard.score++; actualSnake.powerUp = 1;};
					if(Grid.map[x][y].type.equals("Food")) { addTail(); Food.spawn = 0; Scoreboard.score++;}
					if(Grid.map[x][y].type.equals("Tail") || Grid.map[x][y].type.equals("EnemyTail")) { cleanUp(); return false; }
					Grid.map[x][y].type = "otherSnake";
					break;
			}
		}
		moveTail();
		return true;
	}
	
	public void moveTail() { //moving the tail pieces
		if(allSnakeParts.size() > 1) {
			for(int i = 1; i < allSnakeParts.size(); i ++) {
				otherSnake snake = allSnakeParts.get(i);
				snake.lastVisitedX = snake.currX;
				snake.lastVisitedY = snake.currY;
				Grid.map[snake.currX][snake.currY].type = "Blank"; 
				snake.currX = allSnakeParts.get(i-1).lastVisitedX;
				snake.currY = allSnakeParts.get(i-1).lastVisitedY;
				Grid.map[snake.currX][snake.currY].type = "EnemyTail"; 
			}
		}
	}
	
	public static void addTail() { //adding a tail to the snake
		otherSnake newSnake = new otherSnake(allSnakeParts.get(allSnakeParts.size()-1).lastVisitedX, otherSnake.allSnakeParts.get(allSnakeParts.size()-1).lastVisitedY);
	}
	
	public void cleanUp() {
		
		actualSnake.allSnakeParts.clear();
		otherSnake.allSnakeParts.clear();
		EnemySnake.enemySnakeParts.clear();

		for(int i = 0; i < Grid.map.length; i ++) {
			for(int j = 0; j < Grid.map[i].length; j ++) {
				Grid.map[i][j].type = "Blank";
			}
		}
	}
	
}
