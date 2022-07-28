package SpaceInvaders;
import java.awt.Graphics2D;

import utilities.GDV5;
public class InvaderState extends GDV5 {
	
	private SpaceInvadersGameState state = new MenuGameState(this);
	
	public void setState(SpaceInvadersGameState value) {
		this.state = value;
	}
	
	public static void main(String[] args) {
		InvaderState game = new InvaderState();
		game.start();
	}
	
	public void update() {
		state.update();
	}
	
	public void draw(Graphics2D win) {
		state.draw(win);
	}
}
