package snake.GameObjects;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.util.*;
import java.awt.image.*;

public class EnemySnake {
	static ArrayList<EnemySnake> enemySnakeParts = new ArrayList<>();
	int lastVisitedX, lastVisitedY, currX, currY;
	static char direction;
	public EnemySnake(int currX, int currY, char direction, int maxRow, int maxCol) {
		this.currX = currX;
		this.currY = currY;
//		this.sprite = sprite;
		actualSnake.maxRow = maxRow;
		actualSnake.maxCol = maxCol;
		Grid.map[currX][currY].type = "EnemySnake";
		enemySnakeParts.add(this);
	}
	
	public EnemySnake(int currX, int currY) {
		this.currX = currX;
		this.currY = currY;
		Grid.map[currX][currY].type = "EnemyTail";
		enemySnakeParts.add(this);
	}
	
	public void moveEnemy() {
		ArrayList<QItem> path = QItem.findPath(this);
		path.remove(0);
		path.remove(0);
//		System.out.println(path.size());
//		for(int i = 0; i < path.size(); i ++) {
//			System.out.println("X: " + path.get(i).row);
//			System.out.println("Y: " + path.get(i).col);
//			System.out.println("-----");
//		}
		this.lastVisitedX = this.currX;
		this.lastVisitedY = this.currY;
		Grid.map[currX][currY].type = "Blank";
		this.currX = path.get(0).row;
		this.currY = path.get(0).col;
		if(Grid.map[currX][currY].type.equals("Food")) {
			enemyAddTail();
			Food.spawn = 0;
		}
		Grid.map[currX][currY].type = "EnemySnake";
	}
	
	public void enemyMoveTail() { //moving the tail pieces
		if(enemySnakeParts.size() > 1) {
			for(int i = 1; i < enemySnakeParts.size(); i ++) {
				EnemySnake snake = enemySnakeParts.get(i);
				snake.lastVisitedX = snake.currX;
				snake.lastVisitedY = snake.currY;
				Grid.map[snake.currX][snake.currY].type = "Blank"; 
				snake.currX = enemySnakeParts.get(i-1).lastVisitedX;
				snake.currY = enemySnakeParts.get(i-1).lastVisitedY;
				Grid.map[snake.currX][snake.currY].type = "EnemyTail"; 
			}
		}
	}
	
	public static void enemyAddTail() { //adding a tail to the snake

		EnemySnake newSnake = new EnemySnake(enemySnakeParts.get(enemySnakeParts.size()-1).lastVisitedX, EnemySnake.enemySnakeParts.get(enemySnakeParts.size()-1).lastVisitedY);
	}	
}
class QItem {
	  int row;
	  int col;
	  int dist;
	  ArrayList<QItem> list = new ArrayList<>();
	  public QItem(int row, int col, int dist, ArrayList<QItem> path) {
	    this.row = row;
	    this.col = col;
	    this.dist = dist;
	    path.add(this);
	    list = new ArrayList<>(path);
	  }
	
	
	  public static ArrayList<QItem> findPath(EnemySnake snake)
	  {
		ArrayList<QItem> path = new ArrayList<>();
	    QItem source = new QItem(0, 0, 0, path);
	    
	    Queue<QItem> queue = new LinkedList<>();
	    source.row = snake.currX;
	    source.col = snake.currY;
	    
	    queue.add(new QItem(source.row, source.col, 0, path));
	    
	    
	    boolean[][] visited
	      = new boolean[Grid.map.length][Grid.map[0].length];
	    visited[source.row][source.col] = true;
	    
	    while (queue.isEmpty() == false) {
	      QItem p = queue.remove();
	       
	      // Destination found;
	      if (Grid.map[p.row][p.col].type.equals("Food")) {
	    	  System.out.println("hit");
	    	  return p.list;
	      }
	      if(isValid(p.row-1, p.col, visited)) {
	    	  ArrayList<QItem> newAdded = new ArrayList<>(p.list);
	    	  queue.add(new QItem(p.row-1, p.col, p.dist+1, newAdded));
	    	  visited[p.row-1][p.col] = true; 
	      }
	      if(isValid(p.row+1, p.col, visited)) {
	    	  ArrayList<QItem> newAdded = new ArrayList<>(p.list);
	    	  queue.add(new QItem(p.row+1, p.col, p.dist+1, newAdded));
	    	  visited[p.row+1][p.col]= true; 
	      }
	      if(isValid(p.row, p.col-1, visited)) {
	    	  ArrayList<QItem> newAdded = new ArrayList<>(p.list);
	    	  queue.add(new QItem(p.row, p.col-1, p.dist+1, newAdded));
	    	  visited[p.row][p.col-1] = true;
	      }
	      if(isValid(p.row, p.col+1, visited)) {
	    	  ArrayList<QItem> newAdded = new ArrayList<>(p.list);
	    	  queue.add(new QItem(p.row, p.col+1, p.dist+1, newAdded));
	    	  visited[p.row][p.col+1] = true;
	      }
	      
	    }
	    return path;
	  }
	   
	  // checking where it's valid or not
	  private static boolean isValid(int x, int y, boolean[][] visited)
	  {
	    if (x >= 0 && y >= 0 && x < Grid.map.length && y < Grid.map[0].length && !Grid.map[x][y].type.equals("EnemyTail")
	    		&& visited[x][y] == false && !Grid.map[x][y].type.equals("Snake") && !Grid.map[x][y].type.equals("Tail")) {
	      return true;
	    }
	    return false;
	  }
	 }