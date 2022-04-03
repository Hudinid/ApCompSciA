package Battleship;

public abstract class Boat {
	private int team;
	private Coordinates location;
	private int direction;
	private int health;
	private int strength;
	private int vision; 
	
	public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {
		this.team = team;
		this.location = location;
		this.direction = direction; 
		this.health = health;
		this.strength = strength;
		this.vision = vision;
	}
	
	public int getTeam() {
		return this.team;
	}
	
	public Coordinates getLocation() {
		return this.location;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public char getDirection() {
		switch(this.direction) {
			case(0):
				return '\u2191';
			case(1):
				return '\u2197';
			case(2):
				return '\u2192';
			case(3):
				return '\u2198';
			case(4):
				return '\u2193';
			case(5):
				return '\u2199';
			case(6):
				return '\u2190';
			case(7):
				return '\u2196';
			default:
				return 'x';
		}
	}
	
	public String getDir() {
		switch(this.direction) {
			case(0):
				return "N";
			case(1):
				return "NE";
			case(2): 
				return "E";
			case(3):
				return "SE";
			case(4):
				return "S";
			case(5):
				return "SW";
			case(6):
				return "W";
			case(7):
				return "NW";
			default:
				return "X";
		}
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getVision() {
		return this.vision;
	}
	
	abstract String getID();
	
	abstract String act(int[] choices, World world);
	
	abstract String getActions();
	
	public void setLocation(Coordinates coord) {
		this.location = coord;
	}
	
	public String move(World world) { 
		String ret = "";
		
		ret += this.toString(); 
		
		Coordinates movedPlace = world.getAdjacentLocation(this.location, this.direction);
		String currentLocation = this.location.toString();	
		
		if(movedPlace != null && world.isLocationValid(movedPlace)) {
			String movedLocation = movedPlace.toString();
			if(world.setOccupant(this, movedPlace)) {
				world.setNull(this.location);
				this.setLocation(movedPlace);
				ret += " moves from " + currentLocation + " to " + movedLocation;
			}
			else {
				ret += " cannot move to " + movedPlace + " as it is occupied. ";
			}
			
		}
		else {
			ret += " cannot move off the map";
		}
		return ret;
	}
	
	public String turn(int input) {
		String ret = "";
		ret += this.getID();
		if(input == -1) {
			ret += " turned left";
		}
		else ret += " turned right";
		
		
		this.direction += input;
		if(direction < 0) this.direction = 7;
		if(direction > 7) this.direction = 0;
		ret += ", now facing " + this.getDirection();
		return ret; 
	}
	
	public String takeHit(int attack) {
		this.health -= attack;
		String ret = "";
		ret += this.toString();
		if(this.health <= 0) {
			Coordinates deathCoord = new Coordinates(this.location.getX(), this.location.getY());
			World.setNull(deathCoord);
			return ret + " has sunk";
		}
		ret += " has taken " + attack + " damage.";
		return ret;
	}
	
	public String toString() {
//		char addOn =(char) (this.getTeam() + 65);
		String ret = "";
//		ret += addOn;
		ret += this.getID();
		return ret;
	}
}
