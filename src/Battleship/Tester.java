//package Battleship;
//
//import java.util.concurrent.TimeUnit;
//public class Tester {
//
//	public static void main(String[] args) throws InterruptedException {
//		World ocean = new World(10,10);
//		Boat[] teamOne = new Boat[5];
//		
//		Boat[] teamTwo = new Boat[5];
//
//		setTeam(teamTwo, ocean, 2);
//		System.out.println("*************");
//		System.out.println("BOATS TURNING");
//		System.out.println("*************");
//		TimeUnit.SECONDS.sleep(1);
//		System.out.println(ocean.drawTeamMap(teamTwo, 2));
//		for (int i = 0; i < 7; i++) {
//			for (Boat b : teamTwo) {
//				b.turn(1);
//			}
//			System.out.println(ocean.drawTeamMap(teamTwo, 2));
//			TimeUnit.SECONDS.sleep(1);
//		}
//		System.out.println("*************");
//		System.out.println("BOATS MOVING");
//		System.out.println("*************");
//		TimeUnit.SECONDS.sleep(1);
//		
//		for (int i = 0; i < 7; i++) {
//			for (Boat b : teamTwo) {
//				System.out.println(b.move(ocean));
//				System.out.println(ocean.drawTeamMap(teamTwo, 2));
//				TimeUnit.SECONDS.sleep(1);
//			}
//			
//		}
//		/*
//		teamTwo[0] = new Submarine(2, new Coordinates(1, 3), 4, 2);
//		ocean.setOccupant(teamTwo[0], teamTwo[0].getLocation());
//		
//		System.out.println(ocean.drawTeamMap(teamTwo, 2));
//		TimeUnit.SECONDS.sleep(1);
//		for(int i = 0; i < 4; i++) {
//			teamTwo[0].submerge(ocean);
//			System.out.println(ocean.drawTeamMap(teamTwo, 2));
//			TimeUnit.SECONDS.sleep(1);
//		}
//		*/
//		
//		
//		
//		//System.out.println(ocean.drawTeamMap(teamOne, 2));
//		
//		
//		System.out.println("*************");
//		System.out.println("TEAMTWO MOVING/ATTACKING");
//		System.out.println("*************");
//		setTeam(teamOne, ocean, 1);
//		System.out.println(ocean.drawTeamMap(teamOne, 2));
//		for (int i = 0; i < 4; i++) {
//			for (Boat b : teamOne) {
//				System.out.println(b.move(ocean));
//				System.out.println(ocean.drawTeamMap(teamOne, 2));
//				TimeUnit.SECONDS.sleep(1);
//				if (b instanceof Attacker) System.out.println(((Attacker)b).attack(ocean));
//				System.out.println(ocean.drawTeamMap(teamOne, 2));
//				TimeUnit.SECONDS.sleep(1);
//			}
//			
//		}
//		System.out.println("*************");
//		System.out.println("  ATTACKS!");
//		System.out.println("*************");
//		TimeUnit.SECONDS.sleep(1);
//		clearTeam(teamOne, ocean);
//		clearTeam(teamTwo, ocean);
//		setTeamOneAttack(teamOne, ocean, 1);
//		System.out.println(ocean.drawTeamMap(teamOne, 2));
//		setTeamTwoAttack(teamTwo, ocean, 2);
//		System.out.println(ocean.drawTeamMap(teamOne, 2));
//		for (Boat b : teamOne) {
//			if (b instanceof Attacker) System.out.println(((Attacker)b).attack(ocean));
//			System.out.println(ocean.drawTeamMap(teamOne, 2));
//			TimeUnit.SECONDS.sleep(1);
//		}
//		
//	}
//	public static Coordinates getRandomCoordinates(World w) {
//		int xPos, yPos;
//		do {
//			xPos = (int) (Math.random() * w.getWidth());
//			yPos = (int) (Math.random() * w.getHeight());
//		} while (w.isLocationOccupied(new Coordinates(xPos, yPos)));
//
//		return new Coordinates(xPos, yPos);
//	}
//	
//	
//	public static void clearTeam(Boat[] b, World w) {
//		w.setOccupant(null,b[0].getLocation());
// 		w.setOccupant(null,b[1].getLocation());
// 		w.setOccupant(null,b[2].getLocation());
// 		w.setOccupant(null,b[3].getLocation());
// 		w.setOccupant(null,b[4].getLocation());
//	}
//	
//	
//	public static void setTeam(Boat[] b, World w, int t) {
//		b[0] = new Cruiser(t, getRandomCoordinates(w), (int) (Math.random() * 8));
//		w.setOccupant(b[0], b[0].getLocation());
//		b[1] = new Submarine(t, getRandomCoordinates(w),(int) (Math.random() * 8), 4);
// 		w.setOccupant(b[1],b[1].getLocation());
// 		b[2] = new AircraftCarrier(t, getRandomCoordinates(w),(int) (Math.random() * 8));
// 		w.setOccupant(b[2],b[2].getLocation());
// 		b[3] = new Destroyer(t, getRandomCoordinates(w),(int) (Math.random() * 8));
// 		w.setOccupant(b[3],b[3].getLocation());
// 		b[4] = new Battleship(t, getRandomCoordinates(w),(int) (Math.random() * 8));
// 		w.setOccupant(b[4],b[4].getLocation());
//	}
//	
//	
//	public static void setTeamOneAttack(Boat[] b, World w, int t) {
//		b[0] = new Cruiser(t, getRandomCoordinates(w), (int) (Math.random() * 8));
//		w.setOccupant(b[0], b[0].getLocation());
//		b[1] = new Submarine(t, new Coordinates(2, 2),0, 4);
// 		w.setOccupant(b[1],b[1].getLocation());
// 		b[2] = new AircraftCarrier(t, new Coordinates(4,4),2);
// 		w.setOccupant(b[2],b[2].getLocation());
// 		b[3] = new Destroyer(t, new Coordinates(6, 6),4);
// 		w.setOccupant(b[3],b[3].getLocation());
// 		b[4] = new Battleship(t,new Coordinates(8, 8),6);
// 		w.setOccupant(b[4],b[4].getLocation());
//	}
//	
//	public static void setTeamTwoAttack(Boat[] b, World w, int t) {
//		b[0] = new Cruiser(t, getRandomCoordinates(w), (int) (Math.random() * 8));
//		w.setOccupant(b[0], b[0].getLocation());
//		b[1] = new Submarine(t, new Coordinates(2, 1),0, 4);
// 		w.setOccupant(b[1],b[1].getLocation());
// 		b[2] = new AircraftCarrier(t, new Coordinates(5,4),2);
// 		w.setOccupant(b[2],b[2].getLocation());
// 		b[3] = new Destroyer(t, new Coordinates(6, 7),4);
// 		w.setOccupant(b[3],b[3].getLocation());
// 		b[4] = new Battleship(t,new Coordinates(7, 8),6);
// 		w.setOccupant(b[4],b[4].getLocation());
//	}
//
//
//}
