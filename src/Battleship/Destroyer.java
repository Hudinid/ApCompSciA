package Battleship;

public class Destroyer extends Boat implements Attacker{
	public Destroyer(int teamID, Coordinates location, int dir) {
		super(teamID, location, dir, 2, 2 , 1);
	}
	
	public String getID() {
		return "D" + super.getTeam();
	}
	
	public String getActions() {
		return "\"Choose any of the following actions for the Destroyer:\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Attack";
	}
	
	public String act(int[] choices, World w) {
		String result = "";
		
		for(int i = 0; i < choices.length; i ++) {
			
			if(choices[i] == 1) {
				result += super.move(w) + "\n";
			}
			
			if(choices[i] == 2) {
				result += super.turn(-1) + "\n";
			}
			
			if(choices[i] == 3) {
				result += super.turn(1) + "\n";
			}
			
			if(choices[i] == 4) {
				result += this.attack(w) + "\n";
			}
		}
		
		return result;
	}
	
	public String takeHit(int strength) {
		int random = (int) (Math.random()*1);
		
		if(random == 0) {
			return super.takeHit(strength);
		}
		else return this.getID() + " avoids the attack.";
	}
	
	public String attack(World w) {
		Coordinates ourCoord = this.getLocation();
		int ourX = ourCoord.getX();
		int ourY = ourCoord.getY();

		switch(this.getDir()) {
			case("N"):
				for(int i = 0; i < this.getVision(); i ++) {
					int checkedX = ourX;
					int checkedY = ourY- (i+1);
					if(checkedY < 0) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			case("NE"):
				for(int i = 0; i < this.getVision(); i++) {
					int checkedX = ourX + (i+1);
					int checkedY = ourY - (i+1);
					if(checkedX >= w.getWidth() || checkedY < 0) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			case("E"):
				for(int i = 0; i < this.getVision(); i ++) {
					int checkedX = ourX + (i+1);
					int checkedY = ourY - (i+1);
					if(checkedX >= w.getWidth()) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendy Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			case("SE"):
				for(int i = 0; i < this.getVision(); i++) {
					int checkedX = ourX + (i+1);
					int checkedY = ourY + (i+1);
					if(checkedX >= w.getWidth() || checkedY >= w.getHeight()) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			case("S"):
				for(int i = 0; i < this.getVision(); i ++) {
					int checkedX = ourX;
					int checkedY = ourY + (i + 1);
					if(checkedY >= w.getHeight()) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();	
					}
				}
				return "There are no boats in range currently.";
			case("SW"):
				for(int i = 0; i < this.getVision(); i ++) {
					int checkedX = ourX - (i + 1);
					int checkedY = ourY + (i + 1);
					if(checkedY >= w.getHeight() || checkedX < 0) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			case("W"):
				for(int i = 0; i < this.getVision(); i ++) {
					int checkedX = ourX - (i+1);
					int checkedY = ourY;
					if(checkedX < 0) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			case("NW"):
				for(int i = 0; i < this.getVision(); i ++) {
					int checkedX = ourX - (i+1);
					int checkedY = ourY - (i+1);
					if(checkedX < 0 || checkedY < 0) continue;
					Boat boat = w.getOccupant(new Coordinates(checkedX, checkedY));
					if(boat != null) {
						if(boat.getTeam() != this.getTeam()) return "" + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			default:
				return "code broken lol";
		}
	}
}
