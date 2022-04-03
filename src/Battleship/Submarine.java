package Battleship;

public class Submarine extends ScoutBoat implements Attacker {
	private int numTorpedo;
	
	public Submarine(int teamID, Coordinates Location, int dir, int numTorpedo) {
		super(teamID, Location, dir, 3, 2);
		
		this.numTorpedo = numTorpedo;
	}
	
	public String getID() {
		return "S" + super.getTeam();
	}
	
	public String getActions() {
		String ret = "Choose any of the following actions for the Submarine:\r\n"
				+ " 1. Move\r\n"
				+ " 2. Turn left\r\n"
				+ " 3. Turn right\r\n"
				+ " 4. Submerge\r\n"
				+ " 5. Fire torpedoes\r\n";
		
		return ret;
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
				result += this.submerge(w) + "\n";
			}
			
			if(choices[i] == 5) {
				result += this.attack(w) + "\n";
			}
		}
		
		return result;
	}

	public String attack(World w) {
		if(this.numTorpedo <= 0) return this.toString() + " has no torpedoes remaining";
		Coordinates ourCoord = this.getLocation();
		this.numTorpedo--;
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torepedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
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
						if(boat.getTeam() != this.getTeam()) return "Fire torpedoes! " + boat.takeHit(this.getStrength());
						else return "Friendly Boat Ahead of " + this.getID();
					}
				}
				return "There are no boats in range currently.";
			default:
				return "code broken lol";
		}
	}
	
	public String submerge(World w) {
        while (true) {
            int new_x = (int)(Math.random() * w.getWidth());
            int new_y = (int)(Math.random() * w.getHeight());
            if ((new_x > getLocation().getX() + this.getVision() || new_x < getLocation().getX() - this.getVision()) && 
                    (new_y > getLocation().getY() + this.getVision() || new_y < getLocation().getY() - this.getVision())) {
                Coordinates n = new Coordinates(new_x, new_y);
                if (!w.isLocationOccupied(n)) {
                    Coordinates old = getLocation();
                    w.setNull(old);

                    setLocation(n);
                    return getID() + " moves from " + old.toString() + " to " + n.toString();
                }
            }
        }
     }


}
