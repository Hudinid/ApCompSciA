package Battleship;
import java.util.*;
import java.io.*;

public abstract class ScoutBoat extends Boat {
	public ScoutBoat(int team, Coordinates Location, int direction, int health, int vision) {
		super(team, Location, direction, health, 1, vision);
	}
	
	public String takeHit(int strength) {
		
		int random = (int) (Math.random() * 4);
		if(random == 0) return super.toString() + " has dodged the attack!";
		
		return super.takeHit(strength);
		
	}
}
