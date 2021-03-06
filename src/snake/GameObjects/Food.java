package snake.GameObjects;
import java.awt.Rectangle;
import utilities.GDV5;
import java.util.*;
import java.awt.Color;
import snake.GameStates.MainGame;
import snake.gameLookGood.*;
import java.awt.Color;

public class Food {
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color PINK = new Color(255, 192, 203);
	public static int spawnX = (int) (Math.random()* MainGame.rows);
	public static int spawnY = (int) (Math.random()* MainGame.cols);
	public static int power = (int) (Math.random() * 21);
	
	String type;
	static int spawn = 0;
	public Food(String type) {	
		this.type = type;
		Food.spawn = 0;
	}
	
	public void spawnFood() {
		if(spawn == 0) {
		Sound eatSound = new Sound("src/snakeSounds/eatsound.wav");
		eatSound.se.play();
		spawn = 1;
		spawnX = (int) (Math.random()* MainGame.rows);
		spawnY = (int) (Math.random() * MainGame.cols);
		power = (int) (Math.random() * 12);
		
//		if(power == 4) { // slow down power 
//			this.type = "Slow";
//		}
		
//		if(power == 12) { // mario star (invincible)
//			this.type = "MarioStar";
//		}
//		if(power == 16) { //grab a food in the same row / col if you're facing it (magnet)
//			this.type = "Magnet";
//		}
		if(power == 8) { //remove a tail 
			this.type = "RemoveTail";
		}
		else {
			this.type = "Food";
		}
		while(!Grid.map[spawnX][spawnY].type.equals("Blank")) {
			spawnX = (int) (Math.random()* MainGame.rows);
			spawnY = (int) (Math.random() * MainGame.cols);
		}
		Grid.map[spawnX][spawnY].type = type;
		
		}
	}
	
	
}
