package Battleship;

public class AircraftCarrier extends Boat implements Attacker {
	private boolean hasPlanes;
	public AircraftCarrier(int
			teamID, Coordinates location, int dir) {
		super(teamID, location, dir, 5, 1, 1);
		this.hasPlanes = true;
	}
	
	 public String getID() {
	        return "A" + super.getTeam();
	   }
	 
	public String act(int[] choices, World w) {
        String result = "";
        choices[1] = 0;
         for (int i = 0; i < choices.length; i++) {
             if (choices[i] == 1) {
                 result += move(w) + "\n";
             }

             if (choices[i] == 2) {
                 result += turn(-1) + "\n";
             }

             if (choices[i] == 3) {
                 result += turn(1) + "\n";
             }

             if (choices[i] == 4) {
                 result += attack(w) + "\n";
             }
         }
         return result;
     }
	
	public String getActions() {
        return "Choose any of the following actions for the Aircraft Carrier: " + "\n" +
                "1. Move" + "\n" + "2. Turn left" + "\n" + "3. Turn right" + "\n" + "4. Launch Planes" + "\n";
    }
	
	public String attack(World w) {
        String result = "";
        double successRate = 1;
        double oldSucc = successRate;

        if (hasPlanes == false) {
            result += getID() + " has no planes remaining." + "\n";
            return result;
        }

        if (hasPlanes == true) {
            for (int i = Math.max(0, getLocation().getX() - getVision()); i <= Math.min(w.getWidth()-1, getLocation().getX() + getVision()); i++) {
                for (int j = Math.max(0, getLocation().getY() - getVision()); j <= Math.min(w.getHeight()-1, getLocation().getY() + getVision()); j++) {
                   
                	Coordinates checkedSpot = new Coordinates(i, j);
                	if (w.isLocationOccupied(checkedSpot)) {
                        if (w.getOccupant(checkedSpot).getTeam() != this.getTeam()) {
                            result += "Air raid!" + "\n";
                            result += w.getOccupant(checkedSpot).takeHit(getStrength()) + "\n";
                            successRate *= 0.8;
                            if (Math.random() > successRate) {
                                hasPlanes = false;
                                result += "The planes have been destroyed." + "\n";
                                return result;
                            }
                        }
                    }
                }
            }
        }

        if (successRate == oldSucc) {
            result += "There are no boats in range currently." + "\n";
        }
        
        return result;
    }
	
}

