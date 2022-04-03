package Battleship;

public class Cruiser extends ScoutBoat{
	//int team, Coordinates location, int direction, int health, int strength, int vision
	public Cruiser(int teamID, Coordinates Location, int dir) {
		super(teamID, Location, dir, 3, 3);
	}
	
	public String getID() {
		return "C" + this.getTeam();
	}
	
	public String getActions() {
		String ret = "";
		ret += "Choose any of the following actions for the Cruiser: \n";
		ret += "1. Move \n";
		ret += "2. Turn Left \n";
		ret += "3. Turn Right \n";
		return ret;
	}
	
	public String act(int[] decisions, World w) {
		String result = "";
		
		for(int i = 0; i < decisions.length; i++) {
			if(decisions[i] == 1) {
				result += move(w) + "\n";
			}
			
			if(decisions[i] == 2) {
				result += turn(-1) + "\n";
			}
			
			if(decisions[i] == 3) {
				result += turn(1) + "\n";
			}
			
		}
		
		return result;
	}
}
